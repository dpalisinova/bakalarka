package sk.upjs.ics.bakalarka.restapi;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import sk.upjs.ics.bakalarka.dao.ReportDao;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Report;

@RestController
@RequestMapping("/reports")
public class ReportController {

    // private ReportDao reportDao = DaoFactory.INSTANCE.getReportDao(DaoFactory.POSTGRESQL, true);
    private ReportDao reportDao = DaoFactory.INSTANCE.getReportDao(DaoFactory.MONGODB, true);

    public ReportController() {
    }

    @RequestMapping()
    public List<Report> getAll() {
        return reportDao.getAll();
    }

    @RequestMapping("/ranges{patientName}")
    public List<GlucoseRange> get(@PathVariable String patientName) {
        return reportDao.getRangesByPatient(patientName);
    }

}
