package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {

    private Validator validator = new Validator();
    private NumberSchema numberSchema;

    @BeforeEach
    void setUp() {
        numberSchema = validator.number();
    }

    @Test
    public void requiredWithNullTest() {
        assertTrue(numberSchema.isValid(null));
        assertFalse(numberSchema.required().isValid(null));
    }

    @Test
    public void positiveTest() {
        assertTrue(numberSchema.positive().isValid(null));
        assertTrue(numberSchema.positive().isValid(5));
        assertFalse(numberSchema.positive().isValid(0));
    }

    @Test
    public void inRangeTest() {
        assertTrue(numberSchema.range(5, 10).isValid(null));
        assertTrue(numberSchema.range(5, 10).isValid(5));
        assertTrue(numberSchema.range(5, 10).isValid(10));
        assertFalse(numberSchema.range(5, 10).isValid(4));
        assertFalse(numberSchema.range(5, 10).isValid(11));
    }

    @Test
    public void collaborationMethodsTest() {
        NumberSchema actual = numberSchema.positive();
        assertTrue(actual.isValid(null));
        actual.required();
        assertFalse(actual.isValid(null));
        assertFalse(actual.isValid(-10));
        assertTrue(actual.isValid(10));
        actual.range(-5, 7);
        assertFalse(actual.isValid(-4));
        assertTrue(actual.isValid(6));
    }
}
