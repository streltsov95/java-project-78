package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {

    protected Map<String, Predicate<T>> rules;
    protected boolean isRequired;

    public BaseSchema() {
        rules = new LinkedHashMap<>();
    }

    public BaseSchema<T> required() {
        Predicate<T> required = t -> t != null;
        isRequired = true;
        rules.put("REQUIRED", required);
        return this;
    }

    public boolean isValid(T data) {
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
        return true;
    }
}
