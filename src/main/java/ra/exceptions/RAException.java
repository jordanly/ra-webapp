package ra.exceptions;

import org.antlr.v4.runtime.Token;
import org.json.JSONObject;

public class RAException extends Exception {
    private Token start;
    private Token end;
    private String message;

    public RAException(String message) {
        this.message = message;
    }

    public RAException(Token start, Token end, String message) {
        this.start = start;
        this.end = end;
        this.message = message;
    }

    public JSONObject asJson() {
        JSONObject obj = new JSONObject();

        if (start != null) {
            obj.put("start", start.getLine() + ":" + start.getCharPositionInLine());
        }
        if (end != null) {
            obj.put("end", end.getLine() + ":" + (end.getCharPositionInLine() + end.getText().length()));
        }
        obj.put("message", message);

        return obj;
    }
}
