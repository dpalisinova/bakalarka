/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bakalarka.postgresql.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bakalarka.entity.Report;
import sk.upjs.ics.bakalarka.entity.Study;

public class PostgreSqlReportDao implements ReportDao {

    private JdbcTemplate jdbcTemplate;
    

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
        String sql = "INSERT INTO Patient(name, surname, \"DOB\") VALUES ( ?, ?, ?);";
        jdbcTemplate.update(sql, report.getPatientName(), report.getPatientSurname(), report.getPatientDOB());
        PostgreSqlStudyDao studyDao = (PostgreSqlStudyDao) DaoFactory.INSTANCE.getStudyDao();
        for(Study study : report.getStudies() ){
            studyDao.add(study);
        
        }
    }

    @Override
    public void update(Report report) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Report report) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
