package ra;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.json.JSONObject;
import ra.exceptions.RAException;
import ra.grammar.RAErrorListener;
import ra.grammar.RAErrorStrategy;
import ra.grammar.RAEvalVisitor;
import ra.grammar.gen.RAGrammarLexer;
import ra.grammar.gen.RAGrammarParser;
import util.ResultSetUtilities;

import javax.xml.transform.Result;
import java.sql.ResultSet;

public class Query {
    private ParseTree tree;
    private RAException exception;
    private ResultSet resultSet;
    private String raQuery;
    private String sqlQuery;

    public Query(RA ra, String raQuery) {
        this.raQuery = raQuery;
        init(ra);
    }

    private void init(RA ra) {
        ANTLRInputStream inputStream = new ANTLRInputStream(raQuery);
        RAGrammarLexer lexer = new RAGrammarLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        RAGrammarParser parser = new RAGrammarParser(tokenStream);

        /**
         * Do not use the default ANTLRErrorStrategy as it attempts to fix
         * user's queries. Instead, just halt on first error.
         */
        parser.setErrorHandler(new RAErrorStrategy());

        /**
         * Remove the default ANTLR listener before adding our own listener. The
         * default ANTLR listener just prints parsing errors to STDERR"
         */
        parser.removeErrorListeners();
        parser.addErrorListener(new RAErrorListener(this));

        this.tree = parser.exp0();
        this.sqlQuery = new RAEvalVisitor(ra, this).visit(tree);

        if (isValid()) {
            try {
                this.resultSet = ra.evaluateSQLQuery(sqlQuery);
            } catch (Exception e) {
                e.printStackTrace(); // TODO write real code
            }
        }
    }

    public boolean isValid() {
        return (exception == null);
    }

    public void setException(RAException e) {
        this.exception = e;
    }

    public String toJson() {
        JSONObject obj = new JSONObject();

        obj.put("query", raQuery);
        obj.put("isError", exception != null);
        if (exception != null) {
            obj.put("error", exception.asJson());
        } else {
            obj.put("columnNames", ResultSetUtilities.columnsToJSONArray(resultSet));
            obj.put("data", ResultSetUtilities.toJSONArray(resultSet));
        }

        return obj.toString(4);
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    @Override
    public String toString() {
        return ResultSetUtilities.asString(resultSet);
    }
}
