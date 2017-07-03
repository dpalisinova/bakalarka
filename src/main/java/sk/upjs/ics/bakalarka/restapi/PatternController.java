
package sk.upjs.ics.bakalarka.restapi;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import sk.upjs.ics.bakalarka.dao.PatternDao;
import sk.upjs.ics.bakalarka.entity.Pattern;

@RestController
@RequestMapping("/patterns")
public class PatternController {
    //pre vyber testovanej databázy odkomentovat prislusny 
   // private PatternDao patternDao = DaoFactory.INSTANCE.getPatternDao(DaoFactory.POSTGRESQL, true);
     private PatternDao patternDao = DaoFactory.INSTANCE.getPatternDao(DaoFactory.MONGODB, true);

    public PatternController() {
    }

    @RequestMapping("/{type}")
    public List<Pattern> getPatternsByType(@PathVariable String type) throws UnsupportedEncodingException {
        return patternDao.getPatternsByType(URLDecoder.decode(type, "UTF-8"));
    }

}
