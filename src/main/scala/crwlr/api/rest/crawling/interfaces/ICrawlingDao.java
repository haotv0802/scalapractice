package crwlr.api.rest.crawling.interfaces;

import crwlr.api.rest.crawling.beans.Vendor;
import crwlr.api.rest.crawling.beans.VendorProduct;

/**
 * Date: 10/19/2017 Time: 4:56 PM
 *
 * @author haho
 */
public interface ICrawlingDao {
  boolean isVendorExisting(String name);

  void addVendor(Vendor vendor);

  void updateVendor(Vendor vendor);

  boolean isProductExisting(String name, String vendorName);

  void addVendorProduct(VendorProduct product, String vendorName);

  void updateVendorProduct(VendorProduct product, String vendorName);
}
