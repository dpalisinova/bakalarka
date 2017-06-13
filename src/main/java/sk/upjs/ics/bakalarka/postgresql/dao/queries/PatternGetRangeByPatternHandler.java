package sk.upjs.ics.bakalarka.postgresql.dao.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowCallbackHandler;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Pattern;

public class PatternGetRangeByPatternHandler implements RowCallbackHandler {

    private List<GlucoseRange> ranges = new ArrayList<>();
    private List<Pattern> patterns = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        GlucoseRange range = new GlucoseRange();
        range.setId(rs.getLong(1));

        ranges.add(range);

    }

    public List<GlucoseRange> getRanges() {
        return ranges;
    }

}
