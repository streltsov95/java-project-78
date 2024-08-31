package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class BaseSchema<T> {

    protected Map<String, Predicate<T>> rules;
    protected boolean isRequired;

    public BaseSchema() {
        rules = new HashMap<>();
    }

    public BaseSchema<T> required() {
        Predicate<T> required = Objects::nonNull;
        isRequired = true;
        rules.put("REQUIRED", required);
        return this;
    }

    public final boolean isValid(T data) {
        for (var rule : rules.values()) {
            if (!rule.test(data)) {
                return false;
            }
        }
        return true;
    }
}
