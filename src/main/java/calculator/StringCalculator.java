package calculator;

public class StringCalculator {
    public int add(String input) {
        if(input == null || input.isEmpty()) {
            return 0;
        }
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
