package sk.upjs.ics.bakalarka.postgresql.dao.queries;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import sk.upjs.ics.bakalarka.entity.Report;

public class RangeHighPatternTypePatientInfo {

    private BigDecimal high;
    private String patternType;
    private String patientName;
    private String patientSurename;
    private Date patientDOB;

    public RangeHighPatternTypePatientInfo(BigDecimal high, String patternType, String patientName, String patientSurename, Date patientDOB) {
        this.high = high;
        this.patternType = patternType;
        this.patientName = patientName;
        this.patientSurename = patientSurename;
        this.patientDOB = patientDOB;
    }

    @Override
    public String toString() {
        return "RangeHighPatternTypePatientInfo{" + "high=" + high + ", patternType=" + patternType + ", patientName=" + patientName + ", patientSurename=" + patientSurename + ", patientDOB=" + patientDOB + '}';
    }
    

   

}
