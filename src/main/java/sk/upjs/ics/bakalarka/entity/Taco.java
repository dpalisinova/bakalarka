package sk.upjs.ics.bakalarka.entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
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

    public void metoda() {
        /* Pattern novy = new Pattern();
         //  novy.setId(0L);
         novy.setDaytime("vecer");
         novy.setType("'typ'");
         novy.setNoOfDays(2);
         novy.setTimePeriodStart(Time.valueOf("15:00:00"));
         novy.setTimePeriodEnd(Time.valueOf("12:00:00"));
         patternDao.add(novy);*/
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
        System.out.println(pole.get(0).getRangeLow());
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

        
    }
    public void select1(){
    
    }

    public static void main(String[] args) {
        Taco ta = new Taco();
        ta.metoda();
    }

}
