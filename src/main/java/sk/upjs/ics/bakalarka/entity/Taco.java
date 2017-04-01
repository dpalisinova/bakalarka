package sk.upjs.ics.bakalarka.entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import sk.upjs.ics.bakalarka.postgresql.dao.DaoFactory;

import sk.upjs.ics.bakalarka.postgresql.dao.PatternDao;

public class Taco {

    PatternDao patternDao = DaoFactory.INSTANCE.getPatternDao();

    public void metoda() {
        Pattern novy = new Pattern();
        novy.setId(0);
        novy.setDaytime("vecer");
        novy.setType("'typ'");
        novy.setNoOfDays(2);
        novy.setTimePeriodStart(Time.valueOf("15:00:00"));
        novy.setTimePeriodEnd(Time.valueOf("12:00:00"));
        patternDao.add(novy);
        List<Pattern> vypis = new ArrayList<>();
       vypis = patternDao.getAll();
        for (int i = 0; i < vypis.size(); i++) {
            System.out.println(vypis.get(i).toString());
            //System.out.println(vypis.get(i).getPatientId()+ ", " + vypis.get(i).getStudyId()+ ", " + vypis.get(i).getFirstName()+ ", " + vypis.get(i).getSurname());
        }
    }

    public static void main(String[] args) {
    Taco ta= new Taco();
   ta.metoda();

    }

}
