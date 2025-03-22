package org.calculator;

import java.util.List;

public class ExpressionParser {

  private final Tokenizer tokenizer = new Tokenizer();

  public List<String> parse(String expression) {
    List<String> tokens = tokenizer.tokenize(expression);
    ExpressionValidator.validate(tokens); // Проверяем ошибки
    return tokens;
  }
}
