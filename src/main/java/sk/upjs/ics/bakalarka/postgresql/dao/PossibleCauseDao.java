
package sk.upjs.ics.bakalarka.postgresql.dao;

import java.util.List;
import sk.upjs.ics.bakalarka.entity.PossibleCause;

public interface PossibleCauseDao {

    public void add(PossibleCause possibleCause);
    //ID na LONG
    public List<PossibleCause> getAll();
    public Long getIdByString(String cause);
    
    
}
