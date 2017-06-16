package sk.upjs.ics.bakalarka.dao;

import java.util.List;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;

public interface GlucoseRangeDao {

    public List<GlucoseRange> getAll();

    public void add(GlucoseRange range);

    public Long getIdBy(GlucoseRange range);

    public void delete(GlucoseRange range);

}
