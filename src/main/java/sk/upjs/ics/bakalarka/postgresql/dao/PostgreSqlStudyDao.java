package sk.upjs.ics.bakalarka.postgresql.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bakalarka.entity.Study;

public class PostgreSqlStudyDao implements StudyDao {

    private JdbcTemplate jdbcTemplate;

    PostgreSqlStudyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Study> getAll() {
        String sql = "SELECT * FROM study ";
        BeanPropertyRowMapper<Study> mapper = BeanPropertyRowMapper.newInstance(Study.class);
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public void add(Study study) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Study study) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Study study) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
