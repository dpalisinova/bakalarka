package sk.upjs.ics.bakalarka.mongodb.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.upjs.ics.bakalarka.dao.ReportDao;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.entity.Study;

public class MongoDbReportDao implements ReportDao {

    private DB mongoConnection;
    private DBCollection collection;
    private static final String COLLECTION_REPORT = "report";

    public MongoDbReportDao(DB db) {
        mongoConnection = db;
        collection = mongoConnection.getCollection(COLLECTION_REPORT);
    }

    @Override
    public List<Report> getAll() {
        ObjectMapper mapper = new ObjectMapper();
        List<Report> reports = new ArrayList<>();
        DBCursor c = collection.find();
        while (c.hasNext()) {
            DBObject objekt = c.next();
            try {
                Report report = mapper.readValue(objekt.toString(), Report.class);
                reports.add(report);
            } catch (IOException ex) {
                Logger.getLogger(MongoDbReportDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return reports;
    }

    /* public Report findById(Report report) {
     BasicDBObject field = new BasicDBObject();
     }
     */
    @Override
    public void add(Report report) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String jsonInString = mapper.writeValueAsString(report);
            DBObject object = (DBObject) JSON.parse(jsonInString);
            collection.insert(object);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(MongoDbReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Report report) {
        BasicDBObject objectToRemove = new BasicDBObject("ID", report.getId());
        collection.remove(objectToRemove);
        System.out.println("Report has been deleted.");

    }

    @Override
    public List<GlucoseRange> getRangesByPatient(String patientName) {
        List<GlucoseRange> ranges = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        BasicDBObject searchQuery = new BasicDBObject("Name", patientName);
        BasicDBObject field = new BasicDBObject();
        field.put("_id", 0);
        DBCursor cursor = collection.find(searchQuery, field);
        while (cursor.hasNext()) {
            DBObject objekt = cursor.next();
            try {
                Report report = mapper.readValue(objekt.toString(), Report.class);
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

    public List<Report> getPatientByDaytimeAndRangeHigh(String daytime, double rangeHigh) {
        List<Report> reports = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        List<BasicDBObject> partQueries = new ArrayList<>();
        partQueries.add(new BasicDBObject("Daytime", daytime));
        BasicDBObject greater = new BasicDBObject("$gte", rangeHigh);
        partQueries.add(new BasicDBObject("GlucoseRanges.RangeHigh", greater));
        BasicDBObject and = new BasicDBObject("$and", partQueries);
        BasicDBObject fields = new BasicDBObject();
        fields.put("Study", 0);
        BasicDBObject query = new BasicDBObject("Study.Patterns", new BasicDBObject("$elemMatch", and));
        DBCursor cursor = collection.find(query, fields);
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            try {
                Report report = mapper.readValue(object.toString(), Report.class);
                reports.add(report);
            } catch (IOException ex) {
                Logger.getLogger(MongoDbReportDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return reports;
    }

    public List<Report> getRangeHighPatternPatientBy(int rangeNoOfDays, double rangeHigh) {
        List<Report> reports = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        List<BasicDBObject> objects = new ArrayList<>();
        BasicDBObject greater = new BasicDBObject("$gte", rangeHigh);
        objects.add(new BasicDBObject("RangeHigh", greater));
        objects.add(new BasicDBObject("NoOfDays", rangeNoOfDays));
        BasicDBObject and = new BasicDBObject("$and", objects);

        BasicDBObject projection = new BasicDBObject("Study.Patterns.GlucoseRanges", new BasicDBObject("$elemMatch", and));
        DBCursor cursor = collection.find(projection);
        while (cursor.hasNext()) {
            DBObject objekt = cursor.next();
            try {
                Report report = mapper.readValue(objekt.toString(), Report.class);
                reports.add(report);
            } catch (IOException ex) {
                Logger.getLogger(MongoDbReportDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return reports;
    }

}
