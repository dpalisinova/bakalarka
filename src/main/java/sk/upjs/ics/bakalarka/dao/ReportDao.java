package sk.upjs.ics.bakalarka.dao;

import java.util.List;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.postgresql.dao.queries.RangeHighPatternTypePatientInfo;

public interface ReportDao {

    public List<Report> getAll();

    public void add(Report report);

    public void update(Report report);

    public void delete(Report report);

    public List<GlucoseRange> getRangesBy(String patientName);

   // public List<Pattern> select2();

    public List<RangeHighPatternTypePatientInfo> getRangeHighPatternPatientInfo();
}
