package calculator;

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

            Delimiter delimiter = new Delimiter(input.substring(2, lineIndex));

            regex += "|" + delimiter.asRegex();
            numbers = input.substring(lineIndex + 1);
            if (numbers.isEmpty()) {
                return 0;
            }
        }
        return Tokens.of(numbers, regex).sum();
    }
}
