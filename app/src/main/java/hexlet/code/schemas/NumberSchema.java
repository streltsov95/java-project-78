package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        addValidation("POSITIVE", num -> num > 0);
        return this;
    }

    public NumberSchema range(int from, int to) {
        addValidation("RANGE", num -> num >= from && num <= to);
        return this;
    }
}
