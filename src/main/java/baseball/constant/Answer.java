package baseball.constant;

import static baseball.constant.BaseballProperty.NO_MESSAGE;
import static baseball.constant.BaseballProperty.YES_MESSAGE;
import static baseball.constant.ErrorMessage.INVALID_ANSWER;

import java.util.Arrays;

public enum Answer {
    YES(YES_MESSAGE),
    NO(NO_MESSAGE),
    ;

    private final String message;

    Answer(String message) {
        this.message = message;
    }

    public static Answer getMachingAnswer(String message) {
        return Arrays.stream(Answer.values())
                .filter(answer -> answer.matches(message))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ANSWER.getMessage()));
    }

    private boolean matches(String message) {
        return this.message.equals(message);
    }
}
