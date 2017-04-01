/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bakalarka.postgresql.dao;

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
    public List<String> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIdByString(PossibleCause cause) {
        String sql = "SELECT id FROM possibleCauses WHERE cause LIKE ?";
        BeanPropertyRowMapper<PossibleCause> mapper = BeanPropertyRowMapper.newInstance(PossibleCause.class);
        return jdbcTemplate.query(sql, mapper, cause).get(0).getId();

    }

    @Override
    public void add(PossibleCause possibleCause) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
