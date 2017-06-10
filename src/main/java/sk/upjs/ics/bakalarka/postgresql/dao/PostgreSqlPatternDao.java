package sk.upjs.ics.bakalarka.postgresql.dao;

import sk.upjs.ics.bakalarka.postgresql.dao.queries.PatternRowCallbackHandler;
import java.math.BigDecimal;
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
        jdbcTemplate.update(sql,  pattern.getType(), pattern.getDaytime(), pattern.getTimePeriodStart(), pattern.getTimePeriodEnd(), pattern.getNoOfDays());

        System.out.println("pattern.getID nastavil nejake id" + pattern.getId());

        for (PossibleCause possibleCause : pattern.getPossibleCauses()) {
            if (possibleCauseDao.getIdBy(possibleCause) == -1) {
                possibleCauseDao.add(new PossibleCause(possibleCause.getCause()));
            }
            System.out.println("ma pattern.getid nejaku hodnotu:" + pattern.getId());
            String sql2 = "INSERT INTO pattern_possiblecause (patternId, possiblecausesId) VALUES (?,?)";
            jdbcTemplate.update(sql2, pattern.getId(), possibleCauseDao.getIdBy(possibleCause));

        }

        for (GlucoseRange range : pattern.getGlucoseRanges()) {
            System.out.println("je tu vobec nejaky range???" + pattern.getGlucoseRanges().size());
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

    public boolean isNewPattern(Pattern pattern) {

        for (PossibleCause possibleCause : pattern.getPossibleCauses()) {
            System.out.println("id possible cause je" + possibleCause.getId());
            if (possibleCauseDao.getIdBy(possibleCause) != -1L && possibleCause.getId() == null) {
                return true;
//possibleCause.setId(possibleCauseDao.getIdBy(possibleCause));
                //System.out.println("id possible cause UUUZ je" + possibleCause.getId());
            }

            if (possibleCauseDao.getIdBy(possibleCause) == -1L) {
                return true;
            }

        }

        for (GlucoseRange range
                : pattern.getGlucoseRanges()) {
            System.out.println("je tu vobec nejaky range???" + pattern.getGlucoseRanges().size());
            if (rangeDao.getId(range) == -1L || this.hasMissingPatternRangeRow(pattern.getId(), range.getId())) {
                return true;
            }
        }

        return false;
    }
//pomocne tabulky

    public boolean hasMissingPatternPossibleCauseRow(long patternId, long causeId) {
        String sql = "SELECT patternId FROM pattern_possibleCause WHERE patternid = ? AND possiblecausesId = ?";
        BeanPropertyRowMapper<Pattern> mapper = new BeanPropertyRowMapper<>(Pattern.class
        );
        if (jdbcTemplate.query(sql, mapper, patternId, causeId)
                .isEmpty()) {
            return true;
        }

        return false;
    }

    public boolean hasMissingPatternRangeRow(long patternid, long rangeId) {
        String sql = "SELECT patternId FROM range_Pattern WHERE patternid? AND rangeId = ?";
        BeanPropertyRowMapper<Pattern> mapper = new BeanPropertyRowMapper<>(Pattern.class
        );
        if (jdbcTemplate.query(sql, mapper, patternid, rangeId)
                .isEmpty()) {
            return true;
        }

        return false;
    }

    public List<GlucoseRange> getRangesByHighRangeAndNoOfDays(BigDecimal hladina, int noOfDays) {
        String sql = "SELECT r.* FROM Pattern p JOIN range_pattern rp ON p.id = rp.patternid "
                + " JOIN Range r ON r.id = rp.rangeid WHERE r.high = ? AND p.noofdays = ?";
        PatternRowCallbackHandler handler = new PatternRowCallbackHandler();
        jdbcTemplate.query(sql, handler, hladina, noOfDays);

        return handler.getRanges();

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
