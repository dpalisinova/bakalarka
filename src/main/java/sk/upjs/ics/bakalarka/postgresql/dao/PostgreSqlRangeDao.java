package sk.upjs.ics.bakalarka.postgresql.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bakalarka.entity.Range;

public class PostgreSqlRangeDao implements RangeDao {

    private JdbcTemplate jdbcTemplate;

    public PostgreSqlRangeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Range> getAll() {
        String sql = "SELECT * FROM range";
        BeanPropertyRowMapper<Range> mapper = BeanPropertyRowMapper.newInstance(Range.class);
        return jdbcTemplate.query(sql, mapper);

    }

    @Override
    public Long getId(Range range) {
        String sql = "SELECT id FROM Range WHERE high = ? AND WHERE low = ? AND WHERE noofdays = ? AND WHERE units LIKE ?";
        BeanPropertyRowMapper<Range> mapper = BeanPropertyRowMapper.newInstance(Range.class);
        if (jdbcTemplate.query(sql, mapper, range.getHigh(), range.getLow(), range.getNoOfDays(), range.getUnits()).isEmpty()) {
            return -1L;
        }
        return jdbcTemplate.query(sql, mapper, range.getHigh(), range.getLow(), range.getNoOfDays(), range.getUnits()).get(0).getId();
    }

    @Override
    public void add(Range range) { //DOROBIT ADD
        String sql = "INSERT INTO Range(high, low, noofdays, units) VALUES ( ?, ?, ?, ?);";
        jdbcTemplate.update(sql, range.getHigh(), range.getLow(), range.getNoOfDays(), range.getUnits());
    }

    @Override
    public void update(Range range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Range range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
