package sk.upjs.ics.bakalarka.entity;

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

public class Taco {

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

        /* Report novy = new Report();
         Date d = null;
         d =new Date("1.5.2002");
         novy.setName("skuska2");
         novy.setSurname("datumu2");*/
       // reportDao.add(novy);
        /* SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
         Date date2 = null;
         try {
         date2 = dateFormat.parse(d.toString());
         } catch (ParseException ex) {
         Logger.getLogger(Taco.class.getName()).log(Level.SEVERE, null, ex);
         }
         novy.setDOB(date2);
         System.out.println(date2);*/
        // novy.setDOB(new Date("14/2/2001"));
        String str = "20.1.1975";
        try {
            System.out.println("datum: " + dateFormat.parse(str));
        } catch (ParseException ex) {
            Logger.getLogger(Taco.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Pattern> vypis = new ArrayList<>();
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
        }
        System.out.println(possibleCauseDao.getIdByString("Exercised around breakfast"));
        System.out.println(patternDao.getRangesByHighRangeAndNoOfDays(new BigDecimal("33.3"), 2));

    }

    public void select1() {

    }

    public static void main(String[] args) {
        Taco ta = new Taco();
        ta.metoda();
    }

}
