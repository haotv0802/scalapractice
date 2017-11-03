package crwlr.api.rest.crawling;

import crwlr.api.rest.crawling.beans.Vendor;
import crwlr.api.rest.crawling.beans.VendorProduct;
import crwlr.api.rest.crawling.interfaces.ICrawlingDao;
import crwlr.common.dao.DaoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * Date: 10/19/2017 Time: 4:56 PM
 * The DAO is responsible for saving (ADD & UPDATE) crawled data from given pages.
 * @author haho
 */
@Repository("crawlingDao")
public class CrawlingDao implements ICrawlingDao {

  private static final Logger LOGGER = LogManager.getLogger(CrawlingDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public CrawlingDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);

    this.namedTemplate = namedTemplate;
  }

  @Override
  public boolean isVendorExisting(String name) {
    final String sql =
        "SELECT COUNT(*) from crwlr_vendors WHERE name = :name"
        ;
    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("name", name);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.queryForObject(sql, paramsMap, Integer.class) > 0;
  }

  @Override
  public void addVendor(Vendor vendor) {
    final String sql =
        "INSERT INTO crwlr_vendors (name, location, positive, neutral, negative, link, timeOnLazada, rating, size, shipOnTime)"
      + "VALUE (:name, :location, :positive, :neutral, :negative, :link, :timeOnLazada, :rating, :size, :shipOnTime)          "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("name", vendor.getName());
    paramsMap.addValue("link", vendor.getLink());
    paramsMap.addValue("location", vendor.getLocation());
    paramsMap.addValue("positive", vendor.getPositive());
    paramsMap.addValue("neutral", vendor.getNeutral());
    paramsMap.addValue("negative", vendor.getNegative());
    paramsMap.addValue("timeOnLazada", vendor.getTimeOnLazada());
    paramsMap.addValue("rating", vendor.getRating());
    paramsMap.addValue("size", vendor.getSize());
    paramsMap.addValue("shipOnTime", vendor.getShipOnTime());

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    namedTemplate.update(sql, paramsMap);
  }

  @Override
  public void updateVendor(Vendor vendor) {
    final String sql =
      " UPDATE crwlr_vendors                                                                                         "
    + "   SET location   = :location, positive = :positive, neutral = :neutral, negative = :negative, link = :link,  "
    + "    timeOnLazada = :timeOnLazada, rating = :rating, size = :size, shipOnTime = :shipOnTime, updated = :updated"
    + " WHERE name = :name                                                                                           "
    ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("name", vendor.getName());
    paramsMap.addValue("link", vendor.getLink());
    paramsMap.addValue("location", vendor.getLocation());
    paramsMap.addValue("positive", vendor.getPositive());
    paramsMap.addValue("neutral", vendor.getNeutral());
    paramsMap.addValue("negative", vendor.getNegative());
    paramsMap.addValue("timeOnLazada", vendor.getTimeOnLazada());
    paramsMap.addValue("rating", vendor.getRating());
    paramsMap.addValue("size", vendor.getSize());
    paramsMap.addValue("shipOnTime", vendor.getShipOnTime());
    paramsMap.addValue("updated", new Date());

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    namedTemplate.update(sql, paramsMap);
  }

  @Override
  public boolean isProductExisting(String name, String vendorName) {
    final String sql =
        "SELECT COUNT(*) from crwlr_products WHERE name = :name AND vendor_name = :vendor_name"
        ;
    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("name", name);
    paramsMap.addValue("vendor_name", vendorName);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.queryForObject(sql, paramsMap, Integer.class) > 0;
  }

  @Override
  public void addVendorProduct(VendorProduct product, String vendorName) {
    final String sql =
              "INSERT INTO crwlr_products (name, category, vendor_name, link)  "
            + "VALUE (:name, :category, :vendor_name, :link)                   "
        ;
    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("name", product.getName());
    paramsMap.addValue("category", product.getCategory());
    paramsMap.addValue("vendor_name", vendorName);
    paramsMap.addValue("link", product.getLink());

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    namedTemplate.update(sql, paramsMap);
  }

  @Override
  public void updateVendorProduct(VendorProduct product, String vendorName) {
    final String sql =
        "UPDATE crwlr_products SET category = :category, updated = :updated, link = :link"
      + " WHERE name = :name AND vendor_name = :vendor_name                              "
        ;
    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("name", product.getName());
    paramsMap.addValue("category", product.getCategory());
    paramsMap.addValue("vendor_name", vendorName);
    paramsMap.addValue("updated", new Date());
    paramsMap.addValue("link", product.getLink());

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    namedTemplate.update(sql, paramsMap);
  }
}
