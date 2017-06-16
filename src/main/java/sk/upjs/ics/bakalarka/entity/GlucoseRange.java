package sk.upjs.ics.bakalarka.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.math.BigDecimal;

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
    private BigDecimal high;
    @JsonProperty("RangeLow")
    private BigDecimal low;

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
    public BigDecimal getHigh() {
        return high;
    }

    @JsonProperty("RangeHigh")
    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    @JsonProperty("RangeLow")
    public BigDecimal getLow() {
        return low;
    }

    @JsonProperty("RangeLow")
    public void setLow(BigDecimal low) {
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
        return "\n  Range{" + "id=" + id + ", high=" + high + ", low=" + low + ", noOfDays=" + noOfDays + ", units=" + units + '}';
    }
}
