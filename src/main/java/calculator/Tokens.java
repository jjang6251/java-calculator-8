package calculator;

import java.util.Arrays;
import java.util.List;

public final class Tokens {
    private final List<String> values;

    private Tokens(List<String> values) {
        validateNoEmpty(values);
        this.values = List.copyOf(values);
    }

    public static Tokens of(String text, String regex) {
        String[] arr = text.split(regex, -1); // 끝의 빈 토큰 보존
        return new Tokens(Arrays.asList(arr));
    }

    public int sum() {
        PositiveNumber total = PositiveNumber.zero();
        for (String s : values) {
            total = total.add(PositiveNumber.of(s));
        }
        return total.toInt();
    }

    private static void validateNoEmpty(List<String> values) {
        for (String v : values) {
            if (v.isEmpty()) {
                throw new IllegalArgumentException("잘못된 형식(빈 값이 포함됨)");
            }
        }
    }
}
