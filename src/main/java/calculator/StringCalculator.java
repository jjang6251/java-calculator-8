package calculator;

import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        input = input.replace("\\n", "\n");
        //기본 구분자
        String regex = "[,:]";
        String numbers = input;

        //커스텀 구분자
        if (input.startsWith("//")) {
            int lineIndex = input.indexOf('\n');
            if (lineIndex == -1) {
                throw new IllegalArgumentException("잘못된 커스텀 구분자 형식");
            }

            String separator = input.substring(2, lineIndex);
            if (separator.length() != 1) {
                throw new IllegalArgumentException("커스텀 구분자는 한 글자여야 합니다.");
            }

            regex += "|" + Pattern.quote(separator);
            numbers = input.substring(lineIndex + 1);
            if (numbers.isEmpty()) {
                return 0;
            }
        }
        return sumTokens(numbers, regex);
    }

    private int toInt(String token) {
        try {
            int n = Integer.parseInt(token);
            if (n < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다.");
            }
            return n;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
        }
    }

    private int sumTokens(String splitstring, String regex) {
        String[] tokens = splitstring.split(regex, -1);
        int sum = 0;
        for (String x : tokens) {
            if (x.isEmpty()) {
                // 입력값이 "1,2,"인 경우
                throw new IllegalArgumentException("잘못된 형식(빈 값이 포함됨)");
            }
            sum += toInt(x);
        }
        return sum;
    }
}
