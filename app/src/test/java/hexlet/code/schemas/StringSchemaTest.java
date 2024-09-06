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
        var minLengthSchema = stringSchema.minLength(5);
        assertTrue(minLengthSchema.isValid("what does the fox say"));
        assertTrue(minLengthSchema.isValid(null));
        assertFalse(minLengthSchema.isValid("what"));
    }

    @Test
    public void containsTest() {
        var containsSchema = stringSchema.contains("wh");
        assertTrue(containsSchema.isValid("what does the fox say"));
        assertTrue(containsSchema.isValid(null));
        assertFalse(containsSchema.isValid("does the fox say"));
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
