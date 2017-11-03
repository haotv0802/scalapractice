package crwlr.api.rest.crawling.beans;

/**
 * Date: 10/20/2017 Time: 10:56 AM
 * This Vendor Product is to be presented on Front-end.
 * @author haho
 */
public class VendorProductPresenter {
  private String name;
  private String category;
  private String vendorName;
  private String vendorLocation;
  private Integer vendorShipOnTime;
  private Integer vendorPositive;
  private Integer vendorNeutral;
  private Integer vendorNegative;
  private String vendorLink;
  private Integer vendorTimeOnLazada;
  private Float vendorRating;
  private Integer vendorSize;

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

  public String getVendorName() {
    return vendorName;
  }

  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
  }

  public String getVendorLocation() {
    return vendorLocation;
  }

  public void setVendorLocation(String vendorLocation) {
    this.vendorLocation = vendorLocation;
  }

  public Integer getVendorShipOnTime() {
    return vendorShipOnTime;
  }

  public void setVendorShipOnTime(Integer vendorShipOnTime) {
    this.vendorShipOnTime = vendorShipOnTime;
  }

  public Integer getVendorPositive() {
    return vendorPositive;
  }

  public void setVendorPositive(Integer vendorPositive) {
    this.vendorPositive = vendorPositive;
  }

  public Integer getVendorNeutral() {
    return vendorNeutral;
  }

  public void setVendorNeutral(Integer vendorNeutral) {
    this.vendorNeutral = vendorNeutral;
  }

  public Integer getVendorNegative() {
    return vendorNegative;
  }

  public void setVendorNegative(Integer vendorNegative) {
    this.vendorNegative = vendorNegative;
  }

  public String getVendorLink() {
    return vendorLink;
  }

  public void setVendorLink(String vendorLink) {
    this.vendorLink = vendorLink;
  }

  public Integer getVendorTimeOnLazada() {
    return vendorTimeOnLazada;
  }

  public void setVendorTimeOnLazada(Integer vendorTimeOnLazada) {
    this.vendorTimeOnLazada = vendorTimeOnLazada;
  }

  public Float getVendorRating() {
    return vendorRating;
  }

  public void setVendorRating(Float vendorRating) {
    this.vendorRating = vendorRating;
  }

  public Integer getVendorSize() {
    return vendorSize;
  }

  public void setVendorSize(Integer vendorSize) {
    this.vendorSize = vendorSize;
  }
}
