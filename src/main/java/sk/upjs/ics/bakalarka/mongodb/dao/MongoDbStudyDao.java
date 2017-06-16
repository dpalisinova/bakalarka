package sk.upjs.ics.bakalarka.mongodb.dao;

import com.mongodb.DB;
import java.util.ArrayList;
import java.util.List;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import sk.upjs.ics.bakalarka.dao.ReportDao;
import sk.upjs.ics.bakalarka.dao.StudyDao;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.entity.Study;

public class MongoDbStudyDao implements StudyDao {

    private DB mongoConnection;
    private ReportDao reportDao = DaoFactory.INSTANCE.getReportDao(DaoFactory.MONGODB);
    private List<Study> studies = new ArrayList<>();

    public MongoDbStudyDao(DB db) {
        mongoConnection = db;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Study study) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
