package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {

    protected Map<String, Predicate<T>> rules;
    protected boolean isRequired;

    public BaseSchema() {
        rules = new HashMap<>();
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
