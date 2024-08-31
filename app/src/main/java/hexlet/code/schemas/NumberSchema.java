package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        Predicate<Integer> required = Objects::nonNull;
        isRequired = true;
        rules.put("REQUIRED", required);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> isPositive = num -> isRequired ? num != null && num > 0 : num == null || num > 0;
        rules.put("POSITIVE", isPositive);
        return this;
    }

    public NumberSchema range(int from, int to) {
        Predicate<Integer> inRange = num -> isRequired
                ? num != null && (num >= from && num <= to) : num == null || (num >= from && num <= to);
        rules.put("RANGE", inRange);
        return this;
    }
}
