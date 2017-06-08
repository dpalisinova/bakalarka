package sk.upjs.ics.bakalarka.postgresql.dao.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowCallbackHandler;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.entity.PossibleCause;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.entity.Study;
import sk.upjs.ics.bakalarka.postgresql.dao.queries.RangeHighPatternTypePatientInfo;

public class ReportRangeHandler implements RowCallbackHandler {

    private List<Report> reports = new ArrayList<>();
    private List<Study> studies = new ArrayList<>();
    private List<Pattern> patterns = new ArrayList<>();
    private List<GlucoseRange> ranges = new ArrayList<>();
    private List<PossibleCause> causes = new ArrayList<>();
    private List<RangeHighPatternTypePatientInfo> types = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        GlucoseRange range = new GlucoseRange();

        range.setId(rs.getLong(1));
        range.setHigh(rs.getBigDecimal(2));
        range.setLow(rs.getBigDecimal(3));
        range.setNoOfDays(rs.getInt(4));
        range.setUnits(rs.getString(5));
        ranges.add(range);

        Pattern pattern = new Pattern();
        pattern.setGlucoseRanges(ranges);
        patterns.add(pattern);

        Study study = new Study();
        study.setPatterns(patterns);
        studies.add(study);

        Report report = new Report();
        report.setStudies(studies);
        reports.add(report);

    }

    public List<GlucoseRange> getRanges() {
        return ranges;
    }

}
