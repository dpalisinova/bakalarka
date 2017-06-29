package sk.upjs.ics.bakalarka.dao;

import java.util.List;
import java.util.Set;
import sk.upjs.ics.bakalarka.entity.GlucoseRange;
import sk.upjs.ics.bakalarka.entity.Pattern;

public interface PatternDao {

    public List<Pattern> getAll();

    public void add(Pattern patern);

    public void delete(Pattern pattern);

    public List<GlucoseRange> getRangesByHighRangeAndNoOfDays(double highRange, int patternNoOfDays);

    public List<Pattern> getPatternsByType(String type);

}
