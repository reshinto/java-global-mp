class OperatorRegistry {
  static int calculate(String operation, int a, int b) {
    switch (operation) {
      case "+": {
        return Maths.add(a, b);
      }
      case "-": {
        return Maths.subtract(a, b);
      }
      case "*": {
        return Maths.multiply(a, b);
      }
      case "/": {
        return Maths.divide(a, b);
      }
      default: {
        return Maths.add(a, b);
      }
    }
  }
}
