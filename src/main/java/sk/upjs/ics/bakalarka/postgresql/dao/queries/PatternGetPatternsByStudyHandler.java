package sk.upjs.ics.bakalarka.postgresql.dao.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowCallbackHandler;
import sk.upjs.ics.bakalarka.entity.Pattern;

public class PatternGetPatternsByStudyHandler implements RowCallbackHandler {

    private List<Pattern> patterns = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        Pattern pattern = new Pattern();
        pattern.setId(rs.getLong(1));
        pattern.setType(rs.getString(2));
        pattern.setDaytime(rs.getString(3));
        pattern.setTimePeriodStart(rs.getTime(4));
        pattern.setTimePeriodEnd(rs.getTime(5));
        pattern.setNoOfDays(rs.getInt(6));
        patterns.add(pattern);
    }

    public List<Pattern> getPatterns() {
        return patterns;
    }

}
