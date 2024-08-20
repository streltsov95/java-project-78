package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {

    private Validator validator = new Validator();
    private StringSchema baseStringSchema;

    @BeforeEach
    public void setUp() {
        baseStringSchema = validator.string();
    }

    @Test
    public void requiredWithNullTest() {
        assertTrue(baseStringSchema.isValid(null));
        assertFalse(baseStringSchema.required().isValid(null));
    }

    @Test
    public void requiredWithEmptyStringTest() {
        assertTrue(baseStringSchema.isValid(""));
        assertFalse(baseStringSchema.required().isValid(""));
    }

    @Test
    public void minLengthTest() {
        assertTrue(baseStringSchema.minLength(5).isValid("what does the fox say"));
        assertFalse(baseStringSchema.minLength(5).isValid("what"));
    }

    @Test
    public void containsTest() {
        assertTrue(baseStringSchema.contains("wh").isValid("what does the fox say"));
        assertFalse(baseStringSchema.contains("wh").isValid("does the fox say"));
    }

    @Test
    public void collaborationMethodsTest() {
        StringSchema actualSchema = baseStringSchema.required()
                .contains("what ")
                .minLength(10);
        assertTrue(actualSchema.isValid("what does the fox say"));
        assertFalse(actualSchema.isValid(""));
    }
}
