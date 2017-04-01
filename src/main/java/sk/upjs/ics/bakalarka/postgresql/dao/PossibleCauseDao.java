/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bakalarka.postgresql.dao;

import java.util.List;
import sk.upjs.ics.bakalarka.entity.PossibleCause;

public interface PossibleCauseDao {

    public void add(PossibleCause possibleCause);
    //ID na LONG
    public List<String> getAll ();
    public int getIdByString(PossibleCause possibleCause);
    
}
