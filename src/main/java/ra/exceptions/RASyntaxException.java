package ra.exceptions;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.json.JSONObject;

/**
 * Created by jordanly on 10/22/15.
 */
public class RASyntaxException extends ParseCancellationException implements RAException {
    private int line;
    private int charPositionInLine;
    private String msg;

    public RASyntaxException(int line, int charPositionInLine, String msg) {
        this.line = line;
        this.charPositionInLine = charPositionInLine;
        this.msg = msg;
    }

    public JSONObject asJson() {
        JSONObject jo = new JSONObject();

        jo.put("line", line);
        jo.put("charPositionInLine", charPositionInLine);
        jo.put("msg", msg);

        return jo;
    }
}
