package sk.upjs.ics.bakalarka.mongodb.dao;

import com.mongodb.DB;
import java.util.List;
import sk.upjs.ics.bakalarka.dao.RangeDao;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;

public class MongoDbRangeDao implements RangeDao {

    private DB mongoConnection;

    public MongoDbRangeDao(DB db) {
        mongoConnection = db;
    }

    @Override
    public List<GlucoseRange> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(GlucoseRange range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getIdBy(GlucoseRange range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(GlucoseRange range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(GlucoseRange range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
