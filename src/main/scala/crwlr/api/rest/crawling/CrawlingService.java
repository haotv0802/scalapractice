package crwlr.api.rest.crawling;

import crwlr.api.rest.crawling.beans.Vendor;
import crwlr.api.rest.crawling.beans.VendorProduct;
import crwlr.api.rest.crawling.interfaces.ICrawlingDao;
import crwlr.api.rest.crawling.interfaces.ICrawlingService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Date: 10/19/2017 Time: 4:56 PM
 *
 * @author haho
 */
@Service("crawlingService")
public class CrawlingService implements ICrawlingService {

  private final ICrawlingDao crawlingDao;

  private final Logger LOGGER = LogManager.getLogger(getClass());

  private int numberOfProductsCrawled = 5;

  @Autowired
  public CrawlingService(@Qualifier("crawlingDao") ICrawlingDao crawlingDao) {
    Assert.notNull(crawlingDao);

    this.crawlingDao = crawlingDao;
  }

  @Override
  public Map<String, Vendor> saveCrawledData(List<String> pages, Integer numberOfProductsCrawled) {
    if (null == numberOfProductsCrawled) {
      numberOfProductsCrawled = this.numberOfProductsCrawled;
    }

    Map<String, Vendor> vendorMap = new HashMap<>();
    for (String page : pages) {
      getVendorProduct(page, vendorMap, numberOfProductsCrawled);
    }

    Set<String> keys = vendorMap.keySet();
    for (String key : keys) {
      Vendor vendor = vendorMap.get(key);

      // Saving vendor
      if (crawlingDao.isVendorExisting(vendor.getName())) {
        crawlingDao.updateVendor(vendor);
      } else {
        crawlingDao.addVendor(vendor);
      }

      Set<VendorProduct> products = vendor.getProducts();
      for (VendorProduct product: products) {

        // Saving Product
        if (crawlingDao.isProductExisting(product.getName(), vendor.getName())) {
          crawlingDao.updateVendorProduct(product, vendor.getName());
        } else {
          crawlingDao.addVendorProduct(product, vendor.getName());
        }
      }
    }
    return vendorMap;
  }

  /**
   * Get list of products from given Vendor.
   * @param vendorLink
   */
  private void getVendorProduct(String vendorLink, Map<String, Vendor> vendorMap, int numberOfProductsCrawled) {
    try {
      Document document = Jsoup.connect(vendorLink).get();

      Elements content = document.select(".c-product-list");

      String sellerId = document.select("body").attr("data-spm");

      LOGGER.info(">>> Crawling vendor data: " + vendorLink);
      Vendor vendor = getVendorDetails(sellerId, vendorLink, vendorMap);

      Elements productLinks = content.select("a[href]");

      for (Element link : productLinks) {
        if (numberOfProductsCrawled-- < 0) {
          break;
        }
        String productLink = link.attr("abs:href");
        if (null == vendor) {
          getProductDetails(productLink, vendorMap);
        } else {
          getProductDetails(productLink, vendor);
        }
      }

    } catch (IOException e) {
      System.err.println("For '" + vendorLink + "': " + e.getMessage());
    }
  }

  /**
   *
   * @param sellerId
   * @param vendorLink
   * @param vendorMap
   * @return
   */
  private Vendor getVendorDetails(String sellerId, String vendorLink, Map<String, Vendor> vendorMap) {
    Vendor vendor = null;
    if (!StringUtils.isEmpty(sellerId)) {
      sellerId = sellerId.substring(sellerId.indexOf("-") + 1);
      try {
        JSONObject json = new JSONObject(
            IOUtils.toString(new URL("https://seller-transparency-api.lazada.sg/v1/seller/transparency?platform=desktop&lang=en&seller_id=" + sellerId),
                Charset.forName("UTF-8")));
        JSONObject seller = (JSONObject) json.get("seller");
        String name = seller.getString("name");
        vendor = vendorMap.get(name);
        if (null == vendor) {
          vendor = new Vendor();
          vendorMap.put(name, vendor);
        }
        String location = seller.getString("location");
        String shipOnTime = seller.getJSONObject("shipped_on_time").getString("average_rate");
        String positive = seller.getJSONObject("seller_reviews").getJSONObject("positive").getString("total");
        String negative = seller.getJSONObject("seller_reviews").getJSONObject("negative").getString("total");
        String neutral = seller.getJSONObject("seller_reviews").getJSONObject("neutral").getString("total");
        String timeOnLazada = seller.getJSONObject("time_on_lazada").getString("months");
        String sellerSize = seller.getString("size");
        String rating = seller.getString("rate");

        vendor.setName(name);
        vendor.setLocation(location);
        vendor.setShipOnTime(Double.valueOf(shipOnTime));
        vendor.setPositive(StringUtils.isEmpty(positive) ? null : Integer.valueOf(positive));
        vendor.setNegative(StringUtils.isEmpty(negative) ? null : Integer.valueOf(negative));
        vendor.setNeutral(StringUtils.isEmpty(neutral) ? null : Integer.valueOf(neutral));
        vendor.setTimeOnLazada(StringUtils.isEmpty(timeOnLazada) ? null : Integer.valueOf(timeOnLazada));
        vendor.setSize(StringUtils.isEmpty(sellerSize) ? null : Integer.valueOf(sellerSize));
        vendor.setRating(StringUtils.isEmpty(rating) ? null : Double.valueOf(rating));
        vendor.setLink(vendorLink);
      } catch (JSONException | IOException e) {
        e.printStackTrace();
        return null;
      }
    }
    return vendor;
  }

  private void getProductDetails(String productLink, Map<String, Vendor> vendorMap) {
    try {
      Document document = Jsoup.connect(productLink).get();


      String vendorName = document.select(".basic-info__name").get(0).text();

      Vendor vendor = vendorMap.get(vendorName);
      if (null == vendor) {
        vendor = new Vendor();
        vendor.setName(vendorName);

        String rating = document.select("div.seller-rating").attr("data-tooltip-header");
        rating = rating.substring(0, rating.indexOf("/"));
        vendor.setRating(StringUtils.isEmpty(rating) ? null : Double.valueOf(rating));

        Elements timeOnLazada = document.select(".time-on-lazada__value");
        vendor.setTimeOnLazada(timeOnLazada.size() > 0 ? Integer.valueOf(timeOnLazada.get(0).text()) : null);

        String size = document.select(".seller-size__content").select(".seller-size-icon").attr("data-level");
        vendor.setSize(StringUtils.isEmpty(size) ? null : Integer.valueOf(size));

        vendorMap.put(vendorName, vendor);
      }

      VendorProduct vendorProduct = new VendorProduct();
      String productName = document.select("#prod_title").text();
      Elements categories = document.select(".breadcrumb__list").select(".breadcrumb__item-text").select("a[title]");
      String category = null;
      if (null != categories && categories.size() > 0) {
        category = categories.get(0).select("span").text();
      }

      vendorProduct.setName(productName);
      vendorProduct.setCategory(category);
      vendorProduct.setLink(productLink);

      Set<VendorProduct> products = vendor.getProducts();
      if (null == products) {
        products = new HashSet<>();
        vendor.setProducts(products);
      }
      products.add(vendorProduct);

    } catch (IOException e) {
      System.err.println("For '" + productLink + "': " + e.getMessage());
    }
  }

  private void getProductDetails(String productLink, Vendor vendor) {
    try {
      Document document = Jsoup.connect(productLink).get();

      VendorProduct vendorProduct = new VendorProduct();
      String productName = document.select("#prod_title").text();
      Elements categories = document.select(".breadcrumb__list").select(".breadcrumb__item-text").select("a[title]");
      String category = null;
      if (null != categories && categories.size() > 0) {
        category = categories.get(0).select("span").text();
      }

      vendorProduct.setName(productName);
      vendorProduct.setCategory(category);
      vendorProduct.setLink(productLink);

      Set<VendorProduct> products = vendor.getProducts();
      if (null == products) {
        products = new HashSet<>();
        vendor.setProducts(products);
      }
      products.add(vendorProduct);

    } catch (IOException e) {
      System.err.println("For '" + productLink + "': " + e.getMessage());
    }
  }
}