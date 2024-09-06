package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema sizeof(int size) {
        addValidation("SIZE_OF", map -> map.size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addValidation("SHAPE", map -> {
            for (var entry : schemas.entrySet()) {
                var value = map.get(entry.getKey());
                var schema = entry.getValue();
                if (!schema.isValid((T) value)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
