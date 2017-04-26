package sk.upjs.ics.bakalarka.postgresql.convertor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import sk.upjs.ics.bakalarka.postgresql.convertor.GlucoseRange;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Type",
    "Daytime",
    "TimePeriodStart",
    "TimePeriodEnd",
    "NoOfDays",
    "GlucoseRanges",
    "PossibleCauses"
})
public class Pattern {

    @JsonProperty("Type")
    private String type;
    @JsonProperty("Daytime")
    private String daytime;
    @JsonProperty("TimePeriodStart")
    private String timePeriodStart;
    @JsonProperty("TimePeriodEnd")
    private String timePeriodEnd;
    @JsonProperty("NoOfDays")
    private Integer noOfDays;
    @JsonProperty("GlucoseRanges")
    private List<GlucoseRange> glucoseRanges = null;
    @JsonProperty("PossibleCauses")
    private List<String> possibleCauses = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("Daytime")
    public String getDaytime() {
        return daytime;
    }

    @JsonProperty("Daytime")
    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }

    @JsonProperty("TimePeriodStart")
    public String getTimePeriodStart() {
        return timePeriodStart;
    }

    @JsonProperty("TimePeriodStart")
    public void setTimePeriodStart(String timePeriodStart) {
        this.timePeriodStart = timePeriodStart;
    }

    @JsonProperty("TimePeriodEnd")
    public String getTimePeriodEnd() {
        return timePeriodEnd;
    }

    @JsonProperty("TimePeriodEnd")
    public void setTimePeriodEnd(String timePeriodEnd) {
        this.timePeriodEnd = timePeriodEnd;
    }

    @JsonProperty("NoOfDays")
    public Integer getNoOfDays() {
        return noOfDays;
    }

    @JsonProperty("NoOfDays")
    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }

    @JsonProperty("GlucoseRanges")
    public List<GlucoseRange> getGlucoseRanges() {
        return glucoseRanges;
    }

    @JsonProperty("GlucoseRanges")
    public void setGlucoseRanges(List<GlucoseRange> glucoseRanges) {
        this.glucoseRanges = glucoseRanges;
    }

    @JsonProperty("PossibleCauses")
    public List<String> getPossibleCauses() {
        return possibleCauses;
    }

    @JsonProperty("PossibleCauses")
    public void setPossibleCauses(List<String> possibleCauses) {
        this.possibleCauses = possibleCauses;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
