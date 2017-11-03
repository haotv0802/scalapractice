//package crwlr.api.rest.crawling;
//
//import crwlr.api.rest.crawling.beans.Vendor;
//import crwlr.api.rest.crawling.beans.VendorPresenter;
//import crwlr.api.rest.crawling.beans.VendorProductPresenter;
//import crwlr.api.rest.crawling.interfaces.ICrawledDataService;
//import crwlr.api.rest.crawling.interfaces.ICrawlingService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.util.Assert;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * Date: 10/19/2017 Time: 4:56 PM
// *
// * @author haho
// */
//@RestController("crawlingResource")
//@RequestMapping(path = "/svc")
//public class CrawlingResource {
//
//  private final Logger LOGGER = LogManager.getLogger(getClass());
//
//  private final ICrawlingService crawlingService;
//
//  private final ICrawledDataService crawledDataService;
//
//  @Autowired
//  public CrawlingResource(
//      @Qualifier("crawlingService") ICrawlingService crawlingService,
//      @Qualifier("crawledDataService") ICrawledDataService crawledDataService
//  ) {
//    Assert.notNull(crawlingService);
//    Assert.notNull(crawledDataService);
//
//    this.crawlingService = crawlingService;
//    this.crawledDataService = crawledDataService;
//  }
//
//  @GetMapping("/crawler/crawledData")
//  public List<VendorProductPresenter> getCrawledData(
//  ) {
//    return crawledDataService.getAllVendorProducts();
//  }
//
//  @GetMapping("/crawler/crawlingData")
//  public Map<String, Vendor> crawlingData(
//      @RequestParam(value = "link", required = false) String link,
//      @RequestParam(value = "numberOfProductsCrawled", defaultValue = "5", required = false) Integer numberOfProductsCrawled
//  ) {
//    List<String> pages = new ArrayList<>();
//    if (StringUtils.isEmpty(link)) {
//      pages.add("https://www.lazada.sg/value-market");
//      pages.add("https://www.lazada.sg/empire-13");
//      pages.add("https://www.lazada.sg/boom_");
//      pages.add("https://www.lazada.sg/the-bro-store");
//      pages.add("https://www.lazada.sg/taka-jewellery1");
//      pages.add("https://www.lazada.sg/crystalawaking");
//      pages.add("https://www.lazada.sg/nicee-shop");
//      pages.add("https://www.lazada.sg/itechcool");
//      pages.add("https://www.lazada.sg/selffix-pte-ltd");
//      pages.add("https://www.lazada.sg/originalfook");
//    } else {
//      pages.add(link);
//    }
//    return this.crawlingService.saveCrawledData(pages, numberOfProductsCrawled);
//  }
//
//  @GetMapping("/crawler/vendors")
//  public List<VendorPresenter> getAllVendors(
//  ) {
//    return this.crawledDataService.getAllVendors();
//  }
//}