package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema sizeof(int size) {
        Predicate<Map<?, ?>> isSize = map -> map.size() == size;
        rules.put("SIZE_OF", isSize);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        Predicate<Map<?, ?>> isShape = map -> {
            for (var entry : schemas.entrySet()) {
                var value = map.get(entry.getKey());
                var schema = entry.getValue();
                if (!schema.isValid((T) value)) {
                    return false;
                }
            }
            return true;
        };
        rules.put("SHAPE", isShape);
        return this;
    }
}
