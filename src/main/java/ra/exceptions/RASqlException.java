package ra.exceptions;

import org.json.JSONObject;

import java.sql.SQLException;

/**
 * Created by jordanly on 10/22/15.
 */
public class RASqlException extends Exception implements RAException {
    private SQLException exception;

    public RASqlException(SQLException exception) {
        this.exception = exception;
    }

    @Override
    public JSONObject asJson() {
        JSONObject jo = new JSONObject();

        jo.put("type", "SQLException");
        jo.put("msg", exception.toString());

        return jo;
    }
}
