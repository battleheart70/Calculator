package org.calculator;

import java.util.List;
import java.util.Stack;

public class ExpressionValidator {

  public static void validate(List<String> tokens) {
    validateDecimalPoints(tokens);
    validateBrackets(tokens);
    validateOperators(tokens);
    validateDivisionByZero(tokens);
  }

  // Проверка корректности чисел с точкой
  private static void validateDecimalPoints(List<String> tokens) {
    for (String token : tokens) {
      long dotCount = token.chars().filter(ch -> ch == '.').count();
      if (dotCount > 1) {
        throw new IllegalArgumentException("Ошибка: Неверный формат числа (две точки): " + token);
      }
      if (token.endsWith(".")) {
        throw new IllegalArgumentException("Ошибка: Число не может заканчиваться на точку: " + token);
      }
    }
  }


  // Проверка корректности скобок
  private static void validateBrackets(List<String> tokens) {
    Stack<String> stack = new Stack<>();
    for (String token : tokens) {
      if (token.equals("(")) {
        stack.push(token);
      } else if (token.equals(")")) {
        if (stack.isEmpty()) {
          throw new IllegalArgumentException("Ошибка: Закрывающая скобка без открывающей.");
        }
        stack.pop();
      }
    }
    if (!stack.isEmpty()) {
      throw new IllegalArgumentException("Ошибка: Не все скобки закрыты.");
    }
  }

  // Проверка логики операторов
  private static void validateOperators(List<String> tokens) {
    String prev = null;
    for (int i = 0; i < tokens.size(); i++) {
      String token = tokens.get(i);

      if (isOperator(token)) {
        if (i == 0 || i == tokens.size() - 1) {
          throw new IllegalArgumentException("Ошибка: Оператор в начале или в конце выражения: " + token);
        }

        if (prev != null && isOperator(prev) && !prev.equals("(") && !token.equals("-")) {
          throw new IllegalArgumentException("Ошибка: Два оператора подряд: " + prev + " " + token);
        }
      }

      prev = token;
    }
  }

  // Простая проверка деления на 0 (только если сразу видно)
  private static void validateDivisionByZero(List<String> tokens) {
    for (int i = 0; i < tokens.size() - 1; i++) {
      String current = tokens.get(i);
      String next = tokens.get(i + 1);
      if (current.equals("/") && next.equals("0")) {
        throw new IllegalArgumentException("Ошибка: Деление на 0.");
      }
    }
  }

  private static boolean isOperator(String token) {
    return token.matches("[+\\-*/]");
  }

  private static boolean isNumber(String token) {
    return token.matches("-?\\d*(\\.\\d*)?");
  }

}
