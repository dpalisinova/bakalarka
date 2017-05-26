/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import sk.upjs.ics.bakalarka.dao.ReportDao;

/**
 *
 * @author Juraj
 */
public class PostgreSqlPatientDaoTest {

    ReportDao patientDao = DaoFactory.INSTANCE.getPatientDao();

    @Test
    public void dajVsetkyTest() {
        List<Report> patients = patientDao.getAll();
        System.out.println(patients.size() + "velkoooooooost");
        assertEquals(2, patients.size());
    }

}
