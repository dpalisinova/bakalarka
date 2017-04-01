package sk.upjs.ics.bakalarka.postgresql.dao;

import java.util.List;
import sk.upjs.ics.bakalarka.entity.Study;

public interface StudyDao {

    public List<Study> getAll();

    public void add(Study study);

    public void update(Study study);

    public void delete(Study study);
}
