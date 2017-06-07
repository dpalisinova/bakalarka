package sk.upjs.ics.bakalarka.postgresql.dao;

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

public class ReportRowCallbackHandler implements RowCallbackHandler {

    private List<Report> reports = new ArrayList<>();
    private List<Study> studies = new ArrayList<>();
    private List<Pattern> patterns = new ArrayList<>();
    private List<GlucoseRange> ranges = new ArrayList<>();
    private List<PossibleCause> causes = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        GlucoseRange range = new GlucoseRange();

        range.setId(rs.getLong(1));
        range.setHigh(rs.getFloat(2));
        range.setLow(rs.getFloat(3));
        range.setNoOfDays(rs.getInt(4));
        range.setUnits(rs.getString(5));
        ranges.add(range);

        PossibleCause possibleCause = new PossibleCause();
        possibleCause.setId(rs.getLong(6));
        possibleCause.setCause(rs.getString(7));
        causes.add(possibleCause);

        Pattern pattern = new Pattern();
        pattern.setId(rs.getLong(8));
        pattern.setType(rs.getString(9));
        pattern.setDaytime(rs.getString(10));
        pattern.setNoOfDays(rs.getInt(11));
        pattern.setTimePeriodStart(rs.getTime(12));
        pattern.setTimePeriodEnd(rs.getTime(13));
        pattern.setGlucoseRanges(ranges);

        pattern.setGlucoseRanges(ranges);
        pattern.setPossibleCauses(causes);

        patterns.add(pattern);

        Study study = new Study();
        study.setId(rs.getLong(14));
        study.setStartDate(rs.getDate(15));
        study.setEndDate(rs.getDate(16));
        study.setPatientId(rs.getLong(17));

        study.setPatterns(patterns);

        studies.add(study);

        Report report = new Report();
        report.setId(rs.getLong(18));
        report.setName(rs.getString(19));
        report.setSurname(rs.getString(20));
        report.setDOB(rs.getDate(21));
        report.setStudies(studies);

        reports.add(report);

        /**
         *
         * @return
         */
    }

    public List<Pattern> getPattern() {
        return patterns;
    }

    public List<Report> getPatientInfo() {
        return reports;
    }
}
