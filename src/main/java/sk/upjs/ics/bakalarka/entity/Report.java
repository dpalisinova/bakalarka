package sk.upjs.ics.bakalarka.entity;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ID",
    "Name",
    "Surname",
    "DOB",
    "Study"
})
public class Report {

    @JsonProperty("ID")
    private String patientId;
    @JsonProperty("Name")
    private String patientName;
    @JsonProperty("Surname")
    private String patientSurname;
    @JsonProperty("DOB")
    private String patientDOB;
    @JsonProperty("Study")
    private List<Study> studies;

    @JsonProperty("ID")
    public String getPatientID() {
        return patientId;
    }

    @JsonProperty("ID")
    public void setPatientID(String id) {
        this.patientId = id;
    }

    @JsonProperty("Name")
    public String getPatientName() {
        return patientName;
    }

    @JsonProperty("Name")
    public void setPatientName(String name) {
        this.patientName = name;
    }

    @JsonProperty("Surname")
    public String getPatientSurname() {
        return patientSurname;
    }

    @JsonProperty("Surname")
    public void setPatientSurname(String surname) {
        this.patientSurname = surname;
    }

    @JsonProperty("DOB")
    public String getPatientDOB() {
        return patientDOB;
    }

    @JsonProperty("DOB")
    public void setPatientDOB(String dob) {
        this.patientDOB = dob;
    }

    @JsonProperty("Study")
    public List<Study> getStudies() {
        return studies;
    }

    @JsonProperty("Study")
    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }

    @Override
    public String toString() {
        return "Report{" + "patientId=" + patientId + ", patientName=" + patientName + ", patientSurname=" + patientSurname + ", patientDOB=" + patientDOB + ", studies=" + studies + '}';
    }

  

}
