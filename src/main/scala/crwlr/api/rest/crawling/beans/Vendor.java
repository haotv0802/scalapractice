package crwlr.api.rest.crawling.beans;

import java.util.Set;

/**
 * Date: 10/19/2017 Time: 5:00 PM
 * This Vendor is to be stored in Database (Not for presentation on Front-end).
 *
 * @author haho
 */
public class Vendor {
  private String name;
  private String location;
  private Double shipOnTime;
  private Integer positive;
  private Integer neutral;
  private Integer negative;
  private String link;
  private Integer timeOnLazada;
  private Double rating;
  private Integer size;
  private Set<VendorProduct> products;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Double getShipOnTime() {
    return shipOnTime;
  }

  public void setShipOnTime(Double shipOnTime) {
    this.shipOnTime = shipOnTime;
  }

  public Integer getPositive() {
    return positive;
  }

  public void setPositive(Integer positive) {
    this.positive = positive;
  }

  public Integer getNeutral() {
    return neutral;
  }

  public void setNeutral(Integer neutral) {
    this.neutral = neutral;
  }

  public Integer getNegative() {
    return negative;
  }

  public void setNegative(Integer negative) {
    this.negative = negative;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Vendor)) {
      return false;
    }
    Vendor comparedVendor = (Vendor) obj;
    if (comparedVendor.getName().equals(this.getName())) {
      return true;
    } else {
      return false;
    }
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public Integer getTimeOnLazada() {
    return timeOnLazada;
  }

  public void setTimeOnLazada(Integer timeOnLazada) {
    this.timeOnLazada = timeOnLazada;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Set<VendorProduct> getProducts() {
    return products;
  }

  public void setProducts(Set<VendorProduct> products) {
    this.products = products;
  }
}
