package calculator;

public final class PositiveNumber {
    private final int value;

    public PositiveNumber(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다.");
        }
        this.value = value;
    }

    public static PositiveNumber of(String token) {
        try {
            return new PositiveNumber(Integer.parseInt(token));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
        }
    }

    public static PositiveNumber zero() {
        return new PositiveNumber(0);
    }

    public PositiveNumber add(PositiveNumber other) {
        return new PositiveNumber(this.value + other.value);
    }

    public int toInt() {
        return value;
    }
}
