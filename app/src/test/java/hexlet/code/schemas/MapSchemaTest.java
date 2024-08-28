package hexlet.code.schemas;

import hexlet.code.Validator;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {

    private Validator validator = new Validator();
    private MapSchema mapSchema;

    @BeforeEach
    void setUp() {
        mapSchema = validator.map();
    }

    @Test
    public void requiredTest() {
        assertTrue(mapSchema.isValid(null));
        assertFalse(mapSchema.required().isValid(null));
        assertTrue(mapSchema.isValid(new HashMap<>()));
    }

    @Test
    public void mapSchemaTest() {
        Map<Object, Object> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(mapSchema.required().isValid(data));
        mapSchema.sizeof(2);
        assertFalse(mapSchema.isValid(data));
        data.put("key2", "value2");
        assertTrue(mapSchema.isValid(data));
    }

    @Test
    public void nestedValidationTest() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        mapSchema.shape(schemas);

        Map<Object, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(mapSchema.isValid(human1));

        Map<Object, Object> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(mapSchema.isValid(human2));

        Map<Object, Object> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(mapSchema.isValid(human3));
    }
}
