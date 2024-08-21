package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {

    private Validator validator = new Validator();
    private NumberSchema baseNumberSchema;

    @BeforeEach
    void setUp() {
        baseNumberSchema = validator.number();
    }

    @Test
    public void requiredWithNullTest() {
        assertTrue(baseNumberSchema.isValid(null));
        assertFalse(baseNumberSchema.required().isValid(null));
    }

    @Test
    public void positiveTest() {
        assertTrue(baseNumberSchema.positive().isValid(null));
        assertTrue(baseNumberSchema.positive().isValid(5));
        assertFalse(baseNumberSchema.positive().isValid(0));
    }

    @Test
    public void inRangeTest() {
        assertTrue(baseNumberSchema.range(5, 10).isValid(5));
        assertTrue(baseNumberSchema.range(5, 10).isValid(10));
        assertFalse(baseNumberSchema.range(5, 10).isValid(4));
        assertFalse(baseNumberSchema.range(5, 10).isValid(11));
    }

    @Test
    public void collaborationMethodsTest() {
        NumberSchema actual = baseNumberSchema.positive();
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
