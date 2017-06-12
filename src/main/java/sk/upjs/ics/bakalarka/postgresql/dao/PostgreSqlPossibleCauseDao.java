package sk.upjs.ics.bakalarka.postgresql.dao;

import sk.upjs.ics.bakalarka.dao.PossibleCauseDao;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bakalarka.entity.PossibleCause;

public class PostgreSqlPossibleCauseDao implements PossibleCauseDao {

    private JdbcTemplate jdbcTemplate;

    public PostgreSqlPossibleCauseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PossibleCause> getAll() {
        String sql = "SELECT * FROM possiblecause";
        BeanPropertyRowMapper<PossibleCause> mapper = BeanPropertyRowMapper.newInstance(PossibleCause.class);
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public Long getIdBy(PossibleCause cause) {
        String sql = "SELECT id FROM possiblecause WHERE cause LIKE ?";
        BeanPropertyRowMapper<PossibleCause> mapper = BeanPropertyRowMapper.newInstance(PossibleCause.class);
        if (jdbcTemplate.query(sql, mapper, cause.getCause()).isEmpty()) {

            return -1L;
        }
        return jdbcTemplate.query(sql, mapper, cause.getCause()).get(0).getId();

    }

    @Override
    public void add(PossibleCause possibleCause) {
        String sql = "INSERT INTO possiblecause (cause) VALUES (?)";
        jdbcTemplate.update(sql, possibleCause.getCause());
    }

    @Override
    public void delete(PossibleCause cause) {
        String sql = "DELETE FROM PossibleCause WHERE id = ?";
        jdbcTemplate.update(sql, cause.getId());
    }

}
