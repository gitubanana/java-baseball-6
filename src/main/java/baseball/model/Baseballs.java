package baseball.model;

import static baseball.constant.BaseballProperty.BASEBALL_NUMBER_COUNT;
import static baseball.constant.ErrorMessage.BASEBALL_NUMBER_IS_NOT_DIGIT;
import static baseball.constant.ErrorMessage.DUPLICATED_BASEBALL_NUMBER;
import static baseball.constant.ErrorMessage.INVALID_BASEBALL_NUMBER_COUNT;

public class Baseballs {
    private final String numbers;

    public Baseballs(String numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(String numbers) {
        validateNumberCount(numbers);
        validateAllIsDigit(numbers);
        validateNotDuplicated(numbers);
    }

    private void validateNumberCount(String numbers) {
        if (numbers.length() != BASEBALL_NUMBER_COUNT) {
            throw new IllegalArgumentException(INVALID_BASEBALL_NUMBER_COUNT.getMessage());
        }
    }

    private void validateAllIsDigit(String numbers) {
        for (int i = 0; i < numbers.length(); i++) {
            if (!Character.isDigit(numbers.charAt(i))) {
                throw new IllegalArgumentException(BASEBALL_NUMBER_IS_NOT_DIGIT.getMessage());
            }
        }
    }

    private void validateNotDuplicated(String numbers) {
        if (numbers.chars().distinct().count() != BASEBALL_NUMBER_COUNT) {
            throw new IllegalArgumentException(DUPLICATED_BASEBALL_NUMBER.getMessage());
        }
    }

    public GameResult playGameWith(Baseballs other) {
        return new GameResult(countBall(other), countStrike(other));
    }

    private int countBall(Baseballs other) {
        int ballCount = 0;

        for (int i = 0; i < numbers.length(); i++) {
            if (other.contains(numbers.charAt(i)) && numbers.charAt(i) != other.numbers.charAt(i)) {
                ballCount++;
            }
        }
        return ballCount;
    }

    private boolean contains(char number) {
        for (int i = 0; i < numbers.length(); i++) {
            if (numbers.charAt(i) == number) {
                return true;
            }
        }
        return false;
    }

    private int countStrike(Baseballs other) {
        int strikeCount = 0;

        for (int i = 0; i < numbers.length(); i++) {
            if (numbers.charAt(i) == other.numbers.charAt(i)) {
                strikeCount++;
            }
        }
        return strikeCount;
    }
}
