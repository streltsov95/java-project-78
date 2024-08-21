package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class NumberSchema {

    private Map<String, Predicate<Integer>> rules;
    private boolean isRequired;

    public NumberSchema() {
        rules = new LinkedHashMap<>();
    }

    public NumberSchema required() {
        Predicate<Integer> required = num -> num != null;
        isRequired = true;
        rules.put("REQUIRED", required);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> isPositive = num -> num > 0;
        rules.put("POSITIVE", isPositive);
        return this;
    }

    public NumberSchema range(int from, int to) {
        Predicate<Integer> inRange = num -> num >= from && num <= to;
        rules.put("RANGE", inRange);
        return this;
    }

    public boolean isValid(Integer data) {
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
