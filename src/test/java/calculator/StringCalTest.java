package calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringCalTest {

    @DisplayName("빈 문자열 입력 -> 0 반환")
    @Test
    void 빈문자열_0_반환() {
        StringCalculator c = new StringCalculator();

        assertThat(c.add("")).isEqualTo(0);
        assertThat(c.add(null)).isEqualTo(0);
        assertThat(c.add("1:2:3")).isNotEqualTo(0);
    }

    @DisplayName("기본 구분자(, :) 합산 성공")
    @Test
    void 기본_구분자_합산_성공() {
        StringCalculator c = new StringCalculator();

        //성공 케이스
        assertThat(c.add("1,2,3")).isEqualTo(6);
        assertThat(c.add("1:2:3")).isEqualTo(6);
        assertThat(c.add("1,2:3")).isEqualTo(6);
    }

    @DisplayName("기본 구분자(, :) 합산 실패")
    @Test
    void 기본_구분자_합산_실패() {
        StringCalculator c = new StringCalculator();

        //실패 케이스
        assertThatThrownBy(() -> c.add("1/2,3"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> c.add("1:2/3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("비숫자/음수/형식 예외 처리")
    @Test
    void 비숫자_음수_형식_예외처리() {
        StringCalculator c = new StringCalculator();

        assertThatThrownBy(() -> c.add("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> c.add("a,2,3"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> c.add("1,2,"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> c.add("//1,2,3"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> c.add("/1,2,3"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> c.add("//)\na)1)2"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> c.add("//)\n-1)2)3"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> c.add("//)\n1)2)"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
