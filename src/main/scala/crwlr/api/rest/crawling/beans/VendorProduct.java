package crwlr.api.rest.crawling.beans;

/**
 * Date: 10/19/2017 Time: 4:56 PM
 * This Vendor Product is to be stored in Database (Not for presentation on front-end).
 * @author haho
 */
public class VendorProduct {
  private String name;
  private String category;
  private String link;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }
}
