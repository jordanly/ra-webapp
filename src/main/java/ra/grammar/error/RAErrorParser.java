package ra.grammar.error;

import org.antlr.v4.runtime.ParserRuleContext;
import ra.Query;
import ra.RA;
import ra.exceptions.RAException;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RAErrorParser {
    public static RAError[] UNARY_ERRORS = {
        new RAError("column \"(.*)\" does not exist", "ERROR: Column '%s' does not exists"),
        new RAError("syntax error at or near \"(.*)\"", "ERROR: Syntax error at or near '%s'"),
        new RAError("argument of WHERE must be type boolean", "ERROR: Invalid condition statement"),
        new RAError("unterminated quoted string", "ERROR: Unterminated quoted string")
    };
    public static RAError[] UNIT_ERRORS = {
        new RAError("column \"(.*)\" does not exist", "ERROR: Column '%s' does not exists"),
        new RAError("relation \"(.*)\" does not exist", "ERROR: Table '%s' does not exist")
    };
    public static RAError[] BINARY_ERRORS = {
        new RAError("must have the same number of columns", "ERROR: Each relation must have the same number of columns")
    };
    public static String COLUMN_NAME_REGEX = "^[a-zA-Z_][a-zA-Z0-9_]*$";
    public static String CONDITION_REGEX = "^\\(*(not)*\\s*[a-zA-Z_][a-zA-Z0-9_]*\\s*(<|<=|=|>=|>|<>|like)\\s*('[^']*'|[^\\s]*)\\)*$";

    private RA ra;

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
                   // Error matches
                    query.setException(new RAException(
                            ctx.getStart(),
                            ctx.getStop(),
                            error.printMessage()
                    ));

                    return false;
                }
            }

            // Log it as well
            System.err.println(
                "UNKNOWN: Did not recognize this error, contact an administrator -- STACK TRACK "
                    + e.getMessage()
                    + " RA QUERY: "
                    + query.toString()
            );

            // If error but we don't know what error it is
            query.setException(new RAException(
                    ctx.getStart(),
                    ctx.getStop(),
                    "UNKNOWN: Did not recognize this error, contact an administrator -- STACK TRACK "
                            + e.getMessage()
            ));
            return false;
        }

        return true;
    }

    public boolean validateOperatorOption(Query query, String val, String operation,
                                          ParserRuleContext ctx) {
        switch (operation) {
            case "\\select": case "\\join":
                String[] conditions = val.split("\\s+(and|or)\\s+");
                for (String c : conditions) {
                    if (!c.trim().matches(CONDITION_REGEX)) {
                        query.setException(new RAException(
                                ctx.getStart(),
                                ctx.getStop(),
                                String.format("ERROR: Bad operator option: '%s' -- Bad condition: '%s'",
                                        val, c)
                        ));

                        return false;
                    }
                }
                break;
            case "\\project": case "\\rename": // Same set of logic for both
                String[] columns = val.split(",");
                for (String c : columns) {
                    if (!c.trim().matches(COLUMN_NAME_REGEX)) {
                        query.setException(new RAException(
                                ctx.getStart(),
                                ctx.getStop(),
                                String.format("ERROR: Bad operator option: '%s' -- Bad column name: 'c'",
                                        val, c)
                        ));

                        return false;
                    }
                }
                break;
        }

        return true;
    }
}
