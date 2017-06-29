package sk.upjs.ics.bakalarka.dao;

import java.math.BigDecimal;
import java.util.List;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.postgresql.dao.queries.RangeHighPatternTypePatientInfo;

public interface ReportDao {

    public List<Report> getAll();

    public void add(Report report);

    public void delete(Report report);

    public List<GlucoseRange> getRangesByPatient(String patientName);

    public List<Report> getReportByDaytimeAndRangeHigh(String daytime, double rangeHigh);
    
    public List<Report> getReportByNoOfDaysAndRangeHigh(int rangeNoOfDays, double rangeHigh);
    
    public List<Report> getReportByPossibleCause(String cause);

   // public List<RangeHighPatternTypePatientInfo> getRangeHighPatternPatientBy(int rangeNoOfDays, double rangeHigh);
}
