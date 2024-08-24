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
        mapSchema.sizeOf(2);
        assertFalse(mapSchema.isValid(data));
        data.put("key2", "value2");
        assertTrue(mapSchema.isValid(data));
    }
}
