package org.calculator;



import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ExpressionParserTest {

    private final ExpressionParser parser = new ExpressionParser();

    @Test
    public void testBasicExpression() {
        List<String> tokens = parser.parse("12 + 34");
        assertEquals(List.of("12", "+", "34"), tokens);
    }

    @Test
    public void testExpressionWithSpaces() {
        List<String> tokens = parser.parse("  3   +   4   * 2 ");
        assertEquals(List.of("3", "+", "4", "*", "2"), tokens);
    }

    @Test
    public void testExpressionWithBrackets() {
        List<String> tokens = parser.parse("(3 + 4) * 2");
        assertEquals(List.of("(", "3", "+", "4", ")", "*", "2"), tokens);
    }

    @Test
    public void testExpressionWithNegativeNumbers() {
        List<String> tokens = parser.parse("-5 + 3");
        assertEquals(List.of("-5", "+", "3"), tokens);
    }

    @Test
    public void testExpressionWithNegativeInBrackets() {
        List<String> tokens = parser.parse("(-5 + 3) * 2");
        assertEquals(List.of("(", "-5", "+", "3", ")", "*", "2"), tokens);
    }

    @Test
    public void testDecimalNumbers() {
        List<String> tokens = parser.parse("3.14 + .5");
        assertEquals(List.of("3.14", "+", "0.5"), tokens);
    }

    @Test
    public void testDoubleDotError() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse("1.2.3 + 4"));
        assertTrue(exception.getMessage().contains("Неверный формат числа"));
    }

    @Test
    public void testDoubleDotErrorAnotherCase() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse("10..5 + 2"));
        assertTrue(exception.getMessage().contains("Неверный формат числа"));
    }
}
