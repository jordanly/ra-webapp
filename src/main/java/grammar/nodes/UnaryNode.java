package grammar.nodes;

/**
 * Created by jordanly on 10/6/15.
 */
public class UnaryNode extends ExpressionNode {
    private String command;
    private String operator;
    private ExpressionNode target;

    public UnaryNode(String command, String operator, ExpressionNode target) {
        this.command = command;
        this.operator = operator;
        this.target = target;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public ExpressionNode getTarget() {
        return target;
    }

    public void setTarget(ExpressionNode target) {
        this.target = target;
    }
}
