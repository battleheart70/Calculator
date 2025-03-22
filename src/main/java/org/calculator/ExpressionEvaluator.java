package org.calculator;

import java.util.*;

public class ExpressionEvaluator {

  private static final Map<String, Integer> OPERATOR_PRECEDENCE = new HashMap<>();

  static {
    OPERATOR_PRECEDENCE.put("+", 1);
    OPERATOR_PRECEDENCE.put("-", 1);
    OPERATOR_PRECEDENCE.put("*", 2);
    OPERATOR_PRECEDENCE.put("/", 2);
  }

  public double evaluate(List<String> tokens) {
    List<String> rpn = toRPN(tokens);
    return evaluateRPN(rpn);
  }

  // Перевод в обратную польскую нотацию (ОПН)
  private List<String> toRPN(List<String> tokens) {
    List<String> output = new ArrayList<>();
    Stack<String> operators = new Stack<>();

    for (String token : tokens) {
      if (isNumber(token)) {
        output.add(token);
      } else if (OPERATOR_PRECEDENCE.containsKey(token)) {
        while (!operators.isEmpty()
            && OPERATOR_PRECEDENCE.containsKey(operators.peek())
            && OPERATOR_PRECEDENCE.get(operators.peek()) >= OPERATOR_PRECEDENCE.get(token)) {
          output.add(operators.pop());
        }
        operators.push(token);
      } else if (token.equals("(")) {
        operators.push(token);
      } else if (token.equals(")")) {
        while (!operators.isEmpty() && !operators.peek().equals("(")) {
          output.add(operators.pop());
        }
        if (!operators.isEmpty() && operators.peek().equals("(")) {
          operators.pop();
        }
      }
    }

    while (!operators.isEmpty()) {
      output.add(operators.pop());
    }

    return output;
  }

  // Вычисление RPN
  private double evaluateRPN(List<String> rpn) {
    Stack<Double> stack = new Stack<>();

    for (String token : rpn) {
      if (isNumber(token)) {
        stack.push(Double.parseDouble(token));
      } else {
        double b = stack.pop();
        double a = stack.pop();
        double result =
            switch (token) {
              case "+" -> a + b;
              case "-" -> a - b;
              case "*" -> a * b;
              case "/" -> a / b; // Деление на ноль проверяется валидатором
              default -> throw new IllegalArgumentException("Неизвестный оператор: " + token);
            };
        stack.push(result);
      }
    }

    return stack.pop();
  }

  private boolean isNumber(String token) {
    return token.matches("-?\\d+(\\.\\d+)?");
  }
}
