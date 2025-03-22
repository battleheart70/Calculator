package org.calculator;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

  public List<String> tokenize(String expression) {
    List<String> tokens = new ArrayList<>();
    StringBuilder number = new StringBuilder();

    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);

      if (Character.isDigit(c) || c == '.' || (c == '-' && (tokens.isEmpty() || tokens.get(tokens.size() - 1).equals("(")))) {
        number.append(c);
      } else {
        if (number.length() > 0) {
          tokens.add(number.toString());
          number.setLength(0);
        }

        if (c != ' ') {
          tokens.add(String.valueOf(c));
        }
      }
    }

    if (number.length() > 0) {
      tokens.add(number.toString());
    }

    return tokens;
  }
}
