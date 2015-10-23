package ra;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.json.JSONObject;
import ra.exceptions.RAException;
import ra.exceptions.RASyntaxException;
import ra.grammar.RAErrorListener;
import ra.grammar.RAEvalVisitor;
import ra.grammar.gen.RAGrammarLexer;
import ra.grammar.gen.RAGrammarParser;
import util.ResultSetUtilities;

import java.sql.ResultSet;

public class Query {
    private String raQuery;
    private String sqlQuery;
    private ParseTree tree;
    private ResultSet resultSet;

    private RAException exception;

    // Every query begins with an RA query string
    public Query(RA ra, String raQuery) {
        this.raQuery = raQuery;
        init(ra);
    }

    private void init(RA ra) {
        ANTLRInputStream inputStream = new ANTLRInputStream(raQuery);
        RAGrammarLexer lexer = new RAGrammarLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        RAGrammarParser parser = new RAGrammarParser(tokenStream);
        parser.addErrorListener(new RAErrorListener());
        // TODO remove default listener (which just prints to stderr)

        try {
            this.tree = parser.exp0();
            this.sqlQuery = new RAEvalVisitor(ra, this).visit(tree);
            this.resultSet = ra.evaluateSQLQuery(sqlQuery);
        } catch (RASyntaxException rase) {
            exception = rase;
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
