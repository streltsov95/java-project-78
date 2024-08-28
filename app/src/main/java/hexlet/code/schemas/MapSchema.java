package hexlet.code.schemas;

import hexlet.code.Validator;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<Object, Object>> {

    public MapSchema sizeOf(int size) {
        Predicate<Map<Object, Object>> isSize = map -> map.size() == size;
        rules.put("SIZE_OF", isSize);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        Predicate<Map<Object, T>> isShape = map -> {
            for (var entry : schemas.entrySet()) {
                var value = map.get(entry.getKey());
                var schema = entry.getValue();
                if (!schema.isValid(value)) {
                    return false;
                }
            }
            return true;
        };
        rules.put("SHAPE", isShape);
        return this;
    }
}
