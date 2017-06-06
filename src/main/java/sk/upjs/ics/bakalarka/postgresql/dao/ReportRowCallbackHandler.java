
package sk.upjs.ics.bakalarka.postgresql.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.jdbc.core.RowCallbackHandler;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.entity.Study;

public class ReportRowCallbackHandler implements RowCallbackHandler {
    private List<Report> reports = new LinkedList<>();
    private List<Study> studies = new LinkedList<>();
    private List<Pattern> patterns = new LinkedList<>();
    private List<GlucoseRange> ranges = new LinkedList<>();
    

    @Override
    public void processRow(ResultSet rs) throws SQLException {
      GlucoseRange  range = new GlucoseRange();
      
    range.setId(rs.getLong(1));
    range.setHigh(rs.getFloat(2));
    range.setLow(rs.getFloat(3));
    range.setNoOfDays(rs.getInt(4));
    range.setUnits(rs.getString(5));
    ranges.add(range);
    
    Pattern pattern = new Pattern();
    pattern.setId(rs.getLong(6));
    pattern.setType(rs.getString(7));
    pattern.setDaytime(rs.getString(8));
    pattern.setNoOfDays(rs.getInt(9));
   // pattern.setTimePeriodStart(rs.getDate(10));
    //pattern.setTimePeriodEnd(rs.getDate(11));
    pattern.setGlucoseRanges(ranges);
    
    patterns.add(pattern);
    
    Study study = new Study();
    study.setId(rs.getLong(12));
   // study.setStartDate(rs.getDate(13));
    
    /**
     *
     * @return
     */
    }
     public List<Pattern> getPattern() {
        return patterns;
    }
    
    
}
