package com.hensongeodata.technologies.locationgenerator.model;

public class LatLong {
      public String longitude;
      public String latitude;
      public String alttitude;
      public String accuracy;

      public LatLong() {
      }

      public LatLong(String longitude, String latitude, String alttitude, String accuracy) {
            this.longitude = longitude;
            this.latitude = latitude;
            this.alttitude = alttitude;
            this.accuracy = accuracy;
      }

      public String getLongitude() {
            return longitude;
      }

      public void setLongitude(String longitude) {
            this.longitude = longitude;
      }

      public String getLatitude() {
            return latitude;
      }

      public void setLatitude(String latitude) {
            this.latitude = latitude;
      }

      public String getAlttitude() {
            return alttitude;
      }

      public void setAlttitude(String alttitude) {
            this.alttitude = alttitude;
      }

      public String getAccuracy() {
            return accuracy;
      }

      public void setAccuracy(String accuracy) {
            this.accuracy = accuracy;
      }
}
