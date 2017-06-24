package sk.upjs.ics.bakalarka.postgresql.dao.queries;

import sk.upjs.ics.bakalarka.postgresql.dao.queries.RangeHighPatternTypePatientInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.jdbc.core.RowCallbackHandler;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.entity.PossibleCause;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.entity.Study;

public class ReportGetPatientByDaytimeAndRangeHighHandler implements RowCallbackHandler {

    private List<Report> reports = new ArrayList<>();
    private List<Study> studies = new ArrayList<>();
    private List<Pattern> patterns = new ArrayList<>();
    private List<GlucoseRange> ranges = new ArrayList<>();
    private List<PossibleCause> causes = new ArrayList<>();
    private List<RangeHighPatternTypePatientInfo> types = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {
       /* GlucoseRange range = new GlucoseRange();
        ranges.add(range);

        Pattern pattern = new Pattern();
        pattern.setGlucoseRanges(ranges);
        patterns.add(pattern);

        Study study = new Study();
        study.setPatterns(patterns);
        studies.add(study);*/

        Report report = new Report();
        report.setName(rs.getString(1));
        report.setSurname(rs.getString(2));
        report.setId(rs.getLong(3));
        report.setDOB(rs.getDate(4));

       // report.setStudies(studies);

        reports.add(report);

    }

    public List<Pattern> getPatterns() {
        return patterns;
    }

    public List<Report> getPatientInfo() {
        return reports;
    }

    public List<RangeHighPatternTypePatientInfo> getTypes() {
        for (int i = 0; i < ranges.size(); i++) {
            types.add(new RangeHighPatternTypePatientInfo(ranges.get(i).getHigh(), patterns.get(i).getType(), reports.get(i).getName(), reports.get(i).getSurname(), reports.get(i).getDOB()));
        }
        return types;
    }

    public List<Report> getReports() {
        return reports;
    }
}
