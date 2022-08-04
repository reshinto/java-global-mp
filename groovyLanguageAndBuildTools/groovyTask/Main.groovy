class Main {
  static void main(String[] args) {
    String a1 = "5+20-15+8";
    String a2 = "5+20-15+8/8*2-2";
    String a3 = "5+20-15+8/8*2-2*3";
    String a4 = "5+20-15+8/8*(2-2)*3";
    String a5 = "5+20-15+8/8*2";
    String a6 = "(5+20-15+8)/9*(2+2)*3";
    
    Calculator calculator = new Calculator();
    assert calculator.calculate(a1) == 18;
    assert calculator.calculate(a2) == 10;
    assert calculator.calculate(a3) == 6;
    assert calculator.calculate(a4) == 10;
    assert calculator.calculate(a5) == 12;
    assert calculator.calculate(a6) == 24;
  }

}