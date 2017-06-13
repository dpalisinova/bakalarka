/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bakalarka.postgresql.dao.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowCallbackHandler;
import sk.upjs.ics.bakalarka.entity.PossibleCause;

public class PatternGetPossibleCauseByPatternHandler implements RowCallbackHandler {

    private List<PossibleCause> causes = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        PossibleCause possibleCause = new PossibleCause();
        possibleCause.setId(rs.getLong(1));
        causes.add(possibleCause);
    }

    public List<PossibleCause> getPossibleCauses() {
        return causes;
    }

}
