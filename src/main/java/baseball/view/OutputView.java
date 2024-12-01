package baseball.view;

import baseball.model.GameResult;

public class OutputView {
    public void print(String message) {
        System.out.print(message);
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void printGameResult(GameResult gameResult) {
        StringBuilder message = new StringBuilder();

        if (gameResult.isNothing()) {
            message.append("낫싱");
        }

        if (gameResult.ballCount() > 0) {
            message.append(gameResult.ballCount()).append("볼").append(" ");
        }

        if (gameResult.strikeCount() > 0) {
            message.append(gameResult.strikeCount()).append("스트라이크");
        }

        println(message.toString());
    }
}
