import org.calculator.ExpressionValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionValidatorTest {
    // ========== ЧИСЛА ==========
    @Test
    void testValidDecimalNumber() {
        assertDoesNotThrow(() -> ExpressionValidator.validate(List.of("3.14")));
    }

    @Test
    void testDotAtEnd_shouldFail() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> ExpressionValidator.validate(List.of("1.")));
        assertTrue(ex.getMessage().contains("заканчиваться на точку"));
    }

    @Test
    void testMultipleDots_shouldFail() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> ExpressionValidator.validate(List.of("3.2.3")));
        assertTrue(ex.getMessage().contains("две точки"));
    }

    @Test
    void testDotAtStart_allowed() {
        assertDoesNotThrow(() -> ExpressionValidator.validate(List.of(".5")));
    }

    // ========== СКОБКИ ==========
    @Test
    void testUnclosedBracket_shouldFail() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> ExpressionValidator.validate(List.of("(", "2", "+", "3")));
        assertTrue(ex.getMessage().contains("Не все скобки закрыты"));
    }

    @Test
    void testUnopenedBracket_shouldFail() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> ExpressionValidator.validate(List.of("2", "+", "3", ")")));
        assertTrue(ex.getMessage().contains("Закрывающая скобка"));
    }

    @Test
    void testCorrectBrackets_shouldPass() {
        assertDoesNotThrow(() -> ExpressionValidator.validate(List.of("(", "2", "+", "3", ")")));
    }

    // ========== ОПЕРАТОРЫ ==========
    @Test
    void testDoubleOperators_shouldFail() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> ExpressionValidator.validate(List.of("2", "+", "*", "3")));
        assertTrue(ex.getMessage().contains("Два оператора подряд"));
    }

    @Test
    void testOperatorAtStart_shouldFail() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> ExpressionValidator.validate(List.of("+", "2", "*", "3")));
        assertTrue(ex.getMessage().contains("в начале"));
    }

    @Test
    void testOperatorAtEnd_shouldFail() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> ExpressionValidator.validate(List.of("2", "*", "3", "-")));
        assertTrue(ex.getMessage().contains("в начале или в конце"));
    }

    // ========== ДЕЛЕНИЕ НА НОЛЬ ==========
    @Test
    void testDivisionByZero_shouldFail() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> ExpressionValidator.validate(List.of("10", "/", "0")));
        assertTrue(ex.getMessage().contains("Деление на 0"));
    }

    @Test
    void testValidExpression_shouldPass() {
        assertDoesNotThrow(() -> ExpressionValidator.validate(List.of("(", "3.5", "+", "2", ")", "*", ".5")));
    }
    }

