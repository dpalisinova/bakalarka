package sk.upjs.ics.bakalarka.mongodb.dao;

import com.mongodb.DB;
import java.util.ArrayList;
import java.util.List;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import sk.upjs.ics.bakalarka.dao.PatternDao;
import sk.upjs.ics.bakalarka.dao.GlucoseRangeDao;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Pattern;

public class MongoDbGlucoseRangeDao implements GlucoseRangeDao {

    private DB mongoConnection;
    private PatternDao patternDao = DaoFactory.INSTANCE.getPatternDao(DaoFactory.MONGODB);
    private List<GlucoseRange> ranges = new ArrayList<>();

    public MongoDbGlucoseRangeDao(DB db) {
        mongoConnection = db;
    }

    @Override
    public List<GlucoseRange> getAll() {
        for (Pattern pattern : patternDao.getAll()) {
            ranges.addAll(pattern.getGlucoseRanges());
        }
        return ranges;
    }

    @Override
    public void add(GlucoseRange range) {
        throw new UnsupportedOperationException("Please use reportDao.add() method.");
    }

    @Override
    public void delete(GlucoseRange range) {
        throw new UnsupportedOperationException("Please use reportDao.delete() method."); //To change body of generated methods, choose Tools | Templates.
    }
}
