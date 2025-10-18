package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalTest {

    @DisplayName("빈 문자열 입력 -> 0 반환")
    @Test
    void 빈문자열_0_반환() {
        StringCalculator c = new StringCalculator();

        assertThat(c.add("")).isEqualTo(0);
        assertThat(c.add(null)).isEqualTo(0);
        assertThat(c.add("1:2:3")).isNotEqualTo(0);
    }
}
