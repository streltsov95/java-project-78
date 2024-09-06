package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        addValidation("REQUIRED", str -> str != null && !str.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addValidation("MIN_LENGTH", str -> str.length() >= length);
        return this;
    }

    public StringSchema contains(String sample) {
        addValidation("CONTAINS", str -> str.contains(sample));
        return this;
    }
}
