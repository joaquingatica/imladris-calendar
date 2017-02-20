package com.erutulco.utils;

public class LocationInfo {

  private String timeZone;
  private double lat;
  private double lng;

  public String getTimeZone() {
    return this.timeZone;
  }

  public double getLat() {
    return this.lat;
  }

  public double getLng() {
    return this.lng;
  }

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public void setLng(double lng) {
    this.lng = lng;
  }

  /**
   * Location info container for time zone, latitude and longitude.
   * @param timeZone Time Zone id
   * @param lat Latitude
   * @param lng Longitude
   */
  public LocationInfo(String timeZone, double lat, double lng) {
    this.timeZone = timeZone;
    this.lat = lat;
    this.lng = lng;
  }

}
