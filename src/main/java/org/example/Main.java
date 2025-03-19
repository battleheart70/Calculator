package org.example;

import org.calculator.Calculator;
import org.calculator.ExpressionParser;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    Calculator calculator = new Calculator();
    ExpressionParser parser = new ExpressionParser();

    List<String> tokens1 = parser.parse("12.5 + 34.7 * 5");
    System.out.println(tokens1); // ["12.5", "+", "34.7", "*", "5"]

    List<String> tokens2 = parser.parse("(3.14 + 2) * 4 - .5");
    System.out.println(tokens2); // ["(", "3.14", "+", "2", ")", "*", "4", "-", "0.5"]

    List<String> tokens3 = parser.parse("5 + (10. - 3.5) * 2");
    System.out.println(tokens3); // ["5", "+", "(", "10", "-", "3.5", ")", "*", "2"]

    List<String> tokens4 = parser.parse("1.2.3 + 4"); // Ошибочный ввод
    System.out.println(tokens4); // ["1.2", "3", "+", "4"]
  }
}
