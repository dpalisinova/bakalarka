package sk.upjs.ics.bakalarka.main;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import sk.upjs.ics.bakalarka.dao.ReportDao;

import sk.upjs.ics.bakalarka.dao.PatternDao;
import sk.upjs.ics.bakalarka.dao.PossibleCauseDao;
import sk.upjs.ics.bakalarka.dao.RangeDao;
import sk.upjs.ics.bakalarka.dao.StudyDao;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.entity.PossibleCause;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.entity.Study;

public class Main {
    
    PatternDao patternDao = DaoFactory.INSTANCE.getPatternDao();
    PossibleCauseDao possibleCauseDao = DaoFactory.INSTANCE.getPossibleCauseDao();
    RangeDao rangeDao = DaoFactory.INSTANCE.getRangeDao();
    StudyDao studyDao = DaoFactory.INSTANCE.getStudyDao();
    ReportDao reportDao = DaoFactory.INSTANCE.getReportDao();
    private SimpleDateFormat dateFormat = new SimpleDateFormat(
            "dd.MM.yyyy");
    
    public void metoda() {
        /* Pattern novy = new Pattern();
         //  novy.setId(0L);
         novy.setDaytime("vecer");
         novy.setType("'typ'");
         novy.setNoOfDays(2);
         novy.setTimePeriodStart(Time.valueOf("15:00:00"));
         novy.setTimePeriodEnd(Time.valueOf("12:00:00"));
         patternDao.add(novy);*/

        /* List<Pattern> vypis = new ArrayList<>();
         vypis = patternDao.getAll();
         for (int i = 0; i < vypis.size(); i++) {
         System.out.println(vypis.get(i).toString());
         //System.out.println(vypis.get(i).getPatientId()+ ", " + vypis.get(i).getStudyId()+ ", " + vypis.get(i).getFirstName()+ ", " + vypis.get(i).getSurname());
         }
         List<PossibleCause> causes = new ArrayList<>();

         causes = possibleCauseDao.getAll();
         for (int i = 0; i < causes.size(); i++) {
         System.out.println(causes.get(i).getCause());
         }
         List<GlucoseRange> pole = rangeDao.getAll();
         for (int i = 0; i < pole.size(); i++) {
         System.out.println(pole.get(i).toString());
         }
         System.out.println(pole.get(0).getLow());

         List<Study> studie = studyDao.getAll();
         for (int i = 0; i < studie.size(); i++) {
         System.out.println(studie.get(i).toString());

         }
         long startTime = System.currentTimeMillis();

         List<Report> reports = reportDao.getAll();
         long endTime = System.currentTimeMillis();
         System.out.println("duration of getAll from PostgreSql: " + (endTime - startTime));
         for (int i = 0; i < reports.size(); i++) {
         System.out.println(reports.get(i).toString());
         }*/
        //System.out.println(possibleCauseDao.getIdByString("Exercised around breakfast"));
        System.out.println(patternDao.getRangesByHighRangeAndNoOfDays(new BigDecimal("33.3"), 2));
        //System.out.println(reportDao.select1());
        System.out.println(reportDao.getRangeHighPatternPatientInfoBy(3, new BigDecimal("33")));
        // System.out.println(reportDao.select2());
        System.out.println(reportDao.getRangesBy("Steve"));
        System.out.println(reportDao.getPatientInfoBy("Post-dinner", new BigDecimal("33")));
        
    }
    
    public static void main(String[] args) {
        Main ta = new Main();
        ta.metoda();
    }
    
}
