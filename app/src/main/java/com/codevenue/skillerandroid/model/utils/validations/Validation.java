package com.codevenue.skillerandroid.model.utils.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean validatePhoneNumber(String phoneNumber) {
        Pattern VALID_PHONE_NUMBER_REGEX;
        Matcher matcher;

        VALID_PHONE_NUMBER_REGEX = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$", Pattern.CASE_INSENSITIVE);
        matcher = VALID_PHONE_NUMBER_REGEX.matcher(phoneNumber);
        if (matcher.find())
            return true;

        VALID_PHONE_NUMBER_REGEX = Pattern.compile("^\\d{10}$", Pattern.CASE_INSENSITIVE);
        matcher = VALID_PHONE_NUMBER_REGEX.matcher(phoneNumber);
        return matcher.find();

    }

    public static boolean validateEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
