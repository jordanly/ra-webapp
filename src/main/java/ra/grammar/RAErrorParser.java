package ra.grammar;

import ra.Query;
import ra.RA;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jordanly on 10/25/15.
 */
public class RAErrorParser {
    private RA ra;
    private RAError[] errors;

    public RAErrorParser(RA ra) {
        this.ra = ra;
        init();
    }

    private void init() {
        errors = new RAError[]{
                new RAError(Pattern.compile("column \"(.*)\" does not exist"))
        };
    }

    public boolean validate(Query query, String command) {
        try {
            ra.evaluateSQLQuery(command);
        } catch (SQLException e) {
            for (RAError err : errors) {
                Matcher m = err.check(e.getMessage());
                if (m != null) {
                    // TODO
                }
            }

            return false;
        }

        return true;
    }

    private class RAError {
        private Pattern pattern;
        private Matcher matcher;
        // what exception to create? message?

        public RAError(Pattern pattern) {
            this.pattern = pattern;
        }

        public Matcher check(String s) {
            this.matcher = pattern.matcher(s);
            return matcher;
        }
    }
}
