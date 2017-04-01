package sk.upjs.ics.bakalarka.postgresql.dao;

import java.sql.Connection;

import java.sql.SQLException;
import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public enum DaoFactory {

    INSTANCE;
    private JdbcTemplate jdbcTemplate;
    private PGSimpleDataSource source;
    private PatientDao patientDao;
    private PatternDao patternDao;
    private PossibleCauseDao possibleCauseDao;
    private RangeDao rangeDao;
    private StudyDao studyDao;

    public PatientDao getPatientDao() {
        if (this.patientDao == null) {
            this.patientDao = new PostgreSqlPatientDao(getJdbcTemplate());
        }
        return this.patientDao;
    }

    public PatternDao getPatternDao() {
        if (this.patternDao == null) {
            this.patternDao = new PostgreSqlPatternDao(getJdbcTemplate());
        }
        return this.patternDao;
    }

    public PossibleCauseDao getPossibleCauseDao() {
        if (this.possibleCauseDao == null) {
            this.possibleCauseDao = new PostgreSqlPossibleCauseDao(getJdbcTemplate());
        }
        return this.possibleCauseDao;
    }

    public RangeDao getRangeDao() {
        if (this.rangeDao == null) {
            this.rangeDao = new PostgreSqlRangeDao(getJdbcTemplate());
        }
        return this.rangeDao;

    }

    public StudyDao getStudyDao() {
        if (this.studyDao == null) {
            this.studyDao = new PostgreSqlStudyDao(getJdbcTemplate());
        }
        return this.studyDao;
    }

    public JdbcTemplate getJdbcTemplate() {
        if (this.jdbcTemplate == null) {
            this.jdbcTemplate = new JdbcTemplate(getdataSource());

        }
        return this.jdbcTemplate;
    }

    public DataSource getdataSource() {
        /* jdbc dataSource = new PGSimpleDataSource();
         dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
         dataSource.setDatabaseName("postgres");
         dataSource.setUser("postgres");
         dataSource.setPassword("dominika");
         this.dataSource = dataSource;*/

        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setUrl("jdbc:postgresql://localhost:5432/study");
        source.setDatabaseName("study");
        source.setUser("postgres");
        source.setPassword("dominika");

        Connection conn = null;
        try {
            conn = source.getConnection();

            // use connection
        } catch (SQLException e) {

        }

        return source;
    }

}
