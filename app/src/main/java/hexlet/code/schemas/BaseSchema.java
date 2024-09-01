package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class BaseSchema<T> {

    protected Map<String, Predicate<T>> rules;

    public BaseSchema() {
        rules = new HashMap<>();
    }

    public BaseSchema<T> required() {
        Predicate<T> required = Objects::nonNull;
        rules.put("REQUIRED", required);
        return this;
    }

    public final boolean isValid(T data) {
        if (!rules.containsKey("REQUIRED") && data == null) {
            return true;
        }
        if (rules.containsKey("REQUIRED") && data == null) {
            return false;
        }
        for (var rule : rules.values()) {
            if (!rule.test(data)) {
                return false;
            }
        }
        return true;
    }
}
