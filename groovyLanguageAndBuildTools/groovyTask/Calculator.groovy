class Calculator {
  private Map<String, String> priorityOperators;
  private Map<String, String> priority;

  Calculator() {
    this.priorityOperators = new HashMap<String, String>() {{
      put("*", "*");
      put("/", "/");
    }} 
    this.priority = new HashMap<String, String>() {{
      put("(", "(");
      put(")", ")");
    }}
  }

  int calculate(String s) {
    List<String> arr = s.split("(?<=[\\d.])(?=[^\\d.])|(?<=[^\\d.])(?=[\\d.])|(?=[()])|(?<=[()])");
    int result = 0;
    int temp = 0;
    String tempOperator = "";
    String currentOperator = "+";
    
    for (int i = 0; i < arr.size(); i++) {
      if (isNumber(arr[i])) {
        def currentValue = Integer.parseInt(arr[i]);
        result = OperatorRegistry.calculate(currentOperator, result, currentValue)

        if (
          i < arr.size() - 2 && !priorityOperators.containsKey(arr[i + 1]) ||
          i == arr.size() - 1 && tempOperator != ""
        ) {
          result = OperatorRegistry.calculate(tempOperator, temp, result);
          tempOperator = ""
          temp = 0
        }
      } else {
        def currentValue = arr[i];
        if (priority.containsKey(currentValue)) {
          if (currentValue == ")") {
            return result
          }
          if (currentValue == "(") {
            i++;
            while (arr[i] == "(") {
              i++;
            }
            int endIndex = i;
            while (arr[endIndex] != ")") {
              endIndex++;
            }
            result = OperatorRegistry.calculate(currentOperator, result, this.calculate(String.join("", arr.subList(i, ++endIndex))));
            while (arr[i] != ")") {
              i++;
            }
            continue
          }

        }
        if (i < arr.size() - 2 && priorityOperators.containsKey(arr[i + 2]) && tempOperator == "" && !priorityOperators.containsKey(currentValue) && !priority.containsKey(currentValue)) {
          temp = result
          result = 0
          tempOperator = currentValue
          currentOperator = "+"
        } else {
          currentOperator = currentValue
        }
      }
    }
    return result;
  }

  static boolean isNumber(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}