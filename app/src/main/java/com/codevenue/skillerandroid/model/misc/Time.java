package com.codevenue.skillerandroid.model.misc;

import java.io.Serializable;

public class Time implements Serializable {
    private String hour;
    private String minute;

    public Time() {
    }

    public Time(String hour, String minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return hour+":"+minute;
    }
}
