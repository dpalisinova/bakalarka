package sk.upjs.ics.bakalarka.dao;

import java.util.List;
import sk.upjs.ics.bakalarka.entity.PossibleCause;

public interface PossibleCauseDao {

    public void add(PossibleCause possibleCause);

    //ID na LONG
    public List<PossibleCause> getAll();

    public Long getIdBy(PossibleCause cause);
    
    public void delete(PossibleCause cause);

}
