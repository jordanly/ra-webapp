package ra;


import org.json.JSONObject;
import util.ResultSetUtilities;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jennie Ju
 */
public class SchemaRequest {
    private Exception exception;
    private ResultSet resultSet;
    private String sqlQuery;

    public SchemaRequest(RA ra, String sqlQuery)
    {
        this.sqlQuery = sqlQuery;
        try {
            this.resultSet = ra.evaluateSQLQuery(sqlQuery);
        } catch (SQLException e) {
            this.exception = e;
        }
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();

        obj.put("query", sqlQuery);
        obj.put("isError", exception != null);
        if (exception != null) {
            obj.put("error", exception.getMessage());
        } else {
            obj.put("title", ResultSetUtilities.getTableName(resultSet));
            obj.put("columnNames", ResultSetUtilities.columnsToJSONArray(resultSet));
            obj.put("data", ResultSetUtilities.toJSONArray(resultSet));
        }

        return obj;
    }

    @Override
    public String toString() {
        return ResultSetUtilities.asString(resultSet);
    }

}
