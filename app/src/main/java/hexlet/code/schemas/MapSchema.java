package hexlet.code.schemas;

import hexlet.code.Validator;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<Object, Object>> {

    private Map<Object, BaseSchema<?>> schemas;

    public MapSchema sizeOf(int size) {
        Predicate<Map<Object, Object>> isSize = map -> map.size() == size;
        rules.put("SIZE_OF", isSize);
        return this;
    }

    public MapSchema shape(Map<Object, BaseSchema<?>> schemas) {
        this.schemas = schemas;
        return this;
    }

    @Override
    public boolean isValid(Map<Object, Object> data) {
        if (data == null && !isRequired) {
            return true;
        }
        if (data == null && isRequired) {
            return false;
        }
        for (var rule : rules.values()) {
            if (!rule.test(data)) {
                return false;
            }
        }
        for (var schema : schemas.entrySet()) {
            var dataValue = data.get(schema.getKey());
            schema.getValue().isValid(dataValue);

        }
        return true;
    }
}
