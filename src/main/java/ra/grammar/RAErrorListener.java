package ra.grammar;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import ra.Query;
import ra.exceptions.RAException;

/**
 * Created by jordanly on 10/18/15.
 */
public class RAErrorListener extends BaseErrorListener {
    private Query query;

    public RAErrorListener(Query query) {
        this.query = query;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine, String msg, RecognitionException e) {
        String errorMsg = String.format("ERROR: unable to parse command at '%s' (line %s, position %s)",
                e.getOffendingToken().getText(), e.getOffendingToken().getLine(),
                e.getOffendingToken().getCharPositionInLine());

        query.setException(
                new RAException(e.getOffendingToken(), e.getOffendingToken(), errorMsg, e)
        );
    }
}
