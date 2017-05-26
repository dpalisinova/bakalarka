
package sk.upjs.ics.bakalarka.dao;

import java.util.List;
import sk.upjs.ics.bakalarka.entity.Report;

public interface ReportDao {

    public List<Report> getAll();

    public void add(Report report);

    public void update(Report report);

    public void delete(Report report);
}
