package sk.upjs.ics.bakalarka.mongodb.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import java.util.ArrayList;
import java.util.List;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import sk.upjs.ics.bakalarka.dao.ReportDao;
import sk.upjs.ics.bakalarka.dao.StudyDao;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.entity.Study;

public class MongoDbStudyDao implements StudyDao {

    private DB mongoConnection;
    private DBCollection collection;
    private ReportDao reportDao = DaoFactory.INSTANCE.getReportDao(DaoFactory.MONGODB);
    private List<Study> studies = new ArrayList<>();
    private static final String COLLECTION_REPORT = "report";

    public MongoDbStudyDao(DB db) {
        mongoConnection = db;
        collection = mongoConnection.getCollection(COLLECTION_REPORT);
    }

    @Override
    public List<Study> getAll() {
        for (Report report : reportDao.getAll()) {
            studies.addAll(report.getStudies());
        }

        return studies;
    }

   
    @Override
    public void add(Study study) {
        throw new UnsupportedOperationException("Please use reportDao.add() method.");
    }

    @Override
    public void delete(Study study) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
