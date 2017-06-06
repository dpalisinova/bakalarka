/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bakalarka.postgresql.dao;

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
    private List<Float> highResults = new ArrayList<>();
    
    @Override
    public void processRow(ResultSet rs) throws SQLException {
        GlucoseRange range = new GlucoseRange();
        range.setId(rs.getLong(1));
        range.setHigh(rs.getFloat(2));//cisluje sa podla poradia v DB
        range.setLow(rs.getFloat(3));
        range.setNoOfDays(rs.getInt(4));
        range.setUnits(rs.getString(5));
        Pattern p = new Pattern();
        System.out.println("Processing result set" + rs.toString());
        ranges.add(range);
        
        p.setGlucoseRanges(ranges);
        
        patterns.add(p);
    }
    
    public List<Pattern> getPatterns() {
        return patterns;
    }
    
    public List<Float> getHighResult() {
        
        for (GlucoseRange r : ranges) {
            highResults.add(r.getHigh());
        }
        return highResults;
    }
}
