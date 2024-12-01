package baseball.model;

import static baseball.constant.BaseballProperty.BASEBALL_NUMBER_COUNT;

public record GameResult(int ballCount, int strikeCount) {
    public boolean isNothing() {
        return ballCount == 0 && strikeCount == 0;
    }

    public boolean allStrike() {
        return strikeCount == BASEBALL_NUMBER_COUNT;
    }
}
