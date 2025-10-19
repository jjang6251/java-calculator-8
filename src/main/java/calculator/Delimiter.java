package calculator;

import java.util.regex.Pattern;

public final class Delimiter {
    private final String value;

    public Delimiter(String value) {
        if (value == null || value.length() != 1) {
            throw new IllegalArgumentException("커스텀 구분자는 한 글자여야 한다.");
        }
        this.value = value;
    }

    public String asRegex() {
        return Pattern.quote(value);
    }
}
