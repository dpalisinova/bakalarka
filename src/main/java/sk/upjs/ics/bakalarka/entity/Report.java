package sk.upjs.ics.bakalarka.entity;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;
import java.util.List;
//premenovat stlpce tu aj v postgre databaze

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
    private String id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Surname")
    private String surname;
    @JsonProperty("DOB")
    private Date DOB;
    @JsonProperty("Study")
    private List<Study> studies;

    @JsonProperty("ID")
    public String getpatientID() {
        return id;
    }

    @JsonProperty("ID")
    public void setPatientID(String id) {
        this.id = id;
    }

    @JsonProperty("Name")
    public String getPatientName() {
        return name;
    }

    @JsonProperty("Name")
    public void setPatientName(String name) {
        this.name = name;
    }

    @JsonProperty("Surname")
    public String getPatientSurname() {
        return surname;
    }

    @JsonProperty("Surname")
    public void setPatientSurname(String surname) {
        this.surname = surname;
    }

    @JsonProperty("DOB")
    public Date getPatientDOB() {
        return DOB;
    }

    @JsonProperty("DOB")
    public void setPatientDOB(Date DOB) {
        this.DOB = DOB;
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
        return "Patient{" + "iD=" + id + ", name=" + name + ", surname=" + surname + ", DOB=" + DOB + ", studies=" + studies + '}';
    }

}
