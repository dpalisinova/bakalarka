package sk.upjs.ics.bakalarka.postgresql.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.entity.PossibleCause;

public class PostgreSqlPatternDao implements PatternDao {

    private JdbcTemplate jdbcTemplate;

    public PostgreSqlPatternDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pattern> getAll() {
        String sql = "SELECT * FROM pattern";
        BeanPropertyRowMapper<Pattern> mapper = BeanPropertyRowMapper.newInstance(Pattern.class);

        return jdbcTemplate.query(sql, mapper);

    }

    @Override
    public void add(Pattern pattern) {
        //ID na LONG"""""""""""""""""""""""""""""""
        String sql = "INSERT INTO pattern ( Type, Daytime, TimePeriodStart,  TimePeriodEnd,  NoOfDays) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, pattern.getType(), pattern.getDaytime(), pattern.getTimePeriodStart(), pattern.getTimePeriodEnd(), pattern.getNoOfDays());
        
        for(String cause : pattern.getPossibleCauses()){
            if(PossibleCauseDao.getIdByString(cause) == -1){
                PossibleCauseDao.add(new PossibleCause(cause));
            }
           String sql = "INSERT INTO pattern_possiblecauses (patternId, causeId) VALUES (?,?)";
           jdbcTemplate.update(sql, pattern.getId(), possiblecauseDao.getIdByString(cause));
                   
        }
    }

    @Override
    public void update(Pattern pattern) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Pattern pattern) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
