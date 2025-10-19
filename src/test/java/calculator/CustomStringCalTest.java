package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CustomStringCalTest {

    @DisplayName("커스텀 구분자 케이스 성공")
    @Test
    void 커스텀_구분자_합산_성공() {
        StringCalculator c = new StringCalculator();

        //커스텀 성공
        assertThat(c.add("//(\n1(2(3")).isEqualTo(6);
        assertThat(c.add("//*\n1*2*3")).isEqualTo(6);
        assertThat(c.add("//(\n1:2(3")).isEqualTo(6);
        assertThat(c.add("//(\n")).isEqualTo(0);

    }

    @DisplayName("커스텀 구분자 케이스 실패")
    @Test
    void 커스텀_구분자_합산_실패() {
        StringCalculator c = new StringCalculator();

        //커스텀 실패
        assertThatThrownBy(() -> c.add("//(\n1)2)3"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> c.add("//)\n1*2*3"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
