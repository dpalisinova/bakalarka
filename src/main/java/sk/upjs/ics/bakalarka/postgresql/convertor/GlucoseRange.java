package sk.upjs.ics.bakalarka.postgresql.convertor;



import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "NoOfDays",
    "RangeHigh",
    "RangeLow",
    "Units"
})
public class GlucoseRange {

    @JsonProperty("NoOfDays")
    private Integer noOfDays;
    @JsonProperty("RangeHigh")
    private Double rangeHigh;
    @JsonProperty("RangeLow")
    private Double rangeLow;
    @JsonProperty("Units")
    private String units;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("NoOfDays")
    public Integer getNoOfDays() {
        return noOfDays;
    }

    @JsonProperty("NoOfDays")
    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }

    @JsonProperty("RangeHigh")
    public Double getRangeHigh() {
        return rangeHigh;
    }

    @JsonProperty("RangeHigh")
    public void setRangeHigh(Double rangeHigh) {
        this.rangeHigh = rangeHigh;
    }

    @JsonProperty("RangeLow")
    public Double getRangeLow() {
        return rangeLow;
    }

    @JsonProperty("RangeLow")
    public void setRangeLow(Double rangeLow) {
        this.rangeLow = rangeLow;
    }

    @JsonProperty("Units")
    public String getUnits() {
        return units;
    }

    @JsonProperty("Units")
    public void setUnits(String units) {
        this.units = units;
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
