package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class BaseSchema<T> {

    private Map<String, Predicate<T>> rules;

    public BaseSchema() {
        rules = new HashMap<>();
    }

    /**
     * Marks the current schema as required, meaning that the value must not be {@code null}.
     * This method adds a rule to the schema that checks if the value is non-null.
     *
     * <p>Internally, this method creates a {@link Predicate} that verifies the value is non-null
     * and stores it in the {@code rules} map with the key "REQUIRED".</p>
     *
     * @return The current schema instance with the "required" rule applied.
     */
    public BaseSchema<T> required() {
        addValidation("REQUIRED", Objects::nonNull);
        return this;
    }

    public final void addValidation(String ruleName, Predicate<T> rule) {
        rules.put(ruleName, rule);
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
