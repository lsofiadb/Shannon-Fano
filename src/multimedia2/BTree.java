package multimedia2;

import java.util.ArrayList;

public class BTree {
  private Node rootNode;
  
  private Float id = Float.valueOf(0.0F);
  
  private ArrayList<Node> node = new ArrayList<>();
  
  public BTree() {
    this.rootNode = null;
  }
  
  public void insert(Node newNode) {
    if (this.rootNode == null)
      this.rootNode = newNode; 
  }
  
  public Node getRootNode() {
    if (this.rootNode != null)
      return this.rootNode; 
    return null;
  }
  
  public Node search(Node rootNode, String s) {
    Node temp = null;
    if (rootNode == null)
      return null; 
    if (rootNode.getSymbol().compareTo(s) == 0)
      return rootNode; 
    temp = search(rootNode.getLeftChild(), s);
    if (temp == null)
      return search(rootNode.getRightChild(), s); 
    return temp;
  }
  
  public void orderTree(Node rootNode) {
    if (rootNode != null) {
      orderTree(rootNode.getLeftChild());
      rootNode.setId(this.id);
      this.id = Float.valueOf(this.id.floatValue() + 0.1F);
      orderTree(rootNode.getRightChild());
    } 
  }
  
  public void preOrderTree(Node rootNode) {
    if (rootNode != null) {
      this.node.add(rootNode);
      preOrderTree(rootNode.getLeftChild());
      preOrderTree(rootNode.getRightChild());
    } 
  }
  
  public ArrayList<Node> getArrayNode() {
    return this.node;
  }
}
