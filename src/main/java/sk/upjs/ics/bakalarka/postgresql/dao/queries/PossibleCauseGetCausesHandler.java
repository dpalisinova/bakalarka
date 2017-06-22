package sk.upjs.ics.bakalarka.postgresql.dao.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowCallbackHandler;
import sk.upjs.ics.bakalarka.entity.PossibleCause;

public class PossibleCauseGetCausesHandler implements RowCallbackHandler {

    private List<PossibleCause> causes = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        PossibleCause cause = new PossibleCause();
        cause.setId(rs.getLong(1));
        cause.setCause(rs.getString(2));
        causes.add(cause);
    }

    public List<PossibleCause> getCauses() {
        return causes;
    }

}
