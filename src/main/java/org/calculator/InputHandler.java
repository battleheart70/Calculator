package org.calculator;

import java.util.Scanner;

public class InputHandler {
  private Scanner scanner;

  public InputHandler() {
    this.scanner = new Scanner(System.in);
  }

  public double getNumber(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Double.parseDouble(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Ошибка: Введите корректное число.");
      }
    }
  }

  public String getOperator() {
    while (true) {
      System.out.print("Введите оператор (+, -, *, /): ");
      String operator = scanner.nextLine();
      if (operator.matches("[+\\-*/]")) {
        return operator;
      } else {
        System.out.println("Ошибка: Введите один из следующих операторов: +, -, *, /.");
      }
    }
  }

  public void closeScanner() {
    scanner.close();
  }

  public Scanner getScanner() {
    return scanner;
  }
}
