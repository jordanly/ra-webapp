package ra.exceptions;
import org.json.JSONObject;

/**
 * Created by Jennie
 */
public class SchemaException extends Exception {
    private String message;

    public SchemaException(String message, String... args) {
        this.message = String.format(message, args);
    }

    public SchemaException(Exception exception) {
        this.message = exception.getMessage();
    }

    public JSONObject asJson() {
        JSONObject obj = new JSONObject();
        obj.put("message", message);
        return obj;
    }
}
