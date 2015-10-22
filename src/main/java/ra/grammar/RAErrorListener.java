package ra.grammar;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import ra.exceptions.RASyntaxException;

/**
 * Created by jordanly on 10/18/15.
 */
public class RAErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine, String msg, RecognitionException e)
            throws RASyntaxException {
        throw new RASyntaxException(line, charPositionInLine, msg);
    }
}
