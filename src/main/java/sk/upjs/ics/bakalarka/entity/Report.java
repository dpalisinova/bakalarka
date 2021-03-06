package sk.upjs.ics.bakalarka.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@JsonIgnoreProperties(value = {"_id"})
public class Report {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Surname")
    private String surname;
    @JsonProperty("ID")
    private Long id;
    @JsonProperty("DOB")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserialize.class)
    private Date dob;
    @JsonProperty("Study")
    private List<Study> studies;

    @JsonProperty("ID")
    public Long getId() {
        return id;
    }

    @JsonProperty("ID")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Surname")
    public String getSurname() {
        return surname;
    }

    @JsonProperty("Surname")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @JsonProperty("DOB")
    public Date getDOB() {
        return dob;
    }

    @JsonProperty("DOB")
    public void setDOB(Date DOB) {
        /*  SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
         Date date2 = null;
        
         try {
         date2 = dateFormat.parse(DOB.toString());
         } catch (ParseException ex) {
         Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
         }*/

        this.dob = DOB;
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
        return "Patient{" + "iD=" + id + ", name=" + name + ", surname=" + surname + ", DOB=" + dob + ", studies=" + studies + '}' + "\n";
    }

}
