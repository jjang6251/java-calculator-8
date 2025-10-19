package calculator;

import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String input) {
        if(input == null || input.isEmpty()) {
            return 0;
        }
        if(input.startsWith("//")) {
            //커스텀 구분자
            int lineIndex = input.indexOf('\n');
            if(lineIndex == -1) {
                throw new IllegalArgumentException("잘못된 커스텀 구분자 형식");
            }
            String separator = input.substring(2, lineIndex);
            String numString = input.substring(lineIndex+1);
            String[] tokens = numString.split(Pattern.quote(separator));
            int result = 0;
            for(String x : tokens) {
                result += toInt(x);
            }
            return result;
        }
        // 기본 구분자
        String[] tokens = input.split("[,:]");
        int result = 0;
        for(String x : tokens) {
            result += toInt(x);
        }
        return result;
    }

    private int toInt(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("기본 구분자 이외의 구분자가 있습니다");
        }
    }
}
