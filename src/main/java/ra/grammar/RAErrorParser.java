package ra.grammar;

import org.antlr.v4.runtime.ParserRuleContext;
import ra.Query;
import ra.RA;
import ra.exceptions.RAException;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RAErrorParser {
    private RA ra;

    public static RAError[] UNARY_ERRORS = {
        new RAError("column \"(.*)\" does not exist", "ERROR: Column '%s' does not exists"),
        new RAError("syntax error at or near \"(.*)\"", "ERROR: Syntax error at or near '%s'"),
        new RAError("argument of WHERE must be type boolean", "ERROR: Invalid condition statement")
    };
    public static RAError[] UNIT_ERRORS = {
        new RAError("relation \"(.*)\" does not exist", "ERROR: Table '%s' does not exist")
    };
    public static RAError[] BINARY_ERRORS = {
        new RAError("must have the same number of columns", "ERROR: Each relation must have the same number of columns")
    };

    public RAErrorParser(RA ra) {
        this.ra = ra;
    }

    public boolean validate(Query query, String command, RAError[] rules,
                            ParserRuleContext ctx) {
        if (!query.isValid()) { // If error already occured, skip check
            return false;
        }

        // add SELECT * FROM to command since not all of our nodes are complete
        // sql statements (binary ones for example)
        String formattedCommand = String.format("SELECT * FROM ( %s ) %s",
                command, "validateQueryTempTable");

        try {
            ra.evaluateSQLQuery(formattedCommand);
        } catch (SQLException e) {
            for (RAError error : rules) {
                if (error.check(e.getMessage())) {
                    System.out.println(ctx.getStart() + " " + ctx.getStop());
                    // Error matches
                    query.setException(new RAException(
                            ctx.getStart(),
                            ctx.getStop(),
                            error.printMessage(),
                            e
                    ));

                    return false;
                }
            }

            // If error but we don't know what error it is
            query.setException(new RAException(
                    ctx.getStart(),
                    ctx.getStop(),
                    "UNKNOWN: " + e.getMessage() // TODO fix?
            ));
            return false;
        }

        return true;
    }

    private static class RAError { // TODO find out why i have to make this static
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
