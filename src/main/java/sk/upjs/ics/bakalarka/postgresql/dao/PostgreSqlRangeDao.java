package sk.upjs.ics.bakalarka.postgresql.dao;

import sk.upjs.ics.bakalarka.dao.RangeDao;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;

public class PostgreSqlRangeDao implements RangeDao {

    private JdbcTemplate jdbcTemplate;

    public PostgreSqlRangeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GlucoseRange> getAll() {
        String sql = "SELECT * FROM range";
        BeanPropertyRowMapper<GlucoseRange> mapper = BeanPropertyRowMapper.newInstance(GlucoseRange.class);
        return jdbcTemplate.query(sql, mapper);

    }

    @Override
    public Long getId(GlucoseRange range) {
        String sql = "SELECT id FROM Range WHERE high = ? AND WHERE low = ? AND WHERE noofdays = ? AND WHERE units LIKE ?";
        BeanPropertyRowMapper<GlucoseRange> mapper = BeanPropertyRowMapper.newInstance(GlucoseRange.class);
        if (jdbcTemplate.query(sql, mapper, range.getRangeHigh(), range.getRangeLow(), range.getNoOfDays(), range.getUnits()).isEmpty()) {
            return -1L;
        }
        return jdbcTemplate.query(sql, mapper, range.getRangeHigh(), range.getRangeLow(), range.getNoOfDays(), range.getUnits()).get(0).getId();
    }

    @Override
    public void add(GlucoseRange range) { //DOROBIT ADD
        String sql = "INSERT INTO Range(high, low, noofdays, units) VALUES ( ?, ?, ?, ?);";
        jdbcTemplate.update(sql, range.getRangeHigh(), range.getRangeLow(), range.getNoOfDays(), range.getUnits());
    }

    @Override
    public void update(GlucoseRange range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(GlucoseRange range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
