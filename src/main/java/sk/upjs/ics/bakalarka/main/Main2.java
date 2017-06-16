/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bakalarka.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import sk.upjs.ics.bakalarka.dao.PatternDao;
import sk.upjs.ics.bakalarka.dao.PossibleCauseDao;
import sk.upjs.ics.bakalarka.dao.GlucoseRangeDao;
import sk.upjs.ics.bakalarka.dao.ReportDao;
import sk.upjs.ics.bakalarka.dao.StudyDao;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.entity.PossibleCause;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.entity.Study;
import sk.upjs.ics.bakalarka.mongodb.dao.MongoDbReportDao;

/**
 *
 * @author Juraj
 */
public class Main2 {

    private ReportDao mongoDbReportDao = DaoFactory.INSTANCE.getReportDao("mongodb");
    private PatternDao mongoPatternDao = DaoFactory.INSTANCE.getPatternDao(DaoFactory.MONGODB);
    private StudyDao mongoStudyDao = DaoFactory.INSTANCE.getStudyDao(DaoFactory.MONGODB);
    private PossibleCauseDao mongoPossibleCause = DaoFactory.INSTANCE.getPossibleCauseDao(DaoFactory.MONGODB);
    private GlucoseRangeDao mongoRangeDao = DaoFactory.INSTANCE.getGlucoseRangeDao(DaoFactory.MONGODB);

    public void metoda() {
        /* List<Report> reports = new ArrayList<>();
         reports = mongoDbReportDao.getAll();
         System.out.println(reports.toString());
         */
        /* List<Pattern> patterns = new ArrayList<>();
         patterns = mongoPatternDao.getAll();
         System.out.println(patterns.toString());
         */
        List<Pattern> patterns = new ArrayList<>();
        System.out.println(mongoPatternDao.getAll());
        System.out.println("--------------------------");
        List<Pattern> ranges = new ArrayList<>();
        System.out.println(mongoRangeDao.getAll());

    }

    public static void main(String[] args) throws UnknownHostException, IOException {

        /**
         * ** Get collection / table from 'testdb' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
/*
         DBCollection col2 = db.getCollection("report");

         DBCursor c = col2.find();
         ObjectMapper mapper = new ObjectMapper();
         DBObject objekt = c.next();

         System.out.println(objekt.toString());
         Report ex = mapper.readValue(objekt.toString(), Report.class);
         System.out.println(c.next());
         */
        Main2 m = new Main2();
        m.metoda();

    }

}
