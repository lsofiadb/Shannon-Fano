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
    //ordena el alfabeto de mayor a menor frecuencia
    Collections.sort(this.alfabeto, new Alfabeto.ordenarAlfabeto());
    obtenerRaizArbol();
  }
  
  private int alphabetCharIndex(char c) {
    //la primera iteración retornara -1 porque el array esta vacio 
    if (this.alfabeto.isEmpty())
      return -1; 
    int index = 0;
    for (Alfabeto a : this.alfabeto) {
        //compara cada elemento del alfabeto con el caracter para ver si ya esta
      if (c == a.getSi())
        return index; //indice del primer caracter de aparicion
      index++;
    } 
    //el caracter solo aparece una vez en la lista
    return -1;
  }
  
  /*Construye el alfabeto de acuerdo al mensaje ingresado*/
  //obtiene la frecuencia y probabilidad de cada caracter
  private void construirAlfabeto(String mensaje) {
    int stringSize = mensaje.length();
    //para cada letra de la palabra 
    for (char c : mensaje.toCharArray()) {
      //obtiene el primer indice de aparicion de un caracter en la lista
      int index = alphabetCharIndex(c);
      //si el alfabeto esta vacio
      if (index == -1) {
        //caracter de frecuencia igual a 1, primer caracter del alfabeto
        //añade las propiedades del simbolo, su frecuencia y probabilidad
        this.alfabeto.add(new Alfabeto(c, 1, 1.0D / stringSize));
          
      } else {
          //el alfabeto ya tiene al menos un elemento, a partir del indice 
          //va sumando 1 a la frecuencia del caracter
        int fi = ((Alfabeto)this.alfabeto.get(index)).getFi() + 1;
        //establece la frecuencia de cada caracter del alfabeto
        ((Alfabeto)this.alfabeto.get(index)).setFi(fi);
        //De acuerdo a la frecuencia saca la probabilidad 
         double pi =(double)fi/(double)stringSize;
         //establece la probabilidad de cada uno de los caracteres del alfabeto 
        ((Alfabeto)this.alfabeto.get(index)).setPi(pi);
       
      } 
    } 
  }
  //obtiene la raiz del arbol, se compone de varios caracteres
  private String shannonFanoRootString() {
    String root = new String();
    int i = 1;
    for (Alfabeto a : this.alfabeto) {
      //obtiene el ultimo caracter del alfabeto
      if (i == this.alfabeto.size()) {
        root = root.concat(Character.toString(a.getSi()));
      } else {
        //obtiene los elementos iniciales de la raiz y va contatenando
        root = root.concat(Character.toString(a.getSi())).concat(", ");
      } 
      i++;
    } 
    //la raiz se compone del alfabeto
    return root;
  }
  
  /*A partir de la raiz construye el arbol*/
  private void obtenerRaizArbol() {
    String root = shannonFanoRootString();
    construirArbol(root);
  }
  
  private void construirArbol(String root) {
    //los dos ultimos elementos los divide directamente de izquierda a derecha
    if (root.length() > 3) { //l,a
      int vLeft = 0;
      int vRight = 0;
      String left = new String();
      String right = new String();
      //convierte el string de la ruta en un array 
      for (char c : root.replace(", ", "").toCharArray()) {
        int indexChar = alphabetCharIndex(c);
        if (indexChar != -1)
          if (vLeft <= vRight) {
            //añade 1 de acuerdo a la probabilidad del caracter
            vLeft += ((Alfabeto)this.alfabeto.get(indexChar)).getFi();
            //guarda el caracter en el string del nodo de la rama izquierda
            left = left.concat(Character.toString(c)).concat(", ");
          } else {
            //añade 1 de acuerdo a la probabilidad del caracter
            vRight += ((Alfabeto)this.alfabeto.get(indexChar)).getFi();
            //guarda el caracter en el string del nodo de la rama derecha
            right = right.concat(Character.toString(c)).concat(", ");
          }  
      } 
      left = left.subSequence(0, left.length() - 2).toString();
      right = right.subSequence(0, right.length() - 2).toString();
      /*Al construir el arbol se asigna a las ramas 0 a la izquierda y 1 a la derecha*/
      updateCodeword(left.replace(", ", ""), '0');
      updateCodeword(right.replace(", ", ""), '1');
      //recursividad
      //envia nuevamente una "raiz" de cada nivel del arbol
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
