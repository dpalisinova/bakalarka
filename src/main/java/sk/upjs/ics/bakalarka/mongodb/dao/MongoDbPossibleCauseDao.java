package sk.upjs.ics.bakalarka.mongodb.dao;

import com.mongodb.DB;
import java.util.List;
import sk.upjs.ics.bakalarka.dao.PossibleCauseDao;
import sk.upjs.ics.bakalarka.entity.PossibleCause;

public class MongoDbPossibleCauseDao implements PossibleCauseDao{

    private DB mongoConnection;

    public MongoDbPossibleCauseDao(DB db) {
        mongoConnection = db;
    }

    @Override
    public void add(PossibleCause possibleCause) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PossibleCause> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getIdBy(PossibleCause cause) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PossibleCause cause) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
