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
        Predicate<Integer> isPositive = num -> num > 0;
        rules.put("POSITIVE", isPositive);
        return this;
    }

    public NumberSchema range(int from, int to) {
        Predicate<Integer> inRange = num -> num >= from && num <= to;
        rules.put("RANGE", inRange);
        return this;
    }
}
