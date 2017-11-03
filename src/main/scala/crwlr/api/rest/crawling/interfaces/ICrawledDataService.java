package crwlr.api.rest.crawling.interfaces;

import crwlr.api.rest.crawling.beans.VendorPresenter;
import crwlr.api.rest.crawling.beans.VendorProductPresenter;

import java.util.List;

/**
 * Date: 10/20/2017 Time: 10:18 AM
 * TODO: WRITE THE DESCRIPTION HERE
 *
 * @author haho
 */
public interface ICrawledDataService {
  List<VendorProductPresenter> getAllVendorProducts();

  List<VendorPresenter> getAllVendors();
}
