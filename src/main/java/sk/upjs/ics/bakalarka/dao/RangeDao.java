package sk.upjs.ics.bakalarka.dao;

import java.util.List;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;

public interface RangeDao {

    public List<GlucoseRange> getAll();

    public void add(GlucoseRange range);

    public Long getId(GlucoseRange range);

    public void update(GlucoseRange range);

    public void delete(GlucoseRange range);

}
