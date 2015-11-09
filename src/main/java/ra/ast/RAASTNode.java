package ra.ast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordanly on 11/9/15.
 */
public class RAASTNode {
    public String operation;
    public String subscript;
    public List<RAASTNode> children;

    public RAASTNode(String operation, String subscript) {
        this.operation = operation;
        this.subscript = subscript;
        this.children = new ArrayList<>();
    }

    public void addChild(RAASTNode node) {
        children.add(node);
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();

        obj.put("name", operation);
        obj.put("subscript", subscript);

        JSONArray childArr = new JSONArray();
        for (RAASTNode c : children) {
            childArr.put(c.toJson());
        }
        obj.put("children", childArr);

        return obj;
    }
}
