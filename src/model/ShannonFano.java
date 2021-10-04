package model;

import model.Alfabeto;
import view.ShannonFanoJFrame;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShannonFano {

  
  private ArrayList<Alfabeto> alfabeto = new ArrayList<>();
  
  protected ShannonFanoJFrame frame = null;
  
  private DecimalFormat df = new DecimalFormat("#.#####");
  
  public ShannonFano() {}
  
  public ShannonFano(String s) {
    construirAlfabeto(s);
    Collections.sort(this.alfabeto, new Alfabeto.ordenarAlfabeto());
    balanceTree();
   
  }
  
  public void run(String s) {
    construirAlfabeto(s);
    Collections.sort(this.alfabeto, new Alfabeto.ordenarAlfabeto());
    balanceTree();
  }
  
  private void balanceTree() {
    String root = shannonFanoRootString();
    construirArbol(root);
  }
  
  private void construirArbol(String sfRoot) {
    if (sfRoot.length() > 3) {
      int vLeft = 0;
      int vRight = 0;
      String left = new String();
      String right = new String();
      for (char c : sfRoot.replace(", ", "").toCharArray()) {
        int indexChar = alphabetCharIndex(c);
        if (indexChar != -1)
          if (vLeft <= vRight) {
            vLeft += ((Alfabeto)this.alfabeto.get(indexChar)).getFi();
            left = left.concat(Character.toString(c)).concat(", ");
          } else {
            vRight += ((Alfabeto)this.alfabeto.get(indexChar)).getFi();
            right = right.concat(Character.toString(c)).concat(", ");
          }  
      } 
      left = left.subSequence(0, left.length() - 2).toString();
      right = right.subSequence(0, right.length() - 2).toString();
      updateCodeword(left.replace(", ", ""), '0');
      updateCodeword(right.replace(", ", ""), '1');
      construirArbol(left);
      construirArbol(right);
    } 
  }
  
  private void updateCodeword(String s, char bit) {
    for (char c : s.toCharArray()) {
      int index = alphabetCharIndex(c);
      if (index != -1)
        ((Alfabeto)this.alfabeto.get(index)).setCodeword(((Alfabeto)this.alfabeto.get(index)).getCodeword().concat(Character.toString(bit))); 
    } 
  }
  
  private String shannonFanoRootString() {
    String root = new String();
    int i = 1;
    for (Alfabeto a : this.alfabeto) {
      if (i == this.alfabeto.size()) {
        root = root.concat(Character.toString(a.getSi()));
      } else {
        root = root.concat(Character.toString(a.getSi())).concat(", ");
      } 
      i++;
    } 
    return root;
  }
  
  private void construirAlfabeto(String s) {
    int stringSize = s.length();
      System.out.println("string size"+stringSize);
    for (char c : s.toCharArray()) {
      int index = alphabetCharIndex(c);
      if (index == -1) {
        this.alfabeto.add(new Alfabeto(c, 1, 1.0D / stringSize));
          
      } else {
        int fi = ((Alfabeto)this.alfabeto.get(index)).getFi() + 1;
          System.out.println("fa antes"+fi);
        ((Alfabeto)this.alfabeto.get(index)).setFi(fi);
        System.out.println("fa despues"+fi);
        double pi =(double)fi/(double)stringSize;
        ((Alfabeto)this.alfabeto.get(index)).setPi(pi);
       
      } 
    } 
  }
  
  public String compress(String s) {
    String cod = new String();
    for (char c : s.toCharArray()) {
      int index = alphabetCharIndex(c);
      if (index != -1)
        cod = cod.concat(((Alfabeto)this.alfabeto.get(index)).getCodeword()); 
    } 
    return cod;
  }
  
  public double averageLenght() {
    double avg = 0.0D;
    for (Alfabeto a : this.alfabeto)
      avg += a.getPi() * a.getCodeword().length(); 
    return avg;
  }
  
  public double calcularEntropia() {
    double entropia = 0.0D;
    for (Alfabeto a : this.alfabeto)
      entropia += a.getPi() * log2(1.0D / a.getPi()); 
    return entropia;
  }
  
  private double log2(double n) {
    return Math.log(n) / Math.log(2.0D);
  }
  
  private int alphabetCharIndex(char c) {
    if (this.alfabeto.isEmpty())
      return -1; 
    int index = 0;
    for (Alfabeto a : this.alfabeto) {
      if (c == a.getSi())
        return index; 
      index++;
    } 
    return -1;
  }
  /*Decodifica el mensaje codificado, obtiene el original*/
  public String decodificar(String compress) {
    String aux = new String();
    String word = new String();
    for (int i = 0; i < compress.length(); i++) {
      aux = aux.concat(compress.substring(i, i + 1));
      for (Alfabeto a : this.alfabeto) {
        if (a.getCodeword().equalsIgnoreCase(aux)) {
          word = word.concat(Character.toString(a.getSi()));
          aux = "";
        } 
      } 
    } 
    return word;
  }
  
  public void writeTable() {
    JTable tableInicial = this.frame.getjTableInicial();
    JTable tableFinal = this.frame.getjTableFinal();
    DefaultTableModel tmi = (DefaultTableModel)tableInicial.getModel();
    DefaultTableModel tmf = (DefaultTableModel)tableFinal.getModel();
    for (Alfabeto a : this.alfabeto) {
        System.out.println("asdsadasdasd"+new String (this.df.format(a.getPi())));
      tmi.addRow(new Object[] { new String(Character.toString(a.getSi())), new String(Integer.toString(a.getFi())), new String(this.df.format(a.getPi())) });
      tmf.addRow(new Object[] { new String(Character.toString(a.getSi())), new String(Integer.toString(a.getFi())), new String(this.df.format(a.getPi())), new String(a.getCodeword()), new String(Integer.toString(a.getCodeword().length())) });
    } 
  }
  

  public ArrayList<Alfabeto> getAlfabeto() {
    return this.alfabeto;
  }
  
  public void setFrame(ShannonFanoJFrame sff) {
    this.frame = sff;
  }
}
