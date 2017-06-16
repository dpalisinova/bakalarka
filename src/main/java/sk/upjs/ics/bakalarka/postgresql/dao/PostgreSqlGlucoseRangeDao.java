package sk.upjs.ics.bakalarka.postgresql.dao;

import sk.upjs.ics.bakalarka.dao.GlucoseRangeDao;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;

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