package sk.upjs.ics.bakalarka.dao;

import java.util.List;
import sk.upjs.ics.bakalarka.entity.Pattern;

public interface PatternDao {

    public List<Pattern> getAll();

    public void add(Pattern patern);

    public void update(Pattern pattern);

    public void delete(Pattern pattern);

    public Long getIdBy(Pattern pattern);
}