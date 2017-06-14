package sk.upjs.ics.bakalarka.mongodb.dao;

import com.mongodb.DB;
import java.util.List;
import sk.upjs.ics.bakalarka.dao.StudyDao;
import sk.upjs.ics.bakalarka.entity.Study;

public class MongoDbStudyDao implements StudyDao {

    private DB mongoConnection;

    public MongoDbStudyDao(DB db) {
        mongoConnection = db;
    }

    @Override
    public List<Study> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Study study) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Study study) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Study study) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
