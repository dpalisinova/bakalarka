package sk.upjs.ics.bakalarka.postgresql.dao;

import sk.upjs.ics.bakalarka.dao.StudyDao;
import sk.upjs.ics.bakalarka.dao.PatternDao;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.entity.Study;

public class PostgreSqlStudyDao implements StudyDao {

    private JdbcTemplate jdbcTemplate;
    private PatternDao patternDao = DaoFactory.INSTANCE.getPatternDao();

    public PostgreSqlStudyDao(JdbcTemplate jdbcTemplate) {
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
        String sql = "INSERT INTO Study(id,startDate, endDate, patientId) VALUES(?,?,?,?)";//DOKONC
        jdbcTemplate.update(sql, study.getId(), study.getStartDate(), study.getEndDate(), study.getPatientId());
//doplnit, patientID ziskat z Reportu
        int index = 0;
        for (Pattern p : study.getPatterns()) {
            System.out.println("je tu vobeeeeeeec niecooooooooo" + study.getPatterns().size() + study.toString());
            if (patternDao.getIdBy(p, index) == -1L || patternDao.isNewPattern(p)) {
                patternDao.add(p);
                String sql2 = "INSERT INTO Study_pattern (studyId, patternId) VALUES(?,?)";
                jdbcTemplate.update(sql2, study.getId(), p.getId());
            } else {
                while (patternDao.getIdBy(p, index) == -1L) {
                    index++;
                }
                String sql2 = "INSERT INTO Study_pattern (studyId, patternId) VALUES(?,?)";
                jdbcTemplate.update(sql2, study.getId(), patternDao.getIdBy(p, index));

            }
        }
    }

    @Override
    public void update(Study study
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Study study
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
