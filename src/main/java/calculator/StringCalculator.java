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
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("기본 구분자 이외의 구분자가 있습니다");
        }
    }

    private int sumTokens(String splitstring, String regex) {
        String[] tokens = splitstring.split(regex);
        int sum = 0;
        for (String x : tokens) {
            sum += toInt(x);
        }
        return sum;
    }
}
