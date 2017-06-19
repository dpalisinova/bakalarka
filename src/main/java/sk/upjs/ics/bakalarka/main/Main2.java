/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bakalarka.main;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import sk.upjs.ics.bakalarka.mongodb.dao.MongoDbGlucoseRangeDao;
import sk.upjs.ics.bakalarka.mongodb.dao.MongoDbPatternDao;
import sk.upjs.ics.bakalarka.mongodb.dao.MongoDbPossibleCauseDao;
import sk.upjs.ics.bakalarka.mongodb.dao.MongoDbReportDao;
import sk.upjs.ics.bakalarka.mongodb.dao.MongoDbStudyDao;
import sk.upjs.ics.bakalarka.postgresql.dao.PostgreSqlReportDao;

/**
 *
 * @author Juraj
 */
public class Main2 {

    private MongoDbReportDao mongoDbReportDao = (MongoDbReportDao) DaoFactory.INSTANCE.getReportDao("mongodb");
    private MongoDbPatternDao mongoPatternDao = (MongoDbPatternDao) DaoFactory.INSTANCE.getPatternDao(DaoFactory.MONGODB);
    private MongoDbStudyDao mongoStudyDao = (MongoDbStudyDao) DaoFactory.INSTANCE.getStudyDao(DaoFactory.MONGODB);
    private MongoDbPossibleCauseDao mongoPossibleCause = (MongoDbPossibleCauseDao) DaoFactory.INSTANCE.getPossibleCauseDao(DaoFactory.MONGODB);
    private MongoDbGlucoseRangeDao mongoRangeDao = (MongoDbGlucoseRangeDao) DaoFactory.INSTANCE.getGlucoseRangeDao(DaoFactory.MONGODB);

    public void metoda() {
        /* List<Report> reports = new ArrayList<>();
         reports = mongoDbReportDao.getAll();
         System.out.println(reports.toString());
         */
        /* List<Pattern> patterns = new ArrayList<>();
         patterns = mongoPatternDao.getAll();
         System.out.println(patterns.toString());
         */
        /*List<Pattern> patterns = new ArrayList<>();
         System.out.println(mongoPatternDao.getAll());
         System.out.println("--------------------------");
         List<Pattern> ranges = new ArrayList<>();
         System.out.println(mongoRangeDao.getAll());
         */
        //System.out.println(mongoDbReportDao.getRangeHighPatternPatientBy(3, 4.9));
      
        System.out.println(mongoDbReportDao.getRangesByPatient("Amanda"));
        //System.out.println(mongoDbReportDao.getPatientByDaytimeAndRangeHigh("Dinner time", 5));
        // mongoStudyDao.skuska();
        //System.out.println(mongoDbReportDao.getAll());
        mongoDbReportDao.delete(mongoDbReportDao.getAll().get(mongoDbReportDao.getAll().size() - 1));
       //System.out.println(mongoDbReportDao.getAll());
        //System.out.println(mongoDbReportDao.metoda().toString());
        //System.out.println(mongoStudyDao.getAll());
        //System.out.println(mongoPatternDao.getRangesByHighRangeAndNoOfDays(3.9, 2));
    }

    public static void main(String[] args) {

        Main2 m = new Main2();

        m.metoda();
    }

}
