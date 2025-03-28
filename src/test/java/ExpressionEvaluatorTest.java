import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.calculator.ExpressionEvaluator;
import org.calculator.ExpressionParser;
import org.junit.jupiter.api.Test;

public class ExpressionEvaluatorTest {

  private final ExpressionParser parser = new ExpressionParser(); // сам вызывает валидатор
  private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

  @Test
  void testSimpleAddition() {
    List<String> tokens = parser.parse("2 + 3");
    double result = evaluator.evaluate(tokens);
    assertEquals(5.0, result);
  }

  @Test
  void testSimpleMultiplication() {
    List<String> tokens = parser.parse("3 * 4");
    double result = evaluator.evaluate(tokens);
    assertEquals(12.0, result);
  }

  @Test
  void testBracketsPriority() {
    List<String> tokens = parser.parse("(2 + 3) * 4");
    double result = evaluator.evaluate(tokens);
    assertEquals(20.0, result);
  }

  @Test
  void testNegativeNumber() {
    List<String> tokens = parser.parse("-5 + 3");
    double result = evaluator.evaluate(tokens);
    assertEquals(-2.0, result);
  }

  @Test
  void testDecimalExpression() {
    List<String> tokens = parser.parse("3.5 + .5");
    double result = evaluator.evaluate(tokens);
    assertEquals(4.0, result);
  }

  @Test
  void testDivision() {
    List<String> tokens = parser.parse("10 / 5");
    double result = evaluator.evaluate(tokens);
    assertEquals(2.0, result);
  }

  @Test
  void testComplexExpression() {
    List<String> tokens = parser.parse("((2 + 3) * (4 - 1)) / 2");
    double result = evaluator.evaluate(tokens);
    assertEquals(7.5, result);
  }

  @Test
  void testInvalidDivisionByZero() {
    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> parser.parse("10 / 0"));
    assertTrue(exception.getMessage().contains("Деление на 0"));
  }

  @Test
  void testInvalidOperatorSequence() {
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              List<String> tokens = parser.parse("2 + * 3");
              evaluator.evaluate(tokens);
            });
    assertTrue(exception.getMessage().contains("оператор"));
  }
}
