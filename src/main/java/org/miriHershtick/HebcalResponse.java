package org.miriHershtick;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;
@Data
public class HebcalResponse {
    @JsonProperty("start")
    private String start;

    @JsonProperty("end")
    private String end;

    @JsonProperty("locale")
    private String locale;

    @JsonProperty("hdates")
    private Map<String ,HDate> hDates;

    public void printHDates(){
        for (Map.Entry<String, HDate> entry : hDates.entrySet()) {
            System.out.println("Date: " + entry.getKey()+ " -> Hebrew Date: " + entry.getValue().getHebrewDate());
            System.out.println("Events In This Date : " + entry.getValue().getEvents()+"\n");
        }
    }
}
