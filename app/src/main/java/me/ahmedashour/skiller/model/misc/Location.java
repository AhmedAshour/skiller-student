package me.ahmedashour.skiller.model.misc;

import java.io.Serializable;

public class Location implements Serializable {
    private String street;
    private String apartment;
    private String moreInfo;
    private String city;
    private String address;
    private String district;
    private String floor;
    private String placeName;
    private String country;

    public Location(String address, String district, String placeName, String moreInfo) {
        this.placeName = placeName;
        this.moreInfo = moreInfo;
        this.address = address;
        this.district = district;
    }

    public Location() {
    }

    public Location(String address, String apartment, String district, String floor, String moreInfo) {
        this.address = address;
        this.apartment = apartment;
        this.district = district;
        this.floor = floor;
        this.moreInfo = moreInfo;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        /*int streetNum = Integer.parseInt(street);
        if (streetNum > 0)
            this.street = String.valueOf(streetNum);*/
        this.street = street;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        /*int apartmentNum = Integer.parseInt(apartment);
        if (apartmentNum > 0)
            this.apartment = String.valueOf(apartmentNum);*/
        this.apartment = apartment;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        if (placeName != null)
            return placeName;
        else
            return address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}