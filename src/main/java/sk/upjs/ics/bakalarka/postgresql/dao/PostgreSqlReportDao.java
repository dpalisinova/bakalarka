/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bakalarka.postgresql.dao;

import sk.upjs.ics.bakalarka.dao.ReportDao;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.entity.Study;

public class PostgreSqlReportDao implements ReportDao {

    private JdbcTemplate jdbcTemplate;
    private PostgreSqlStudyDao studyDao = (PostgreSqlStudyDao) DaoFactory.INSTANCE.getStudyDao();

    public PostgreSqlReportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Report> getAll() {
        String sql = "SELECT * FROM Patient";
        BeanPropertyRowMapper<Report> mapper = BeanPropertyRowMapper.newInstance(Report.class);

        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public void add(Report report) {

        for (Report r : this.getAll()) {
            if (r.getID().equals(report.getID())) {
                // taky pacient uz je v db, nemusim nic robit
                return;
            }
        }
        String sql = "INSERT INTO Patient(id, name, surname, \"DOB\") VALUES ( ?,?, ?, ?);";
        jdbcTemplate.update(sql, report.getID(),report.getName(), report.getSurname(), report.getDOB());
        // PostgreSqlStudyDao studyDao = (PostgreSqlStudyDao) DaoFactory.INSTANCE.getStudyDao();
        boolean studyExists = false;
        if (report.getStudies() != null) {
            for (Study study : report.getStudies()) {
                for (Study dbStudy : studyDao.getAll()) {
                    if (study.getId() == dbStudy.getId()) {
                        studyExists = true;
                        break;
                    }
                }
                if (!studyExists) {
                    study.setPatientId(report.getID());
                    studyDao.add(study);
                }

                studyExists = false;
            }
        }
    }
//TODO
   /* public Report select1() {
     String sql = "SELECT pt.name, pt.surname from Range r \n"
     + "JOIN Range_Pattern rp ON rp.rangeid = r.id\n"
     + "JOIN Pattern p ON p.id = rp.patternid\n"
     + "JOIN Study_Pattern sp ON sp.patternid = p.id\n"
     + "JOIN Study s ON s.id = sp.studyid\n"
     + "JOIN Patient pt ON pt.id = s.patientid\n"
     + "WHERE r.noofdays >=3 AND r.high > 33";
     jdbcTemplate.query(sql, null);
     }
     */

    @Override
    public void update(Report report) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Report report) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
