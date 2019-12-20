package com.codevenue.skillerandroid.model.misc;

import com.codevenue.skillerandroid.model.utils.validations.Validation;

import java.io.Serializable;

public class Contact implements Serializable {
    private String email;
    private String phoneNumber;
    private Location location;

    public Contact() {
        location = new Location();
    }

    public Contact(String email, String phoneNumber, Location location) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (Validation.validateEmail(email))
            this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Location getLocation() {
        return location;
    }
}