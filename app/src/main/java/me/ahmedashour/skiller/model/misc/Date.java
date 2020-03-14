package me.ahmedashour.skiller.model.misc;


import java.io.Serializable;

import me.ahmedashour.skiller.constants.StringsConstants;

public class Date implements Serializable{
    private String day;
    private String month;
    private String year;

    public Date() {
    }

    public Date(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public static String monthFactory(String month) {
        if (month == null)
            return StringsConstants.Months.DEFAULT;

        switch (Integer.parseInt(month)) {
            case 1:
                return StringsConstants.Months.JANUARY;
            case 2:
                return StringsConstants.Months.FEBRUARY;
            case 3:
                return StringsConstants.Months.MARCH;
            case 4:
                return StringsConstants.Months.APRIL;
            case 5:
                return StringsConstants.Months.MAY;
            case 6:
                return StringsConstants.Months.JUNE;
            case 7:
                return StringsConstants.Months.JULY;
            case 8:
                return StringsConstants.Months.AUGUST;
            case 9:
                return StringsConstants.Months.SEPTEMBER;
            case 10:
                return StringsConstants.Months.OCTOBER;
            case 11:
                return StringsConstants.Months.NOVEMBER;
            case 12:
                return StringsConstants.Months.DECEMBER;
        }
        return null;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}