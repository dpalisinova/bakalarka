/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bakalarka.postgresql.dao.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowCallbackHandler;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;

/**
 *
 * @author Juraj
 */
public class GlucoseRangeGetRangesHandler implements RowCallbackHandler {

    private List<GlucoseRange> ranges = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        GlucoseRange range = new GlucoseRange();
        range.setId(rs.getLong(1));
        range.setHigh(rs.getBigDecimal(2));
        range.setLow(rs.getBigDecimal(3));
        range.setNoOfDays(rs.getInt(4));
        range.setUnits(rs.getString(5));
        ranges.add(range);
    }

    public List<GlucoseRange> getRanges() {
        return ranges;
    }

}
