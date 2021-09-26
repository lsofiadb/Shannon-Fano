package multimedia2;

public class Node {
  private Node leftChild;
  
  private Node rightChild;
  
  private String symbol;
  
  private Float id;
  
  public Node(String symbol) {
    this.symbol = symbol;
    this.leftChild = null;
    this.rightChild = null;
    this.id = Float.valueOf(-1.0F);
  }
  
  public String getSymbol() {
    return this.symbol;
  }
  
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
  
  public Node getLeftChild() {
    return this.leftChild;
  }
  
  public Node getRightChild() {
    return this.rightChild;
  }
  
  public void setLeftChild(Node leftChild) {
    this.leftChild = leftChild;
  }
  
  public void setRightChild(Node rightChild) {
    this.rightChild = rightChild;
  }
  
  public Float getId() {
    return this.id;
  }
  
  public void setId(Float id) {
    this.id = id;
  }
}
