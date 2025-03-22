package org.console;

import java.util.List;
import java.util.Scanner;
import org.calculator.ExpressionEvaluator;
import org.calculator.ExpressionParser;

public class ExpressionConsoleRunner {

  private final ExpressionParser parser = new ExpressionParser();
  private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

  public void run() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Введите математическое выражение или 'exit' для выхода:");

    while (true) {
      System.out.print("> ");
      String input = scanner.nextLine().trim();

      if (input.equalsIgnoreCase("exit")) {
        System.out.println("До свидания!");
        break;
      }

      try {
        List<String> tokens = parser.parse(input);
        double result = evaluator.evaluate(tokens);
        System.out.println("Результат: " + result);
      } catch (Exception e) {
        System.out.println("Ошибка: " + e.getMessage());
      }
    }

    scanner.close();
  }
}
