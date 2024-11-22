package org.miriHershtick;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class HebrewDateEntity{
    @JsonProperty("gy")
    private int gregorianYear;

    @JsonProperty("gm")
    private int gregorianMonth;

    @JsonProperty("gd")
    private int gregorianDay;

    @JsonProperty("afterSunset")
    private boolean afterSunset;

    @JsonProperty("hy")
    private int hebrewYear;

    @JsonProperty("hm")
    private String hebrewMonth;

    @JsonProperty("hd")
    private int hebrewDay;

    @JsonProperty("hebrew")
    private String hebrewDate;

    @JsonProperty("heDateParts")
    private Map<String, String> heDateParts;

    @JsonProperty("events")
    private List<String> events;

    public String getHebrewDateParts(){
        StringBuilder result = new StringBuilder();
        for(Map.Entry<String, String> entry : heDateParts.entrySet()){
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }
        return result.substring(0, result.length()-2);
    }
    public String getHebrewDate(){
        return hebrewDate;
    }
    public String getEvents(){
        return events.toString();
    }
    public String toString(){
        return "תאריך עברי:"+ "\n"+hebrewDate;
    }

}
