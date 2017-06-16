package sk.upjs.ics.bakalarka.mongodb.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.upjs.ics.bakalarka.dao.ReportDao;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.postgresql.dao.queries.RangeHighPatternTypePatientInfo;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void delete(Report report) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GlucoseRange> getRangesByPatient(String patientName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Report> getPatientByDaytimeAndRangeHigh(String daytime, BigDecimal rangeHigh) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RangeHighPatternTypePatientInfo> getRangeHighPatternPatientBy(int noOfDays, BigDecimal rangeHigh) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
