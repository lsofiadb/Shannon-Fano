package multimedia2;

import java.util.Comparator;

public class Table {
  private String si;
  
  private double pi;
  
  private String aux;
  
  public Table() {}
  
  public Table(String si, double pi, String aux) {
    this.si = si;
    this.pi = pi;
    this.aux = aux;
  }
  
  public double getPi() {
    return this.pi;
  }
  
  public String getSi() {
    return this.si;
  }
  
  public void setPi(double pi) {
    this.pi = pi;
  }
  
  public void setSi(String si) {
    this.si = si;
  }
  
  public static class sortTable implements Comparator<Table> {
    public int compare(Table t0, Table t1) {
      return String.valueOf(t1.getPi()).compareTo(String.valueOf(t0.getPi()));
    }
  }
  
  public String getAux() {
    return this.aux;
  }
  
  public void setAux(String aux) {
    this.aux = aux;
  }
}
