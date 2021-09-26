package multimedia2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.introcs.StdOut;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MM2BST<Key extends Comparable<Key>, Value> {
  private Node root;
  
  private Huffman hf = null;
  
  private ShannonFano sf = null;
  
  private class Node {
    private Key key;
    
    private Value val;
    
    private Node left;
    
    private Node right;
    
    private int N;
    
    public Node(Key key, Value val, int N) {
      this.key = key;
      this.val = val;
      this.N = N;
    }
  }
  
  public boolean isEmpty() {
    return (size() == 0);
  }
  
  public int size() {
    return size(this.root);
  }
  
  private int size(Node x) {
    if (x == null)
      return 0; 
    return x.N;
  }
  
  public boolean contains(Key key) {
    return (get(key) != null);
  }
  
  public Value get(Key key) {
    return get(this.root, key);
  }
  
  private Value get(Node x, Key key) {
    if (x == null)
      return null; 
    int cmp = key.compareTo(x.key);
    if (cmp < 0)
      return get(x.left, key); 
    if (cmp > 0)
      return get(x.right, key); 
    return x.val;
  }
  
  public void put(Key key, Value val) {
    if (val == null) {
      delete(key);
      return;
    } 
    this.root = put(this.root, key, val);
    assert check();
  }
  
  private Node put(Node x, Key key, Value val) {
    if (x == null)
      return new Node(key, val, 1); 
    int cmp = key.compareTo(x.key);
    if (cmp < 0) {
      x.left = put(x.left, key, val);
    } else if (cmp > 0) {
      x.right = put(x.right, key, val);
    } else {
      x.val = val;
    } 
    x.N = 1 + size(x.left) + size(x.right);
    return x;
  }
  
  public void deleteMin() {
    if (isEmpty())
      throw new NoSuchElementException("Symbol table underflow"); 
    this.root = deleteMin(this.root);
    assert check();
  }
  
  private Node deleteMin(Node x) {
    if (x.left == null)
      return x.right; 
    x.left = deleteMin(x.left);
    x.N = size(x.left) + size(x.right) + 1;
    return x;
  }
  
  public void deleteMax() {
    if (isEmpty())
      throw new NoSuchElementException("Symbol table underflow"); 
    this.root = deleteMax(this.root);
    assert check();
  }
  
  private Node deleteMax(Node x) {
    if (x.right == null)
      return x.left; 
    x.right = deleteMax(x.right);
    x.N = size(x.left) + size(x.right) + 1;
    return x;
  }
  
  public void delete(Key key) {
    this.root = delete(this.root, key);
    assert check();
  }
  
  private Node delete(Node x, Key key) {
    if (x == null)
      return null; 
    int cmp = key.compareTo(x.key);
    if (cmp < 0) {
      x.left = delete(x.left, key);
    } else if (cmp > 0) {
      x.right = delete(x.right, key);
    } else {
      if (x.right == null)
        return x.left; 
      if (x.left == null)
        return x.right; 
      Node t = x;
      x = min(t.right);
      x.right = deleteMin(t.right);
      x.left = t.left;
    } 
    x.N = size(x.left) + size(x.right) + 1;
    return x;
  }
  
  public Key min() {
    if (isEmpty())
      return null; 
    return (min(this.root)).key;
  }
  
  private Node min(Node x) {
    if (x.left == null)
      return x; 
    return min(x.left);
  }
  
  public Key max() {
    if (isEmpty())
      return null; 
    return (max(this.root)).key;
  }
  
  private Node max(Node x) {
    if (x.right == null)
      return x; 
    return max(x.right);
  }
  
  public Key floor(Key key) {
    Node x = floor(this.root, key);
    if (x == null)
      return null; 
    return x.key;
  }
  
  private Node floor(Node x, Key key) {
    if (x == null)
      return null; 
    int cmp = key.compareTo(x.key);
    if (cmp == 0)
      return x; 
    if (cmp < 0)
      return floor(x.left, key); 
    Node t = floor(x.right, key);
    if (t != null)
      return t; 
    return x;
  }
  
  public Key ceiling(Key key) {
    Node x = ceiling(this.root, key);
    if (x == null)
      return null; 
    return x.key;
  }
  
  private Node ceiling(Node x, Key key) {
    if (x == null)
      return null; 
    int cmp = key.compareTo(x.key);
    if (cmp == 0)
      return x; 
    if (cmp < 0) {
      Node t = ceiling(x.left, key);
      if (t != null)
        return t; 
      return x;
    } 
    return ceiling(x.right, key);
  }
  
  public Key select(int k) {
    if (k < 0 || k >= size())
      return null; 
    Node x = select(this.root, k);
    return x.key;
  }
  
  private Node select(Node x, int k) {
    if (x == null)
      return null; 
    int t = size(x.left);
    if (t > k)
      return select(x.left, k); 
    if (t < k)
      return select(x.right, k - t - 1); 
    return x;
  }
  
  public int rank(Key key) {
    return rank(key, this.root);
  }
  
  private int rank(Key key, Node x) {
    if (x == null)
      return 0; 
    int cmp = key.compareTo(x.key);
    if (cmp < 0)
      return rank(key, x.left); 
    if (cmp > 0)
      return 1 + size(x.left) + rank(key, x.right); 
    return size(x.left);
  }
  
  public Iterable<Key> keys() {
    return keys(min(), max());
  }
  
  public Iterable<Key> keys(Key lo, Key hi) {
    Queue<Key> queue = new Queue();
    keys(this.root, queue, lo, hi);
    return (Iterable<Key>)queue;
  }
  
  private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
    if (x == null)
      return; 
    int cmplo = lo.compareTo(x.key);
    int cmphi = hi.compareTo(x.key);
    if (cmplo < 0)
      keys(x.left, queue, lo, hi); 
    if (cmplo <= 0 && cmphi >= 0)
      queue.enqueue(x.key); 
    if (cmphi > 0)
      keys(x.right, queue, lo, hi); 
  }
  
  public int size(Key lo, Key hi) {
    if (lo.compareTo(hi) > 0)
      return 0; 
    if (contains(hi))
      return rank(hi) - rank(lo) + 1; 
    return rank(hi) - rank(lo);
  }
  
  public int height() {
    return height(this.root);
  }
  
  private int height(Node x) {
    if (x == null)
      return -1; 
    return 1 + Math.max(height(x.left), height(x.right));
  }
  
  public Iterable<Key> levelOrder() {
    Queue<Key> keys = new Queue();
    Queue<Node> queue = new Queue();
    queue.enqueue(this.root);
    while (!queue.isEmpty()) {
      Node x = (Node)queue.dequeue();
      if (x == null)
        continue; 
      keys.enqueue(x.key);
      queue.enqueue(x.left);
      queue.enqueue(x.right);
    } 
    return (Iterable<Key>)keys;
  }
  
  private boolean check() {
    if (!isBST())
      StdOut.println("Not in symmetric order"); 
    if (!isSizeConsistent())
      StdOut.println("Subtree counts not consistent"); 
    if (!isRankConsistent())
      StdOut.println("Ranks not consistent"); 
    return (isBST() && isSizeConsistent() && isRankConsistent());
  }
  
  private boolean isBST() {
    return isBST(this.root, null, null);
  }
  
  private boolean isBST(Node x, Key min, Key max) {
    if (x == null)
      return true; 
    if (min != null && x.key.compareTo(min) <= 0)
      return false; 
    if (max != null && x.key.compareTo(max) >= 0)
      return false; 
    return (isBST(x.left, min, x.key) && isBST(x.right, x.key, max));
  }
  
  private boolean isSizeConsistent() {
    return isSizeConsistent(this.root);
  }
  
  private boolean isSizeConsistent(Node x) {
    if (x == null)
      return true; 
    if (x.N != size(x.left) + size(x.right) + 1)
      return false; 
    return (isSizeConsistent(x.left) && isSizeConsistent(x.right));
  }
  
  private boolean isRankConsistent() {
    for (int i = 0; i < size(); i++) {
      if (i != rank(select(i)))
        return false; 
    } 
    for (Comparable<Key> comparable : keys()) {
      if (comparable.compareTo(select(rank((Key)comparable))) != 0)
        return false; 
    } 
    return true;
  }
  
  public void drawHuffmanTree(MM2BST<String, Integer> st) throws InterruptedException {
    ArrayList<multimedia2.Node> nodes = this.hf.getHuffmanTree().getArrayNode();
    int i = 0;
    for (multimedia2.Node n : nodes) {
      i++;
      String s = Float.toString(n.getId().floatValue()).concat(" ");
      st.put(s.concat(n.getSymbol()), Integer.valueOf(i));
    } 
    drawTree(st);
  }
  
  public void drawShannonFanoTree(MM2BST<String, Integer> st) throws InterruptedException {
    ArrayList<multimedia2.Node> nodes = this.sf.getShannonFanoTree().getArrayNode();
    int i = 0;
    for (multimedia2.Node n : nodes) {
      i++;
      String s = Float.toString(n.getId().floatValue()).concat(" ");
      st.put(s.concat(n.getSymbol()), Integer.valueOf(i));
    } 
    drawTree(st);
  }
  
  public float[] position(Key key, float max_width) {
    float[] node_pos = { 0.0F, 0.0F, max_width, -1.0F, -1.0F, 0.0F };
    return position(this.root, key, node_pos);
  }
  
  private float[] position(Node x, Key key, float[] node_pos) {
    if (x == null)
      return null; 
    int cmp = key.compareTo(x.key);
    if (cmp < 0) {
      node_pos[5] = -1.0F;
      node_pos[3] = node_pos[1];
      node_pos[4] = node_pos[2];
      node_pos[0] = node_pos[0] + 1.0F;
      node_pos[2] = node_pos[1] + (node_pos[2] - node_pos[1]) / 2.0F;
      return position(x.left, key, node_pos);
    } 
    if (cmp > 0) {
      node_pos[5] = 1.0F;
      node_pos[3] = node_pos[1];
      node_pos[4] = node_pos[2];
      node_pos[0] = node_pos[0] + 1.0F;
      node_pos[1] = node_pos[1] + (node_pos[2] - node_pos[1]) / 2.0F;
      return position(x.right, key, node_pos);
    } 
    return node_pos;
  }
  
  private static void printLevelFormatTree(MM2BST<String, Integer> st) {
    int level = 0;
    System.out.println("-----------------------------------------------------------------------");
    System.out.println("Tree:");
    int prev_level = 0, prev_x_node_pos = 0;
    for (String s : st.levelOrder()) {
      float[] position = st.position(s, 80.0F);
      level = (int)position[0];
      int x_node_pos = (int)((position[2] + position[1]) / 2.0F);
      if (level > prev_level) {
        System.out.println();
        System.out.println();
        prev_level = level;
        prev_x_node_pos = 0;
      } 
      for (int i = prev_x_node_pos; i < x_node_pos; i++)
        System.out.print(" "); 
      System.out.print(s.substring(s.indexOf(" ")));
      prev_x_node_pos = x_node_pos;
    } 
    System.out.println("\n-----------------------------------------------------------------------");
  }
  
  private static void drawTree(MM2BST<String, Integer> st) throws InterruptedException {
    double raio = 0.025D;
    float h_factor = (2 * (1 + st.height()));
    MMStdDraw.setCanvasSize(1280, 650);
    MMStdDraw.setFont(MMStdDraw.getFont().deriveFont(12.0F));
    for (String s : st.levelOrder()) {
      float[] position = st.position(s, 1.0F);
      float y_node_pos = position[0] / h_factor;
      float x_node_pos = (position[2] + position[1]) / 2.0F;
      float y_parent_nodes_pos = (position[0] - 1.0F) / h_factor;
      float x_parent_node_pos = (position[4] + position[3]) / 2.0F;
      if (x_parent_node_pos >= 0.0F) {
        MMStdDraw.setPenColor(MMStdDraw.BLACK);
        MMStdDraw.line(x_node_pos, (1.0F - y_node_pos), x_parent_node_pos, (1.0F - y_parent_nodes_pos) - raio);
      } 
      MMStdDraw.setPenColor(MMStdDraw.LIGHT_GRAY);
      MMStdDraw.filledCircle(x_node_pos, (1.0F - y_node_pos), raio);
      MMStdDraw.setPenColor(MMStdDraw.BLACK);
      MMStdDraw.text(x_node_pos, (1.0F - y_node_pos), s.substring(s.indexOf(" ")));
    } 
  }
  
  public void clearWindow() {
    MMStdDraw.getFrame().setVisible(false);
  }
  
  public void setHuffman(Huffman hf) {
    this.hf = hf;
  }
  
  public void setShannonFano(ShannonFano sf) {
    this.sf = sf;
  }
}
