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
    private float rangeHigh;
    @JsonProperty("RangeLow")
    private float rangeLow;
    
    @JsonProperty("NoOfDays")
    private Integer noOfDays;
    
    @JsonProperty("Units")
    private String units;

    public GlucoseRange(GlucoseRange range) {
        this.rangeHigh = range.rangeHigh;
        this.rangeLow = range.rangeLow;
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
    public float getRangeHigh() {
        return rangeHigh;
    }

    @JsonProperty("RangeHigh")
    public void setRangeHigh(float rangeHigh) {
        this.rangeHigh = rangeHigh;
    }

    @JsonProperty("RangeLow")
    public float getRangeLow() {
        return rangeLow;
    }

    @JsonProperty("RangeLow")
    public void setRangeLow(float rangeLow) {
        this.rangeLow = rangeLow;
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
        return "Range{" + "id=" + id + ", high=" + rangeHigh + ", low=" + rangeLow + ", noOfDays=" + noOfDays + ", units=" + units + '}';
    }
}
