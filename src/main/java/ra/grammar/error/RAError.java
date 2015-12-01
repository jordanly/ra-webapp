package ra.grammar.error;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jordanly on 11/29/15.
 */
public class RAError {
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
        return matcher.find();
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
