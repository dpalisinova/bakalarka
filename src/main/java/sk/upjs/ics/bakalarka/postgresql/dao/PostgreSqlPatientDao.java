/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bakalarka.postgresql.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bakalarka.entity.Patient;

public class PostgreSqlPatientDao implements PatientDao {

    private JdbcTemplate jdbcTemplate;

    public PostgreSqlPatientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Patient> getAll() {
        String sql = "SELECT * FROM Patient";
        BeanPropertyRowMapper<Patient> mapper = BeanPropertyRowMapper.newInstance(Patient.class);

        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public void add(Patient patient) {
        String sql = "INSERT INTO Patient(name, surname, \"DOB\") VALUES ( ?, ?, ?);";
        jdbcTemplate.update(sql, patient.getName(), patient.getSurname(), patient.getDob());
    }

    @Override
    public void update(Patient patient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Patient patient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
