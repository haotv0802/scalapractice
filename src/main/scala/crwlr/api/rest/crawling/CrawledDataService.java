package crwlr.api.rest.crawling;

import crwlr.api.rest.crawling.beans.VendorPresenter;
import crwlr.api.rest.crawling.beans.VendorProductPresenter;
import crwlr.api.rest.crawling.interfaces.ICrawledDataDao;
import crwlr.api.rest.crawling.interfaces.ICrawledDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Date: 10/20/2017 Time: 10:19 AM
 * TODO: WRITE THE DESCRIPTION HERE
 *
 * @author haho
 */
@Repository("crawledDataService")
public class CrawledDataService implements ICrawledDataService {
  private final ICrawledDataDao crawledDataDao;

  private final Logger LOGGER = LogManager.getLogger(getClass());

  @Autowired
  public CrawledDataService(@Qualifier("crawledDataDao") ICrawledDataDao crawledDataDao) {
    Assert.notNull(crawledDataDao);

    this.crawledDataDao = crawledDataDao;
  }

  @Override
  public List<VendorProductPresenter> getAllVendorProducts() {
    return this.crawledDataDao.getAllVendorProducts();
  }

  @Override
  public List<VendorPresenter> getAllVendors() {
    return crawledDataDao.getAllVendors();
  }
}
