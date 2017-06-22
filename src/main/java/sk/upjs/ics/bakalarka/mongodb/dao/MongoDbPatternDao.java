package sk.upjs.ics.bakalarka.mongodb.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
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
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.entity.Study;

public class MongoDbPatternDao implements PatternDao {

    private DB mongoConnection;
    private StudyDao studyDao = DaoFactory.INSTANCE.getStudyDao(DaoFactory.MONGODB, true);
    private List<Pattern> patterns = new ArrayList<>();
    private DBCollection collection;
    private static final String COLLECTION_REPORT = "report";

    public MongoDbPatternDao(DB db) {
        mongoConnection = db;
        collection = mongoConnection.getCollection(COLLECTION_REPORT);

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
        throw new UnsupportedOperationException("Please use reportDao.delete() method.");
    }
    
    //vyskusat TODO
    public List<GlucoseRange> getRangesByHighRangeAndNoOfDays(double rangeHigh, int patternNoOfDays) {
        List<GlucoseRange> ranges = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        List<BasicDBObject> partQueries = new ArrayList<>();
        partQueries.add(new BasicDBObject("NoOfDays", patternNoOfDays));
        BasicDBObject greater = new BasicDBObject("$gte", rangeHigh);
        partQueries.add(new BasicDBObject("GlucoseRanges.RangeHigh", greater));
        BasicDBObject and = new BasicDBObject("$and", partQueries);
        BasicDBObject fields = new BasicDBObject();

        BasicDBObject query = new BasicDBObject("Study.Patterns", new BasicDBObject("$elemMatch", and));
        DBCursor cursor = collection.find(query, fields);
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            try {
                Report report = mapper.readValue(object.toString(), Report.class);
                for (Study study : report.getStudies()) {
                    for (Pattern pattern : study.getPatterns()) {
                        ranges.addAll(pattern.getGlucoseRanges());

                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(MongoDbReportDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ranges;
    }

}
