package multimedia2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShannonFano {
  private BTree shannonFanoTree = new BTree();
  
  private ArrayList<Alphabet> alphabet = new ArrayList<>();
  
  protected ShannonFanoJFrame frame = null;
  
  private DecimalFormat df = new DecimalFormat("#.#####");
  
  public ShannonFano() {}
  
  public ShannonFano(String s) {
    buildAlphabet(s);
    Collections.sort(this.alphabet, new Alphabet.sortAlphabet());
    balanceTree();
    this.shannonFanoTree.orderTree(this.shannonFanoTree.getRootNode());
    this.shannonFanoTree.preOrderTree(this.shannonFanoTree.getRootNode());
  }
  
  public void run(String s) {
    buildAlphabet(s);
    Collections.sort(this.alphabet, new Alphabet.sortAlphabet());
    balanceTree();
    this.shannonFanoTree.orderTree(this.shannonFanoTree.getRootNode());
    this.shannonFanoTree.preOrderTree(this.shannonFanoTree.getRootNode());
  }
  
  private void balanceTree() {
    String root = shannonFanoRootString();
    this.shannonFanoTree.insert(new Node(root));
    buildTree(root);
  }
  
  private void buildTree(String sfRoot) {
    if (sfRoot.length() > 3) {
      int vLeft = 0;
      int vRight = 0;
      String left = new String();
      String right = new String();
      for (char c : sfRoot.replace(", ", "").toCharArray()) {
        int indexChar = alphabetCharIndex(c);
        if (indexChar != -1)
          if (vLeft <= vRight) {
            vLeft += ((Alphabet)this.alphabet.get(indexChar)).getFa();
            left = left.concat(Character.toString(c)).concat(", ");
          } else {
            vRight += ((Alphabet)this.alphabet.get(indexChar)).getFa();
            right = right.concat(Character.toString(c)).concat(", ");
          }  
      } 
      left = left.subSequence(0, left.length() - 2).toString();
      right = right.subSequence(0, right.length() - 2).toString();
      updateCodeword(left.replace(", ", ""), '0');
      updateCodeword(right.replace(", ", ""), '1');
      Node temp = this.shannonFanoTree.search(this.shannonFanoTree.getRootNode(), sfRoot);
      temp.setLeftChild(new Node(left));
      temp.setRightChild(new Node(right));
      buildTree(left);
      buildTree(right);
    } 
  }
  
  private void updateCodeword(String s, char bit) {
    for (char c : s.toCharArray()) {
      int index = alphabetCharIndex(c);
      if (index != -1)
        ((Alphabet)this.alphabet.get(index)).setCodeword(((Alphabet)this.alphabet.get(index)).getCodeword().concat(Character.toString(bit))); 
    } 
  }
  
  private String shannonFanoRootString() {
    String root = new String();
    int i = 1;
    for (Alphabet a : this.alphabet) {
      if (i == this.alphabet.size()) {
        root = root.concat(Character.toString(a.getSi()));
      } else {
        root = root.concat(Character.toString(a.getSi())).concat(", ");
      } 
      i++;
    } 
    return root;
  }
  
  private void buildAlphabet(String s) {
    int stringSize = s.length();
      System.out.println("string size"+stringSize);
    for (char c : s.toCharArray()) {
      int index = alphabetCharIndex(c);
      if (index == -1) {
        this.alphabet.add(new Alphabet(c, 1, 1.0D / stringSize));
          
      } else {
        int fa = ((Alphabet)this.alphabet.get(index)).getFa() + 1;
          System.out.println("fa antes"+fa);
        ((Alphabet)this.alphabet.get(index)).setFa(fa);
        System.out.println("fa despues"+fa);
        double pi =(double)fa/(double)stringSize;
        ((Alphabet)this.alphabet.get(index)).setPi(pi);
       
      } 
    } 
  }
  
  public String compress(String s) {
    String cod = new String();
    for (char c : s.toCharArray()) {
      int index = alphabetCharIndex(c);
      if (index != -1)
        cod = cod.concat(((Alphabet)this.alphabet.get(index)).getCodeword()); 
    } 
    return cod;
  }
  
  public double averageLenght() {
    double avg = 0.0D;
    for (Alphabet a : this.alphabet)
      avg += a.getPi() * a.getCodeword().length(); 
    return avg;
  }
  
  public double entropy() {
    double entropy = 0.0D;
    for (Alphabet a : this.alphabet)
      entropy += a.getPi() * log2(1.0D / a.getPi()); 
    return entropy;
  }
  
  private double log2(double n) {
    return Math.log(n) / Math.log(2.0D);
  }
  
  private int alphabetCharIndex(char c) {
    if (this.alphabet.isEmpty())
      return -1; 
    int index = 0;
    for (Alphabet a : this.alphabet) {
      if (c == a.getSi())
        return index; 
      index++;
    } 
    return -1;
  }
  
  public String decode(String compress) {
    String aux = new String();
    String word = new String();
    for (int i = 0; i < compress.length(); i++) {
      aux = aux.concat(compress.substring(i, i + 1));
      for (Alphabet a : this.alphabet) {
        if (a.getCodeword().equalsIgnoreCase(aux)) {
          word = word.concat(Character.toString(a.getSi()));
          aux = "";
        } 
      } 
    } 
    return word;
  }
  
  public void writeTable() {
    JTable tableInitial = this.frame.getjTableInitial();
    JTable tableFinal = this.frame.getjTableFinal();
    DefaultTableModel tmi = (DefaultTableModel)tableInitial.getModel();
    DefaultTableModel tmf = (DefaultTableModel)tableFinal.getModel();
    for (Alphabet a : this.alphabet) {
        System.out.println("asdsadasdasd"+new String (this.df.format(a.getPi())));
      tmi.addRow(new Object[] { new String(Character.toString(a.getSi())), new String(Integer.toString(a.getFa())), new String(this.df.format(a.getPi())) });
      tmf.addRow(new Object[] { new String(Character.toString(a.getSi())), new String(Integer.toString(a.getFa())), new String(this.df.format(a.getPi())), new String(a.getCodeword()), new String(Integer.toString(a.getCodeword().length())) });
    } 
  }
  
  public BTree getShannonFanoTree() {
    return this.shannonFanoTree;
  }
  
  public ArrayList<Alphabet> getAlphabet() {
    return this.alphabet;
  }
  
  public void setFrame(ShannonFanoJFrame sff) {
    this.frame = sff;
  }
}
