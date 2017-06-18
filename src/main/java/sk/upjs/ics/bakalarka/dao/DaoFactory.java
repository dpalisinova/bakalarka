package sk.upjs.ics.bakalarka.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bakalarka.postgresql.dao.PostgreSqlPatternDao;
import sk.upjs.ics.bakalarka.postgresql.dao.PostgreSqlPossibleCauseDao;
import sk.upjs.ics.bakalarka.postgresql.dao.PostgreSqlGlucoseRangeDao;
import sk.upjs.ics.bakalarka.postgresql.dao.PostgreSqlReportDao;
import sk.upjs.ics.bakalarka.postgresql.dao.PostgreSqlStudyDao;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.upjs.ics.bakalarka.mongodb.dao.MongoDbPatternDao;
import sk.upjs.ics.bakalarka.mongodb.dao.MongoDbPossibleCauseDao;
import sk.upjs.ics.bakalarka.mongodb.dao.MongoDbGlucoseRangeDao;
import sk.upjs.ics.bakalarka.mongodb.dao.MongoDbReportDao;
import sk.upjs.ics.bakalarka.mongodb.dao.MongoDbStudyDao;

public enum DaoFactory {

    INSTANCE;
    private JdbcTemplate jdbcTemplate;
    private PGSimpleDataSource source;
    private PostgreSqlReportDao postgreReportDao;
    private MongoDbReportDao mongoReportDao;
    private PostgreSqlPatternDao postgrePatternDao;
    private MongoDbPatternDao mongoPatternDao;
    private PostgreSqlPossibleCauseDao postgrePossibleCauseDao;
    private MongoDbPossibleCauseDao mongoPossibleCause;
    private PostgreSqlGlucoseRangeDao postgreRangeDao;
    private MongoDbGlucoseRangeDao mongoRangeDao;
    private PostgreSqlStudyDao postgreStudyDao;
    private MongoDbStudyDao mongoStudyDao;
    public static final String POSTGRESQL = "postgresql";
    public static final String MONGODB = "mongodb";

    public ReportDao getReportDao(String database) {
        switch (database) {
            case POSTGRESQL:
                if (this.postgreReportDao == null) {
                    this.postgreReportDao = new PostgreSqlReportDao(getJdbcTemplate());
                }
                return this.postgreReportDao;

            case MONGODB:
                if (this.mongoReportDao == null) {
                    try {
                        this.mongoReportDao = new MongoDbReportDao(getMongoConnection());
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
                        throw new RuntimeException("Unknown host", ex);
                    }
                }
                return this.mongoReportDao;
            default:
                throw new IllegalArgumentException("Unknown database type.");
        }

    }

    public PatternDao getPatternDao(String database) {
        switch (database) {
            case POSTGRESQL:
                if (this.postgrePatternDao == null) {
                    this.postgrePatternDao = new PostgreSqlPatternDao(getJdbcTemplate());
                }
                return this.postgrePatternDao;
            case MONGODB:
                if (this.mongoPatternDao == null) {
                    try {
                        this.mongoPatternDao = new MongoDbPatternDao(getMongoConnection());
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
                        throw new RuntimeException("Unknown host", ex);
                    }
                }
                return this.mongoPatternDao;
            default:
                throw new IllegalArgumentException("Unknown database type.");
        }

    }

    public PossibleCauseDao getPossibleCauseDao(String database) {
        switch (database) {
            case POSTGRESQL:
                if (this.postgrePossibleCauseDao == null) {
                    this.postgrePossibleCauseDao = new PostgreSqlPossibleCauseDao(getJdbcTemplate());
                }
                return this.postgrePossibleCauseDao;
            case MONGODB:
                if (this.mongoPossibleCause == null) {
                    try {
                        this.mongoPossibleCause = new MongoDbPossibleCauseDao(getMongoConnection());
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
                        throw new RuntimeException("Unknown host", ex);
                    }
                }
                return this.mongoPossibleCause;
            default:
                throw new IllegalArgumentException("Unknown database type.");
        }

    }

    public GlucoseRangeDao getGlucoseRangeDao(String database) {
        switch (database) {
            case POSTGRESQL:
                if (this.postgreRangeDao == null) {
                    this.postgreRangeDao = new PostgreSqlGlucoseRangeDao(getJdbcTemplate());
                }
                return this.postgreRangeDao;
            case MONGODB:
                if (this.mongoRangeDao == null) {
                    try {
                        this.mongoRangeDao = new MongoDbGlucoseRangeDao(getMongoConnection());
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
                        throw new RuntimeException("Unknown host", ex);
                    }
                }
                return this.mongoRangeDao;
            default:
                throw new IllegalArgumentException("Unknown database type.");
        }

    }

    public StudyDao getStudyDao(String database) {
        switch (database) {
            case POSTGRESQL:
                if (this.postgreStudyDao == null) {
                    this.postgreStudyDao = new PostgreSqlStudyDao(getJdbcTemplate());
                }
                return this.postgreStudyDao;
            case MONGODB:
                if (this.mongoStudyDao == null) {
                    try {
                        this.mongoStudyDao = new MongoDbStudyDao(getMongoConnection());
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
                        throw new RuntimeException("Unknown host", ex);
                    }
                }
                return this.mongoStudyDao;
            default:
                throw new IllegalArgumentException("Unknown database type.");
        }

    }

    public JdbcTemplate getJdbcTemplate() {
        if (this.jdbcTemplate == null) {
            this.jdbcTemplate = new JdbcTemplate(getdataSource());

        }
        return this.jdbcTemplate;
    }

    public DB getMongoConnection() throws UnknownHostException {
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("testdb");
        return db;

    }

    public DataSource getdataSource() {
        /* jdbc dataSource = new PGSimpleDataSource();
         dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
         dataSource.setDatabaseName("postgres");
         dataSource.setUser("postgres");
         dataSource.setPassword("dominika");
         this.dataSource = dataSource;*/

        source = new PGSimpleDataSource();
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
