package multimedia2;

import java.util.Comparator;

public class Alfabeto {
  private char si;
  
  private int fi;
  
  private double pi;
  
  private String codeword;
  
  public Alfabeto() {}
  
  public Alfabeto(char si, int fi, double pi) {
    this.si = si;
    this.fi = fi;
    this.pi = pi;
    setCodeword(new String());
      System.out.println(pi);
  }
  
  public Alfabeto(char si, String codeword) {
    this.si = si;
    this.codeword = codeword;
  }

    @Override
    public String toString() {
        return "Alphabet{" + "si=" + si + ", fi=" + fi + ", pi=" + pi + ", codeword=" + codeword + '}';
    }
  
  
  
  public char getSi() {
    return this.si;
  }
  
  public void setFi(int fi) {
    this.fi = fi;
  }
  
  public int getFi() {
    return this.fi;
  }
  
  public void setSi(char si) {
    this.si = si;
  }
  
  public double getPi() {
    return this.pi;
  }
  
  public void setPi(double pi) {
    this.pi = pi;
      System.out.println("pi"+pi);
  }
  
  public String getCodeword() {
    return this.codeword;
  }
  
  public void setCodeword(String codeword) {
    this.codeword = codeword;
  }
  
  public static class ordenarAlfabeto implements Comparator<Alfabeto> {
    public int compare(Alfabeto a0, Alfabeto a1) {
      return a1.getFi() - a0.getFi();
    }
  }
}
