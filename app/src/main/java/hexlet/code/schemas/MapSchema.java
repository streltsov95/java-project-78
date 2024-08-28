package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {

    public MapSchema<K, V> sizeof(int size) {
        Predicate<Map<K, V>> isSize = map -> map.size() == size;
        rules.put("SIZE_OF", isSize);
        return this;
    }

    public <T> MapSchema<K, V> shape(Map<String, BaseSchema<T>> schemas) {
        Predicate<Map<K, V>> isShape = map -> {
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
