package org.example;

import org.calculator.Calculator;
import org.calculator.InputHandler;
import org.exceptions.DivideByZeroException;
import org.exceptions.ErrorHandler;

public class Main {
  public static void main(String[] args) {
    InputHandler inputHandler = new InputHandler();
    Calculator calculator = new Calculator();

    while (true) { // Запускаем бесконечный цикл
      double num1 = inputHandler.getNumber("Введите первое число: ");
      double num2 = inputHandler.getNumber("Введите второе число: ");
      String operator = inputHandler.getOperator();

      try {
        double result = calculator.calculate(num1, num2, operator);
        System.out.println("Результат: " + result);
      } catch (DivideByZeroException e) {
        ErrorHandler.handleException(e);
      }


      System.out.print("Хотите выполнить новый расчет? (y/n): ");
      String choice = inputHandler.getScanner().nextLine().trim().toLowerCase();

      if (choice.equals("n")) {
        System.out.println("Программа завершена.");
        break;
      }
    }

    inputHandler.closeScanner(); // Закрываем Scanner перед выходом
  }
}

