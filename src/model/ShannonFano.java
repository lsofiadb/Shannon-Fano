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
  protected ShannonFanoJFrame jFrameShannonFano = null;
  private DecimalFormat df = new DecimalFormat("#.#####");
  
  /*Constructor*/
  public ShannonFano() {}
  
  /*Construye el alfabeto, lo ordena, y  balancea el arbol*/
  public void run(String mensaje) {
    construirAlfabeto(mensaje);
    Collections.sort(this.alfabeto, new Alfabeto.ordenarAlfabeto());
    balancearArbol();
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
  
  /*Construye el alfabeto de acuerdo al mensaje ingresado*/
  private void construirAlfabeto(String mensaje) {
    int stringSize = mensaje.length();
    for (char c : mensaje.toCharArray()) {
      int index = alphabetCharIndex(c);
      if (index == -1) {
        this.alfabeto.add(new Alfabeto(c, 1, 1.0D / stringSize));
          
      } else {
        int fi = ((Alfabeto)this.alfabeto.get(index)).getFi() + 1;
        ((Alfabeto)this.alfabeto.get(index)).setFi(fi);
         double pi =(double)fi/(double)stringSize;
        ((Alfabeto)this.alfabeto.get(index)).setPi(pi);
       
      } 
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
  
  /*A partir de la raiz balancea el arbol*/
  private void balancearArbol() {
    String root = shannonFanoRootString();
    construirArbol(root);
  }
  
  /*Al construir el arbol se asigna a las ramas 0 a la izquierda y 1 a la derecha*/
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
  
  /*Realiza la codificacion del mensaje*/
  public String compress(String s) {
    String codificacion = new String();
    for (char c : s.toCharArray()) {
      int index = alphabetCharIndex(c);
      if (index != -1)
        codificacion = codificacion.concat(((Alfabeto)this.alfabeto.get(index)).getCodeword()); 
    } 
    return codificacion;
  }
  
  /*---------Variables del JPanel Intermedio de los resultados---------*/
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
  
  /*-----------------------------------------------------------------*/
  
  /*Decodifica el mensaje codificado, obtiene el original*/
  public String decodificar(String compress) {
    String aux = new String();
    String mensajeOriginal = new String();
    for (int i = 0; i < compress.length(); i++) {
      aux = aux.concat(compress.substring(i, i + 1));
      for (Alfabeto a : this.alfabeto) {
        if (a.getCodeword().equalsIgnoreCase(aux)) {
          mensajeOriginal = mensajeOriginal.concat(Character.toString(a.getSi()));
          aux = "";
        } 
      } 
    } 
    return mensajeOriginal;
  }
  
  /*Asigna los valores a los elementos de las tablas del JFrame*/
  public void writeTable() {
    JTable tableInicial = this.jFrameShannonFano.getjTableInicial();
    JTable tableFinal = this.jFrameShannonFano.getjTableFinal();
    DefaultTableModel tmi = (DefaultTableModel)tableInicial.getModel();
    DefaultTableModel tmf = (DefaultTableModel)tableFinal.getModel();
    for (Alfabeto a : this.alfabeto) {
      tmi.addRow(new Object[] { new String(Character.toString(a.getSi())), new String(Integer.toString(a.getFi())), new String(this.df.format(a.getPi())) });
      tmf.addRow(new Object[] { new String(Character.toString(a.getSi())), new String(Integer.toString(a.getFi())), new String(this.df.format(a.getPi())), new String(a.getCodeword()), new String(Integer.toString(a.getCodeword().length())) });
    } 
  }
  

  public ArrayList<Alfabeto> getAlfabeto() {
    return this.alfabeto;
  }
  
  public void setFrame(ShannonFanoJFrame sff) {
    this.jFrameShannonFano = sff;
  }
}
