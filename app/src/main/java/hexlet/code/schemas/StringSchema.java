package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        Predicate<String> required = str -> str != null && !str.isEmpty();
        isRequired = true;
        rules.put("REQUIRED", required);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<String> isMinLength = str -> isRequired
                ? str != null && str.length() >= length : str == null || str.length() >= length;
        rules.put("MIN_LENGTH", isMinLength);
        return this;
    }

    public StringSchema contains(String sample) {
        Predicate<String> isContains = str -> isRequired
                ? str != null && str.contains(sample) : str == null || str.contains(sample);
        rules.put("CONTAINS", isContains);
        return this;
    }
}
