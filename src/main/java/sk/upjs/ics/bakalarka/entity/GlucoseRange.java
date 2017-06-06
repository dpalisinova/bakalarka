package sk.upjs.ics.bakalarka.entity;

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

    private Long id;
    
    @JsonProperty("RangeHigh")
    private float high;
    @JsonProperty("RangeLow")
    private float low;
    
    @JsonProperty("NoOfDays")
    private Integer noOfDays;
    
    @JsonProperty("Units")
    private String units;

    public GlucoseRange(GlucoseRange range) {
        this.high = range.high;
        this.low = range.low;
        this.noOfDays = range.noOfDays;
        this.units = range.units;
    }

    public GlucoseRange() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
@JsonProperty("RangeHigh")
    public float getHigh() {
        return high;
    }

    @JsonProperty("RangeHigh")
    public void setHigh(float high) {
        this.high = high;
    }

    @JsonProperty("RangeLow")
    public float getLow() {
        return low;
    }

    @JsonProperty("RangeLow")
    public void setLow(float low) {
        this.low = low;
    }

     @JsonProperty("NoOfDays")
    public Integer getNoOfDays() {
        return noOfDays;
    }

    @JsonProperty("NoOfDays")
    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }
    @JsonProperty("Units")
    public String getUnits() {
        return units;
    }

    @JsonProperty("Units")
    public void setUnits(String units) {
        this.units = units;
    }

   

    @Override
    public String toString() {
        return "Range{" + "id=" + id + ", high=" + high + ", low=" + low + ", noOfDays=" + noOfDays + ", units=" + units + '}';
    }
}
