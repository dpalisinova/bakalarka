package sk.upjs.ics.bakalarka.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Date;
import sk.upjs.ics.bakalarka.entity.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "StartDate",
    "EndDate",
    "Patterns"
})
public class Study {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("StartDate")
    @JsonDeserialize(using = DateAndTimeDeserialize.class)
    private Date startDate;
    @JsonProperty("EndDate")
    @JsonDeserialize(using = DateAndTimeDeserialize.class)
    private Date endDate;
    private Long patientId;
    @JsonProperty("Patterns")
    private List<Pattern> patterns = null;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("StartDate")
    public Date getStartDate() {
        return startDate;
    }

    @JsonProperty("StartDate")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("EndDate")
    public Date getEndDate() {
        return endDate;
    }

    @JsonProperty("EndDate")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("Patterns")
    public List<Pattern> getPatterns() {
        return patterns;
    }

    @JsonProperty("Patterns")
    public void setPatterns(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    @Override
    public String toString() {
        return "Study{" + "id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", patientId=" + patientId + ", patterns=" + patterns + '}';
    }

}
