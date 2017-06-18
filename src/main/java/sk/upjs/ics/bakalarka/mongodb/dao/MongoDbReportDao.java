package sk.upjs.ics.bakalarka.mongodb.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import java.io.BufferedReader;
import org.bson.Document;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.upjs.ics.bakalarka.dao.ReportDao;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.postgresql.dao.queries.RangeHighPatternTypePatientInfo;

import org.slf4j.LoggerFactory;
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

        DBCursor c = collection.find();

        ObjectMapper mapper = new ObjectMapper();
        List<Report> reports = new ArrayList<>();
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

    @Override
    public void add(Report report) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String jsonInString = mapper.writeValueAsString(report);
            DBObject object = (DBObject) JSON.parse(jsonInString);
            collection.insert();
        } catch (JsonProcessingException ex) {
            Logger.getLogger(MongoDbReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Report report) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GlucoseRange> getRangesByPatient(String patientName) {
        List<GlucoseRange> ranges = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        BasicDBObject searchQuery = new BasicDBObject("Name", patientName);
        BasicDBObject field = new BasicDBObject();
        //field.put("GlucoseRanges", 1);
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

    @Override
    public List<Report> getPatientByDaytimeAndRangeHigh(String daytime, BigDecimal rangeHigh) {
        List<Report> reports = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        List<BasicDBObject> partQueries = new ArrayList<>();
        partQueries.add(new BasicDBObject("Daytime", daytime));
        partQueries.add(new BasicDBObject("GlucoseRanges.RangeHigh", rangeHigh));
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
//tato metoda je len zakomentovana v interace-i, lebo tu nemozem spravit customizovanu triedu co s nou??? TODO

    public List<Report> getRangeHighPatternPatientBy(int rangeNoOfDays, BigDecimal rangeHigh) {
        List<Report> reports = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        List<BasicDBObject> objects = new ArrayList<>();
        objects.add(new BasicDBObject("RangeHigh", rangeHigh));
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
