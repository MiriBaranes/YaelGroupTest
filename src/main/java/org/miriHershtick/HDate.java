package org.miriHershtick;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HDate {

    @JsonProperty("hy")
    private String hebrewYear;

    @JsonProperty("hm")
    private String hebrewMonth;

    @JsonProperty("hd")
    private String hebrewDay;

    @JsonProperty("hebrew")
    private String hebrewDate;

    @JsonProperty("heDateParts")
    private Map<String, String> heDateParts;

    @JsonProperty("events")
    private List<String> events;

    public String toString(){
        return "Hebrew Date: "+hebrewDate+"\n";
    }

}
