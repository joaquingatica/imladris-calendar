package com.erutulco.utils;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.TimeZoneApi;
import com.google.maps.model.GeocodingResult;
import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;
import com.maxmind.geoip.LookupService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.FileUtils;

public class SunsetUtils {

  private static String GOOGLE_MAPS_API_KEY = null;
  private static GeoApiContext geoApiContext = null;

  public static void setGoogleMapsApiKey(String googleMapsApiKey) {
    GOOGLE_MAPS_API_KEY = googleMapsApiKey;
  }

  /**
   * Get Google Geo API context.
   * @return Geo API context
   */
  public static GeoApiContext getGeoApiContext() {
    if (geoApiContext == null) {
      geoApiContext = new GeoApiContext().setApiKey(GOOGLE_MAPS_API_KEY);
    }
    return geoApiContext;
  }

  /**
   * Convert Country and City to valid Geocode search string.
   * @param city City
   * @param country Country
   * @return Location string
   */
  public static String makeLocationString(String city, String country) {
    String place = "";
    if (city.length() > 0 || country.length() > 0) {
      if (city.length() > 0) {
        place += city;
      }
      if (country.length() > 0) {
        if (place.length() > 0) {
          place += ", ";
        }
        place += country;
      }
    }
    return place;
  }

  /**
   * Get current IP address.
   * @return IP address string
   */
  public static String getIpAddress() {
    String ip = null;
    try {
      URL url = new URL("https://api.ipify.org");
      HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
      connection.setDoOutput(true);
      InputStream is = connection.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      StringBuilder out = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        out.append(line);
      }
      ip = out.toString();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
    return ip;
  }

  /**
   * Calculate Country and City from IP Address.
   * @param ip IP address string
   * @return Country, City string array
   */
  public static String[] getCountryAndCityFromIpAddress(String ip) {
    String[] res = new String[2];
    try {
      InputStream is = SunsetUtils.class.getResourceAsStream(
          "/com/erutulco/utils/maxmind/GeoLiteCity.dat"
      );
      File tempFile = File.createTempFile("GeoLiteCity", ".dat");
      tempFile.deleteOnExit();
      FileUtils.copyInputStreamToFile(is, tempFile);

      LookupService cl = new LookupService(tempFile,
          LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);
      com.maxmind.geoip.Location location = cl.getLocation(ip);
      res[0] = location.countryName;
      res[1] = location.city;
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

    return res;
  }

  /**
   * Get current Coutry and City by using current IP address.
   * @return Country, City string array
   */
  public String[] getCurrentCountryAndCity() {
    String[] result = {};
    String ip = getIpAddress();
    if (ip != null && !ip.isEmpty()) {
      result = getCountryAndCityFromIpAddress(ip);
    }
    return result;
  }

  private static GeocodingResult getGeocodingResult(String city, String country) throws Exception {
    GeocodingResult result = null;
    String place = makeLocationString(city, country);
    if (place.length() > 0) {
      GeocodingResult[] results = GeocodingApi.geocode(getGeoApiContext(), place).await();
      if (results.length > 0) {
        result = results[0];
      }
    }
    return result;
  }

  private static TimeZone getTimeZone(GeocodingResult result) throws Exception {
    return TimeZoneApi.getTimeZone(getGeoApiContext(), result.geometry.location).await();
  }

  /**
   * Get location info form city and country, including Time Zone, Latitude and Longitude.
   * @param city City string
   * @param country Country string
   * @return Location Info with TZ, Lat and Long
   * @throws Exception Geo API exceptions
   */
  public static LocationInfo getLocationInfo(String city, String country) throws Exception {
    GeocodingResult result = getGeocodingResult(city, country);
    if (result != null) {
      TimeZone tz = getTimeZone(result);
      double lat = result.geometry.location.lat;
      double lng = result.geometry.location.lng;
      return new LocationInfo(tz.getID(), lat, lng);
    }
    return null;
  }

  /**
   * Calculate sunset for the day specified in "calendar", for the location in "info".
   * @param calendar Calendar instance
   * @param info Location info
   * @return Sunset time string
   */
  public static String calculateSunset(Calendar calendar, LocationInfo info) {
    String lat = Double.toString(info.getLat());
    String lng = Double.toString(info.getLng());
    Location location = new Location(lat, lng);
    SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, info.getTimeZone());
    String sunset = calculator.getOfficialSunsetForDate(calendar) + ":00";
    return sunset;
  }

}
