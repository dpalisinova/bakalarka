package sk.upjs.ics.bakalarka.postgresql.dao;

import sk.upjs.ics.bakalarka.dao.StudyDao;
import sk.upjs.ics.bakalarka.dao.PatternDao;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.entity.Study;

public class PostgreSqlStudyDao implements StudyDao {

    private JdbcTemplate jdbcTemplate;
    private PostgreSqlPatternDao patternDao = (PostgreSqlPatternDao) DaoFactory.INSTANCE.getPatternDao(DaoFactory.POSTGRESQL, true);
    private PostgreSqlGlucoseRangeDao rangeDao = (PostgreSqlGlucoseRangeDao) DaoFactory.INSTANCE.getGlucoseRangeDao(DaoFactory.POSTGRESQL);
    private PostgreSqlPossibleCauseDao possibleCauseDao = (PostgreSqlPossibleCauseDao) DaoFactory.INSTANCE.getPossibleCauseDao(DaoFactory.POSTGRESQL);
    private final boolean recursiveFetch;

    public PostgreSqlStudyDao(JdbcTemplate jdbcTemplate, boolean recursiveFetch) {
        this.jdbcTemplate = jdbcTemplate;
        this.recursiveFetch = recursiveFetch;
    }

    @Override
    public List<Study> getAll() {
        String sql = "SELECT * FROM study";
        BeanPropertyRowMapper<Study> mapper = BeanPropertyRowMapper.newInstance(Study.class);
        List<Study> studies = jdbcTemplate.query(sql, mapper);
        if (recursiveFetch) {
            for (Study study : studies) {
                study.setPatterns(patternDao.getPatterns(study));
            }
        }
        return studies;
    }

    @Override
    public void add(Study study) {
        String sql = "INSERT INTO Study(id,startDate, endDate, patientId) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, study.getId(), study.getStartDate(), study.getEndDate(), study.getPatientId());

        for (Pattern pattern : study.getPatterns()) {
            if (patternDao.getIdBy(pattern) == -1L || patternDao.isNewPattern(pattern)) {
                patternDao.add(pattern);
                String sql2 = "INSERT INTO Study_pattern (studyId, patternId) VALUES(?,?)";
                jdbcTemplate.update(sql2, study.getId(), pattern.getId());
            } else {
                String sql2 = "INSERT INTO Study_pattern (studyId, patternId) VALUES(?,?)";
                jdbcTemplate.update(sql2, study.getId(), patternDao.getIdBy(pattern));
            }
        }
    }

    @Override
    public void delete(Study study) {
        String sql = "DELETE FROM Study WHERE id = ?";
        jdbcTemplate.update(sql, study.getId());
    }

    public List<Study> getStudies(Report report) {
        String sql = "SELECT * FROM Study WHERE patientId = ?";
        BeanPropertyRowMapper<Study> mapper = BeanPropertyRowMapper.newInstance(Study.class);
        List<Study> studies = jdbcTemplate.query(sql, mapper, report.getId());
        for (Study study : studies) {
            study.setPatterns(patternDao.getPatterns(study));

        }

        return studies;
    }

}
