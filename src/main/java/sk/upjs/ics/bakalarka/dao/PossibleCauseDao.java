package sk.upjs.ics.bakalarka.dao;

import java.util.List;
import sk.upjs.ics.bakalarka.entity.PossibleCause;

public interface PossibleCauseDao {

    public void add(PossibleCause possibleCause);

    public List<PossibleCause> getAll();

    public void delete(PossibleCause cause);

}
