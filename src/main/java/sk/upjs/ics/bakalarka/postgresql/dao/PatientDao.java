/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bakalarka.postgresql.dao;

import java.util.List;
import sk.upjs.ics.bakalarka.entity.Patient;

public interface PatientDao {

    public List<Patient> getAll();

    public void add(Patient patient);

    public void update(Patient patient);

    public void delete(Patient patient);
}
