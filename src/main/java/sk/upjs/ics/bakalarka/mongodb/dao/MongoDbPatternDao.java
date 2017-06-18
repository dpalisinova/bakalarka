package sk.upjs.ics.bakalarka.mongodb.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import sk.upjs.ics.bakalarka.dao.PatternDao;
import sk.upjs.ics.bakalarka.dao.StudyDao;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.entity.Study;

public class MongoDbPatternDao implements PatternDao {

    private DB mongoConnection;
    private StudyDao studyDao = DaoFactory.INSTANCE.getStudyDao(DaoFactory.MONGODB);
    private List<Pattern> patterns = new ArrayList<>();

    public MongoDbPatternDao(DB db) {
        mongoConnection = db;
    }

    @Override
    public List<Pattern> getAll() {
        for (Study study : studyDao.getAll()) {
            patterns.addAll(study.getPatterns());
        }
        return patterns;
    }

    @Override
    public void add(Pattern patern) {
        throw new UnsupportedOperationException("Please use reportDao.add() method.");
    }

    @Override
    public void delete(Pattern pattern) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GlucoseRange> getRangesByHighRangeAndNoOfDays(BigDecimal highRange, int noOfDays) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
