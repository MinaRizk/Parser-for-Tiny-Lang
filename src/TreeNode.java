import java.util.LinkedList;

public class TreeNode {

    private TreeNode parent;
    private LinkedList<TreeNode> children = new LinkedList<TreeNode>();
    private String name = new String("null");


    public TreeNode(){}
    public TreeNode(String s) {
        name = s;
    }
    public void setChild(TreeNode s) {
        children.addLast(s);
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public LinkedList<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<TreeNode> children) {
        this.children = children;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
