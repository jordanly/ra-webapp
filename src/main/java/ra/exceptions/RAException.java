package ra.exceptions;

import org.json.JSONObject;

public class RAException extends Exception {
    private int line;
    private int charPositionInLine;
    private String message;
    private Exception exception;

    public RAException(Exception exception) {
        this.exception = exception;
    }

    public RAException(int line, int charPositionInLine, String message) {
        this.line = line;
        this.charPositionInLine = charPositionInLine;
        this.message = message;
    }

    public RAException(int line, int charPositionInLine, String message, Exception exception) {
        this(line, charPositionInLine, message);
        this.exception = exception;
    }

    public JSONObject asJson() {
        JSONObject obj = new JSONObject();

        obj.put("line", line);
        obj.put("charPositionInLine", charPositionInLine);
        obj.put("message", message);

        return obj;
    }
}
