package multimedia2;

import java.util.Comparator;

public class Alphabet {
  private char si;
  
  private int fa;
  
  private double pi;
  
  private String codeword;
  
  public Alphabet() {}
  
  public Alphabet(char si, int fa, double pi) {
    this.si = si;
    this.fa = fa;
    this.pi = pi;
    setCodeword(new String());
      System.out.println(pi);
  }
  
  public Alphabet(char si, String codeword) {
    this.si = si;
    this.codeword = codeword;
  }

    @Override
    public String toString() {
        return "Alphabet{" + "si=" + si + ", fa=" + fa + ", pi=" + pi + ", codeword=" + codeword + '}';
    }
  
  
  
  public char getSi() {
    return this.si;
  }
  
  public void setFa(int fa) {
    this.fa = fa;
  }
  
  public int getFa() {
    return this.fa;
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
  
  public static class sortAlphabet implements Comparator<Alphabet> {
    public int compare(Alphabet a0, Alphabet a1) {
      return a1.getFa() - a0.getFa();
    }
  }
}
