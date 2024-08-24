package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<Object, Object>> {

    public MapSchema sizeOf(int size) {
        Predicate<Map<Object, Object>> isSize = map -> map.size() == size;
        rules.put("SIZE_OF", isSize);
        return this;
    }
}
