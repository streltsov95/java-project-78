package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class StringSchema {

    private Map<String, Predicate<String>> rules;
    private boolean isRequired;

    public StringSchema() {
        rules = new LinkedHashMap<>();
    }

    public StringSchema required() {
        Predicate<String> required = str -> str != null && !str.isEmpty();
        isRequired = true;
        rules.put("REQUIRED", required);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<String> isMinLength = str -> str.length() >= length;
        rules.put("MIN_LENGTH", isMinLength);
        return this;
    }

    public StringSchema contains(String sample) {
        Predicate<String> isContains = str -> str.contains(sample);
        rules.put("CONTAINS", isContains);
        return this;
    }

    public boolean isValid(String data) {
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
