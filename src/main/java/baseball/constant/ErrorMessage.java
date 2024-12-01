package baseball.constant;

import static baseball.constant.BaseballProperty.BASEBALL_NUMBER_COUNT;

public enum ErrorMessage {
    INVALID_BASEBALL_NUMBER_COUNT("숫자의 개수는 " + BASEBALL_NUMBER_COUNT + "이어야 합니다."),
    BASEBALL_NUMBER_IS_NOT_DIGIT("각 숫자는 1~9이어야 합니다."),
    DUPLICATED_BASEBALL_NUMBER("서로 다른 " + BASEBALL_NUMBER_COUNT + "개의 숫자여야 합니다."),
    INVALID_ANSWER("잘못된 답변입니다."),
    ;

    private static final String ERROR_PREFIX = "[Error] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return this.message;
    }
}
