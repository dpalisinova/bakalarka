
package sk.upjs.ics.bakalarka.postgresql.dao;

import java.util.List;
import sk.upjs.ics.bakalarka.entity.Range;

public interface RangeDao {
   
    
 public List<Range> getAll();

    public void add(Range range);

    public void update(Range range);

    public void delete(Range range);
}
