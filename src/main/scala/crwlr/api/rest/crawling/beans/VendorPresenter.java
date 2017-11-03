package crwlr.api.rest.crawling.beans;

/**
 * Date: 10/21/2017 Time: 5:00 PM
 * This Vendor is to be presented on Front-end.
 *
 * @author haho
 */
public class VendorPresenter {
  private String name;
  private Double shipOnTime;
  private Integer timeOnLazada;
  private Integer size;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getShipOnTime() {
    return shipOnTime;
  }

  public void setShipOnTime(Double shipOnTime) {
    this.shipOnTime = shipOnTime;
  }

  public Integer getTimeOnLazada() {
    return timeOnLazada;
  }

  public void setTimeOnLazada(Integer timeOnLazada) {
    this.timeOnLazada = timeOnLazada;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }
}
