package sk.upjs.ics.bakalarka.postgresql.dao;

import sk.upjs.ics.bakalarka.dao.RangeDao;
import sk.upjs.ics.bakalarka.dao.PossibleCauseDao;
import sk.upjs.ics.bakalarka.dao.PatternDao;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.entity.PossibleCause;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;

public class PostgreSqlPatternDao implements PatternDao {

    private JdbcTemplate jdbcTemplate;
    PossibleCauseDao possibleCauseDao = DaoFactory.INSTANCE.getPossibleCauseDao();
    RangeDao rangeDao = DaoFactory.INSTANCE.getRangeDao();

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
        String sql = "INSERT INTO pattern ( Type, Daytime, TimePeriodStart,  TimePeriodEnd,  NoOfDays) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, pattern.getType(), pattern.getDaytime(), pattern.getTimePeriodStart(), pattern.getTimePeriodEnd(), pattern.getNoOfDays());

        for (String cause : pattern.getPossibleCauses()) {
            if (possibleCauseDao.getIdByString(cause) == -1) {
                possibleCauseDao.add(new PossibleCause(cause));
            }
            String sql2 = "INSERT INTO pattern_possiblecause (patternId, causeId) VALUES (?,?)";
            jdbcTemplate.update(sql2, pattern.getId(), possibleCauseDao.getIdByString(cause));
        }
        for (GlucoseRange range : pattern.getGlucoseRanges()) {
            if (rangeDao.getId(range) == -1L) {
                rangeDao.add(new GlucoseRange(range));
            }

            String sql3 = "INSERT INTO range_pattern (rangeid, patternid) VALUES (?,?)";
            jdbcTemplate.update(sql3, rangeDao.getId(range), pattern.getId());
        }
    }

    @Override
    public Long getIdBy(Pattern pattern) {
        String sql = "SELECT id FROM pattern WHERE Type LIKE ? AND Daytime LIKE ? AND TimePeriodStart = ? AND  TimePeriodEnd = ? AND NoOfDays = ?";
        BeanPropertyRowMapper<Pattern> mapper = BeanPropertyRowMapper.newInstance(Pattern.class);
        if (jdbcTemplate.query(sql, mapper, pattern.getType(), pattern.getDaytime(), pattern.getTimePeriodStart(), pattern.getTimePeriodEnd(), pattern.getNoOfDays()).isEmpty()) {
            return -1L;
        }
        return jdbcTemplate.query(sql, mapper, pattern.getType(), pattern.getDaytime(), pattern.getTimePeriodStart(), pattern.getTimePeriodEnd(), pattern.getNoOfDays()).get(0).getId();
    }

    public List<Float> skusobny(Float hladina) {
        String sql = "SELECT r.* FROM Pattern p JOIN range_pattern rp ON p.id = rp.patternid\n"
                + "JOIN Range r ON r.id = rp.rangeid WHERE r.high = ? AND p.noofdays = 2";
        PatternRowCallbackHandler handler = new PatternRowCallbackHandler();
        jdbcTemplate.query(sql, handler, hladina);

        return handler.getHighResult();

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
