package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {

    private Validator validator = new Validator();
    private StringSchema stringSchema;

    @BeforeEach
    public void setUp() {
        stringSchema = validator.string();
    }

    @Test
    public void requiredWithNullTest() {
        assertTrue(stringSchema.isValid(null));
        assertFalse(stringSchema.required().isValid(null));
    }

    @Test
    public void requiredWithEmptyStringTest() {
        assertTrue(stringSchema.isValid(""));
        assertFalse(stringSchema.required().isValid(""));
    }

    @Test
    public void minLengthTest() {
        assertTrue(stringSchema.minLength(5).isValid("what does the fox say"));
        assertFalse(stringSchema.minLength(5).isValid("what"));
    }

    @Test
    public void containsTest() {
        assertTrue(stringSchema.contains("wh").isValid("what does the fox say"));
        assertFalse(stringSchema.contains("wh").isValid("does the fox say"));
    }

    @Test
    public void collaborationMethodsTest() {
        StringSchema actualSchema = stringSchema.required()
                .contains("what ")
                .minLength(10);
        assertTrue(actualSchema.isValid("what does the fox say"));
        assertFalse(actualSchema.isValid(""));
    }
}
