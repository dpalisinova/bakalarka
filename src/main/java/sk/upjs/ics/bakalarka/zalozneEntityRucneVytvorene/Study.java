package sk.upjs.ics.bakalarka.zalozneEntityRucneVytvorene;

import java.util.Date;
import java.util.List;

public class Study {

    private Long id;
    private Date startDate;
    private Date endDate;
    private Long patientId;
    private List<Pattern> patterns;

    public List<Pattern> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "Study{" + "id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", patientId=" + patientId + '}';
    }
}
