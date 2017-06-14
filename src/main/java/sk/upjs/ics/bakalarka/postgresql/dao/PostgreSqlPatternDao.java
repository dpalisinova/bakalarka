package sk.upjs.ics.bakalarka.postgresql.dao;

import sk.upjs.ics.bakalarka.postgresql.dao.queries.PatternGetPossibleCauseByPatternHandler;
import sk.upjs.ics.bakalarka.postgresql.dao.queries.PatternGetRangeByPatternHandler;
import sk.upjs.ics.bakalarka.postgresql.dao.queries.PatternRowCallbackHandler;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
    private PossibleCauseDao possibleCauseDao = DaoFactory.INSTANCE.getPossibleCauseDao("postgresql");
    private RangeDao rangeDao = DaoFactory.INSTANCE.getRangeDao("postgresql");

    public PostgreSqlPatternDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pattern> getAll() {
        String sql = "SELECT * FROM pattern";
        BeanPropertyRowMapper<Pattern> mapper = BeanPropertyRowMapper.newInstance(Pattern.class);

        return jdbcTemplate.query(sql, mapper);

    }

    public Long lastId() {
        String sql = "SELECT id from Pattern ORDER BY id DESC limit 1";
        BeanPropertyRowMapper<Pattern> mapper = BeanPropertyRowMapper.newInstance(Pattern.class);
        if (jdbcTemplate.query(sql, mapper).isEmpty()) {
            return 0L;
        }
        return jdbcTemplate.query(sql, mapper).get(0).getId();
    }

    @Override
    public void add(Pattern pattern) {

        String sql = "INSERT INTO pattern ( Type, Daytime, TimePeriodStart,  TimePeriodEnd,  NoOfDays) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, pattern.getType(), pattern.getDaytime(), pattern.getTimePeriodStart(), pattern.getTimePeriodEnd(), pattern.getNoOfDays());

        pattern.setId(this.lastId());

        for (PossibleCause possibleCause : pattern.getPossibleCauses()) {
            if (possibleCauseDao.getIdBy(possibleCause) == -1) {
                possibleCauseDao.add(new PossibleCause(possibleCause.getCause()));
            }

            String sql2 = "INSERT INTO pattern_possiblecause (patternId, possiblecausesId) VALUES (?,?)";
            jdbcTemplate.update(sql2, pattern.getId(), possibleCauseDao.getIdBy(possibleCause));

        }

        for (GlucoseRange range : pattern.getGlucoseRanges()) {

            if (rangeDao.getIdBy(range) == -1L) {
                rangeDao.add(new GlucoseRange(range));
            }
            String sql3 = "INSERT INTO range_pattern (rangeid, patternid) VALUES (?,?)";
            jdbcTemplate.update(sql3, rangeDao.getIdBy(range), pattern.getId());

        }
    }

    @Override
    public Long getIdBy(Pattern pattern) {
        List<Pattern> samePatterns = new ArrayList<>();

        String sql = "SELECT id FROM pattern WHERE Type LIKE ? AND Daytime LIKE ? AND TimePeriodStart = ? AND  TimePeriodEnd = ? AND NoOfDays = ?";
        BeanPropertyRowMapper<Pattern> mapper = BeanPropertyRowMapper.newInstance(Pattern.class);
        samePatterns = jdbcTemplate.query(sql, mapper, pattern.getType(), pattern.getDaytime(), pattern.getTimePeriodStart(), pattern.getTimePeriodEnd(), pattern.getNoOfDays());
        if (samePatterns.isEmpty()) {
            return -1L;
        }

        List<GlucoseRange> rangesFromSameList = new ArrayList<>();
        List<Long> rangeIdFromList = new ArrayList<>();
        List<Long> rangeIdFromInput = new ArrayList<>();

        List<PossibleCause> causesFromSameList = new ArrayList<>();
        List<Long> causeIdFromList = new ArrayList<>();
        List<Long> causeIdFromInput = new ArrayList<>();

        for (Pattern patternFromList : samePatterns) {
            rangesFromSameList = this.getRangeIdByPattern(patternFromList);
            causesFromSameList = this.getPossibleCauseByPattern(patternFromList);

            for (GlucoseRange rangeFromRPTable : rangesFromSameList) {
                rangeIdFromList.add(rangeFromRPTable.getId());
            }
            for (GlucoseRange gr : pattern.getGlucoseRanges()) {
                rangeIdFromInput.add(rangeDao.getIdBy(gr));
            }
            Collections.sort(rangeIdFromList);
            Collections.sort(rangeIdFromInput);

            for (PossibleCause causeFromPCPTable : causesFromSameList) {
                causeIdFromList.add(causeFromPCPTable.getId());
            }
            for (PossibleCause ps : pattern.getPossibleCauses()) {
                causeIdFromInput.add(possibleCauseDao.getIdBy(ps));
            }
            Collections.sort(causeIdFromList);
            Collections.sort(causeIdFromInput);

            if (rangeIdFromList.equals(rangeIdFromInput) && causeIdFromList.equals(causeIdFromInput)) {
                return patternFromList.getId();
            }
        }

        return -1L;
    }

    public boolean isNewPattern(Pattern pattern) {
    //Pattern contains new possibleCause/glucoseRange
        for (PossibleCause possibleCause : pattern.getPossibleCauses()) {
            if (possibleCauseDao.getIdBy(possibleCause) == -1L) {
                return true;
            }
        }

        for (GlucoseRange range : pattern.getGlucoseRanges()) {
            System.out.println("je tu vobec nejaky range???" + pattern.getGlucoseRanges().size());
            if (rangeDao.getIdBy(range) == -1L) {
                return true;
            }
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

    public List<GlucoseRange> getRangeIdByPattern(Pattern pattern) {
        String sql = "SELECT rangeid FROM Range_pattern WHERE patternId = ?";
        PatternGetRangeByPatternHandler handler = new PatternGetRangeByPatternHandler();
        jdbcTemplate.query(sql, handler, pattern.getId());

        return handler.getRanges();

    }

    public List<PossibleCause> getPossibleCauseByPattern(Pattern pattern) {
        String sql = "SELECT possibleCausesid FROM Pattern_possibleCause WHERE patternId = ?";
        PatternGetPossibleCauseByPatternHandler handler = new PatternGetPossibleCauseByPatternHandler();
        jdbcTemplate.query(sql, handler, pattern.getId());
        return handler.getPossibleCauses();
    }

    @Override
    public void update(Pattern pattern) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Pattern pattern) {
        String sql = "DELETE FROM Pattern WHERE id = ?";
        jdbcTemplate.update(sql, pattern.getId());
    }

}
