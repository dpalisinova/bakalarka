package sk.upjs.ics.bakalarka.postgresql.convertor;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

import sk.upjs.ics.bakalarka.dao.DaoFactory;
import sk.upjs.ics.bakalarka.dao.ReportDao;
import sk.upjs.ics.bakalarka.entity.Report;

public class JsonToJava {

    private ReportDao reportDao = DaoFactory.INSTANCE.getReportDao(DaoFactory.POSTGRESQL, true);
    private DaoFactory dao = DaoFactory.INSTANCE;

//    public static void main(String[] args) {
//        JsonToJava obj = new JsonToJava();
//        obj.run();
//    }

    private void run() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Report[] ex = mapper.readValue(new File("C:\\Users\\Juraj\\Desktop\\JSON_DATA.json"), Report[].class);
                     
            for (Report report : ex) {
                reportDao.add(report);
            }
            System.out.println(ex);
            String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ex);
            System.out.println(prettyStaff1);
        } catch (IOException ex1) {
            Logger.getLogger(JsonToJava.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }

}
