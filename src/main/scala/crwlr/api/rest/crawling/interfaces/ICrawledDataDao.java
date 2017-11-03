package crwlr.api.rest.crawling.interfaces;

import crwlr.api.rest.crawling.beans.VendorPresenter;
import crwlr.api.rest.crawling.beans.VendorProductPresenter;

import java.util.List;

/**
 * Date: 10/20/2017 Time: 9:44 AM
 *
 * @author haho
 */
public interface ICrawledDataDao {
  List<VendorProductPresenter> getAllVendorProducts();

  List<VendorPresenter> getAllVendors();
}
