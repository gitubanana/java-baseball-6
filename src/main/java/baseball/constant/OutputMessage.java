package baseball.constant;

import static baseball.constant.BaseballProperty.NO_MESSAGE;
import static baseball.constant.BaseballProperty.YES_MESSAGE;

public enum OutputMessage {
    WELCOME("숫자 야구 게임을 시작합니다."),
    BASEBALL_PROMPT("숫자를 입력해주세요 : "),
    ALL_STRIKE("3개의 숫자를 모두 맞히셨습니다! 게임 종료"),
    MORE_GAME("게임을 새로 시작하려면 " + YES_MESSAGE + ", 종료하려면 " + NO_MESSAGE + "를 입력하세요."),
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
