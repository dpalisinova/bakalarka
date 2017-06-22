package sk.upjs.ics.bakalarka.postgresql.dao;

import sk.upjs.ics.bakalarka.dao.GlucoseRangeDao;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.postgresql.dao.queries.GlucoseRangeGetRangesHandler;

public class PostgreSqlGlucoseRangeDao implements GlucoseRangeDao {

    private JdbcTemplate jdbcTemplate;

    public PostgreSqlGlucoseRangeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GlucoseRange> getAll() {
        String sql = "SELECT * FROM range";
        BeanPropertyRowMapper<GlucoseRange> mapper = BeanPropertyRowMapper.newInstance(GlucoseRange.class);
        return jdbcTemplate.query(sql, mapper);

    }

    public List<GlucoseRange> getRanges(Pattern pattern) {
        String sql = "SELECT r.* from Range r \n"
                + " JOIN Range_Pattern rp ON r.id = rp.rangeId\n"
                + " JOIN Pattern p ON p.id = rp.patternId\n"
                + " WHERE p.id = ?";
        GlucoseRangeGetRangesHandler handler = new GlucoseRangeGetRangesHandler();
        jdbcTemplate.query(sql, handler, pattern.getId());
        return handler.getRanges();

    }

    public Long getIdBy(GlucoseRange range) {
        String sql = "SELECT id FROM Range WHERE high = ? AND low = ? AND noofdays = ? AND units LIKE ?";
        BeanPropertyRowMapper<GlucoseRange> mapper = BeanPropertyRowMapper.newInstance(GlucoseRange.class);
        if (jdbcTemplate.query(sql, mapper, range.getHigh(), range.getLow(), range.getNoOfDays(), range.getUnits()).isEmpty()) {
            return -1L;
        }
        return jdbcTemplate.query(sql, mapper, range.getHigh(), range.getLow(), range.getNoOfDays(), range.getUnits()).get(0).getId();
    }

    @Override
    public void add(GlucoseRange range) {
        String sql = "INSERT INTO Range(high, low, noofdays, units) VALUES ( ?, ?, ?, ?);";
        jdbcTemplate.update(sql, range.getHigh(), range.getLow(), range.getNoOfDays(), range.getUnits());
    }

    @Override
    public void delete(GlucoseRange range) {
        String sql = "DELETE FROM Range WHERE id = ?";
        jdbcTemplate.update(sql, range.getId());
    }

}
