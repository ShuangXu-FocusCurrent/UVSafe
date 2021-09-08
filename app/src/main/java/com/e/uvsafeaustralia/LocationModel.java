package com.e.uvsafeaustralia;


public class LocationModel {
    private int id;
    private String postcode;
    private String suburb;
    private String latitude;
    private String longitude;

    public LocationModel(int id, String postcode, String suburb, String latitude, String longitude) {
        this.id = id;
        this.postcode = postcode;
        this.suburb = suburb;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LocationModel{" +
                "id=" + id +
                ", postcode='" + postcode + '\'' +
                ", suburb='" + suburb + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
