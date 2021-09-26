package multimedia2;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Huffman implements Serializable {
  private BTree huffmanTree = new BTree();
  
  private ArrayList<Alphabet> alphabet = new ArrayList<>();
  
  private ArrayList<String> nodes = new ArrayList<>();
  
  protected transient HuffmanJFrame frame = null;
  
  private DecimalFormat df = new DecimalFormat("#.#####");
  
  public Huffman() {}
  
  public Huffman(String s) {
    buildAlphabet(s);
    Collections.sort(this.alphabet, new Alphabet.sortAlphabet());
    String root = buildHuffmanRootString();
    buildHuffmanTree(root);
    this.huffmanTree.orderTree(this.huffmanTree.getRootNode());
    this.huffmanTree.preOrderTree(this.huffmanTree.getRootNode());
  }
  
  public void run(String s) {
    buildAlphabet(s);
    Collections.sort(this.alphabet, new Alphabet.sortAlphabet());
    String root = buildHuffmanRootString();
    buildHuffmanTree(root);
    this.huffmanTree.orderTree(this.huffmanTree.getRootNode());
    this.huffmanTree.preOrderTree(this.huffmanTree.getRootNode());
  }
  
  private void buildHuffmanTree(String rootNode) {
    int indexArray = this.nodes.size() - 1;
    this.nodes.remove(indexArray);
    this.huffmanTree.insert(new Node(rootNode));
    while (this.nodes.size() > 0) {
      indexArray = this.nodes.size() - 1;
      String s = "(".concat(this.nodes.get(indexArray)).concat(",").concat(this.nodes.get(indexArray - 1)).concat(")");
      Node temp = this.huffmanTree.search(this.huffmanTree.getRootNode(), s);
      temp.setLeftChild(new Node(this.nodes.get(indexArray)));
      temp.setRightChild(new Node(this.nodes.get(indexArray - 1)));
      this.nodes.remove(indexArray);
      this.nodes.remove(indexArray - 1);
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
  
  public String buildHuffmanRootString() {
    ArrayList<Table> table = new ArrayList<>();
    buildInitialTable(table);
    JTable tableInt = this.frame.getjTableIntermedia();
    DefaultTableModel tm = (DefaultTableModel)tableInt.getModel();
    Integer i = Integer.valueOf(1);
    while (table.size() > 1) {
      String str = new String("Intermediate Table ".concat(Integer.toString(i.intValue())));
      tm.addRow(new Object[] { new String(str), new String("") });
      processTable(table);
      Collections.sort(table, new Table.sortTable());
      Integer integer1 = i, integer2 = i = Integer.valueOf(i.intValue() + 1);
    } 
    String aux = new String("Intermediate Table ".concat(Integer.toString(i.intValue())));
    tm.addRow(new Object[] { new String(aux), new String("") });
    tm.addRow(new Object[] { new String(((Table)table.get(0)).getSi()), new String(this.df.format(((Table)table.get(0)).getPi())) });
    this.nodes.add(((Table)table.get(0)).getSi());
    return ((Table)table.get(0)).getSi();
  }
  
  private void processTable(ArrayList<Table> t) {
    int index = t.size() - 1;
    double pi = ((Table)t.get(index)).getPi();
    String c = ((Table)t.get(index)).getSi();
    String cAux = ((Table)t.get(index)).getAux();
    double piAnt = ((Table)t.get(index - 1)).getPi();
    String cAnt = ((Table)t.get(index - 1)).getSi();
    String cAntAux = ((Table)t.get(index - 1)).getAux();
    JTable tableInt = this.frame.getjTableIntermedia();
    DefaultTableModel tm = (DefaultTableModel)tableInt.getModel();
    for (Table temp : t) {
      tm.addRow(new Object[] { new String(temp.getSi()), new String(this.df.format(temp.getPi())) });
    } 
    tm.addRow(new Object[] { new String(), new String() });
    tm.addRow(new Object[] { new String(), new String() });
    this.nodes.add(c);
    this.nodes.add(cAnt);
    ((Table)t.get(index - 1)).setPi(piAnt + pi);
    ((Table)t.get(index - 1)).setSi("(".concat(cAnt).concat(",").concat(c).concat(")"));
    ((Table)t.get(index - 1)).setAux(cAntAux.concat(cAux));
    t.remove(index);
    updateCodeword(cAntAux, '0');
    updateCodeword(cAux, '1');
  }
  
  private void updateCodeword(String s, char bit) {
    for (char c : s.toCharArray()) {
      int index = alphabetCharIndex(c);
      if (index != -1)
        ((Alphabet)this.alphabet.get(index)).setCodeword(Character.toString(bit).concat(((Alphabet)this.alphabet.get(index)).getCodeword())); 
    } 
  }
  
  private void buildInitialTable(ArrayList<Table> t) {
    for (Alphabet a : this.alphabet)
      t.add(new Table(Character.toString(a.getSi()), a.getPi(), Character.toString(a.getSi()))); 
  }
  
  private void buildAlphabet(String s) {
    int stringSize = s.length();
    for (char c : s.toCharArray()) {
      int index = alphabetCharIndex(c);
      if (index == -1) {
        this.alphabet.add(new Alphabet(c, 1, 1.0D / stringSize));
      } else {
        int fa = ((Alphabet)this.alphabet.get(index)).getFa() + 1;
        ((Alphabet)this.alphabet.get(index)).setFa(fa);
        ((Alphabet)this.alphabet.get(index)).setPi(fa / stringSize);
      } 
    } 
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
  
  public void printAlphabet() {
    JTable tableFinal = this.frame.getjTableFinal();
    DefaultTableModel tm = (DefaultTableModel)tableFinal.getModel();
    for (Alphabet a : this.alphabet) {
      tm.addRow(new Object[] { new String(Character.toString(a.getSi())), new String(Integer.toString(a.getFa())), new String(this.df.format(a.getPi())), new String(a.getCodeword()), new String(Integer.toString(a.getCodeword().length())) });
    } 
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
  
  public BTree getHuffmanTree() {
    return this.huffmanTree;
  }
  
  public void setFrame(HuffmanJFrame frame) {
    this.frame = frame;
  }
  
  public ArrayList<Alphabet> getAlphabet() {
    return this.alphabet;
  }
}
