package org.calculator;

import org.exceptions.DivideByZeroException;

public class Calculator {

  public double add(double a, double b) {
    return a + b;
  }

  public double subtract(double a, double b) {
    return a - b;
  }

  public double multiply(double a, double b) {
    return a * b;
  }

  public double divide(double a, double b) throws DivideByZeroException {
    if (b == 0) {
      throw new DivideByZeroException();
    }
    return a / b;
  }

  public double calculate(double num1, double num2, String operator) throws DivideByZeroException {
    switch (operator) {
      case "+":
        return add(num1, num2);
      case "-":
        return subtract(num1, num2);
      case "*":
        return multiply(num1, num2);
      case "/":
        return divide(num1, num2);
      default:
        throw new IllegalArgumentException();
    }
  }
}
