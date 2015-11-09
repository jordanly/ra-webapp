package ra;


import org.json.JSONObject;
import util.ResultSetUtilities;
import ra.exceptions.SchemaException;

import java.sql.ResultSet;
import java.sql.SQLException;
import constants.SQLQueryConstants;

/**
 * Created by Jennie Ju
 */
public class SchemaRequest {
    private static final String SCHEMA_INDICATOR = "\\d";
    private static final int MAX_TOKENS = 2;

    private SchemaException exception;
    private ResultSet resultSet;
    private String sqlQuery;
    private String title;

    public SchemaRequest(RA ra, String sqlQuery)
    {
        this.sqlQuery = sqlQuery;
        try {
            this.resultSet = parseSchemaRequest(ra, sqlQuery);
        } catch (SchemaException e) {
            this.exception = e;
        }
    }

    private ResultSet parseSchemaRequest(RA ra, String sqlQuery) throws SchemaException{
        ResultSet rs = null;
        String[] tokens = sqlQuery.trim().split("\\s+");
        checkTokens(tokens);

        if (isRelationRequest(tokens)) {
            try {
                rs = ra.evaluateSQLQuery(SQLQueryConstants.RELATIONS_QUERY);
                this.title = "List of Relations";
            } catch (SQLException e) {
                // Should not reach this error
                throw new SchemaException(e); // TODO: Display real error
            }
        }
        else {
            try {
                String tableQuery = String.format(SQLQueryConstants.TABLE_QUERY, tokens[1]);
                rs = ra.evaluateSQLQuery(tableQuery);
                this.title = String.format("Table \"%s\"", tokens[1]);
                if (!rs.isBeforeFirst()) { // table empty
                    throw new SchemaException("Did not find relation named \"%s\".", tokens[1]);
                }
            } catch (SQLException e) {
                // Should not reach this error
                throw new SchemaException(e); // TODO: Display real error;
            }
        }
        return rs;
    }

    private void checkTokens(String[] tokens) throws SchemaException {
        if (tokens.length < 1) {
            throw new SchemaException("Not enough fields in schema request.");
        }
        if (tokens.length > SchemaRequest.MAX_TOKENS) {
            throw new SchemaException("Too many fields in schema request.");
        }
        if (!tokens[0].equals(SchemaRequest.SCHEMA_INDICATOR)) {
            throw new SchemaException("Unknown schema request indicator.");
        }
    }

    private boolean isRelationRequest(String[] tokens) {
        return tokens.length == 1;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();

        obj.put("query", sqlQuery);
        obj.put("isError", exception != null);
        if (exception != null) {
            obj.put("error", exception.asJson());
        } else {
            obj.put("title", this.title);
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
