package sk.upjs.ics.bakalarka.mongodb.dao;

import com.mongodb.DB;
import java.util.ArrayList;
import java.util.List;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import sk.upjs.ics.bakalarka.dao.PatternDao;
import sk.upjs.ics.bakalarka.dao.PossibleCauseDao;
import sk.upjs.ics.bakalarka.entity.Pattern;
import sk.upjs.ics.bakalarka.entity.PossibleCause;

public class MongoDbPossibleCauseDao implements PossibleCauseDao {

    private DB mongoConnection;
    private PatternDao patternDao = DaoFactory.INSTANCE.getPatternDao(DaoFactory.MONGODB);
    private List<PossibleCause> causes = new ArrayList<>();

    public MongoDbPossibleCauseDao(DB db) {
        mongoConnection = db;
    }

    @Override
    public List<PossibleCause> getAll() {
        for (Pattern pattern : patternDao.getAll()) {
            causes.addAll(pattern.getPossibleCauses());
        }
        return causes;
    }

    @Override
    public void add(PossibleCause possibleCause) {

    }

    @Override
    public Long getIdBy(PossibleCause cause) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PossibleCause cause) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
