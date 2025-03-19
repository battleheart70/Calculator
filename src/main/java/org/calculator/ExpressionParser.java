package org.calculator;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser {

  public List<String> parse(String expression) {
    List<String> tokens = new ArrayList<>();
    StringBuilder number = new StringBuilder();
    boolean hasDot = false;

    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);

      // Обрабатываем унарный минус
      if (c == '-' && (tokens.isEmpty() || tokens.get(tokens.size() - 1).equals("("))) {
        number.append(c); // Запоминаем унарный минус как часть числа
        continue;
      }

      if (Character.isDigit(c) || c == '.') {
        if (c == '.') {
          if (hasDot) {
            throw new IllegalArgumentException("Ошибка: Неверный формат числа (две точки в числе) в выражении: " + expression);
          }
          if (number.isEmpty()) {
            number.append('0'); // ".5" → "0.5"
          }
          hasDot = true;
        }
        number.append(c);
      } else {
        saveLastNumber(tokens, number, expression);
        hasDot = false;
        if (c != ' ') tokens.add(String.valueOf(c));
      }
    }

    saveLastNumber(tokens, number, expression);
    return tokens;
  }

  private void saveLastNumber(List<String> tokens, StringBuilder number, String expression) {
    if (!number.isEmpty()) {
      // Проверяем, если в числе больше одной точки
      if (number.toString().chars().filter(ch -> ch == '.').count() > 1) {
        throw new IllegalArgumentException("Ошибка: Неверный формат числа (две точки в числе) в выражении: " + expression);
      }

      if (number.charAt(number.length() - 1) == '.') {
        number.deleteCharAt(number.length() - 1); // Убираем лишнюю точку
      }
      tokens.add(number.toString());
      number.setLength(0);
    }
  }
}
