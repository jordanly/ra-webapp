package ra.grammar;

import ra.Query;
import ra.RA;
import ra.exceptions.RAException;
import ra.grammar.gen.RAGrammarParser;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jordanly on 10/25/15.
 */
public class RAErrorParser {
    private RA ra;
    private RAError[] UNARY_ERRORS = {
        new RAError("column \"(.*)\" does not exist", "ERROR: Column %s does not exists"),
    };

    public RAErrorParser(RA ra) {
        this.ra = ra;
    }

    public boolean validateUnaryExp(Query query, String command,
                                    RAGrammarParser.UnaryExpContext ctx) {
        try {
            ra.evaluateSQLQuery(command);
        } catch (SQLException e) {
            for (RAError error : UNARY_ERRORS) {
                if (error.check(e.getMessage())) {
                    // Error matches
                    query.setException(new RAException(
                            -1,
                            -1,
                            error.printMessage(),
                            e
                    ));
                    return false;
                }
            }

            // No error exists
            // TODO return error where we don't know what's going on
        }

        return true;
    }

    private class RAError {
        private Pattern pattern;
        private Matcher matcher;
        private String message;
        // what exception to create? message?

        public RAError(String pattern, String message) {
            this.pattern = Pattern.compile(pattern);
            this.message = message;
        }

        public boolean check(String s) {
            this.matcher = pattern.matcher(s);
            if (matcher.find()) {
                return true;
            }
            return false;
        }

        public String printMessage() {
            if (matcher == null) {
                throw new RuntimeException("Tried to print error message without a match");
            }

            String[] groups = new String[matcher.groupCount()];
            for (int i = 0; i < groups.length; i++) {
                groups[i] = matcher.group(i + 1); // + 1 since group 0 is full string
            }

            return String.format(message, groups);
        }
    }
}
