package sk.upjs.ics.bakalarka.postgresql.dao.queries;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.jdbc.core.RowCallbackHandler;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Pattern;

/**
 *
 * @author Juraj
 */
public class PatternRowCallbackHandler implements RowCallbackHandler {

    private List<Pattern> patterns = new LinkedList<>();
    List<GlucoseRange> ranges = new ArrayList<>();
    private List<BigDecimal> highResults = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        GlucoseRange range = new GlucoseRange();
        range.setId(rs.getLong(1));
        range.setHigh(rs.getBigDecimal(2));//cisluje sa podla poradia v DB
        range.setLow(rs.getBigDecimal(3));
        range.setNoOfDays(rs.getInt(4));
        range.setUnits(rs.getString(5));
        Pattern p = new Pattern();
        ranges.add(range);

        p.setGlucoseRanges(ranges);

        patterns.add(p);
    }

    public List<Pattern> getPatterns() {
        return patterns;
    }

    public List<BigDecimal> getHighResult() {

        for (GlucoseRange r : ranges) {
            highResults.add(r.getHigh());
        }
        return highResults;
    }

    public List<GlucoseRange> getRanges() {
        return ranges;
    }
}
