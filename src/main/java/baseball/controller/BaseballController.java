package baseball.controller;

import static baseball.constant.Answer.YES;
import static baseball.constant.BaseballProperty.BASEBALL_NUMBER_COUNT;
import static baseball.constant.OutputMessage.ALL_STRIKE;
import static baseball.constant.OutputMessage.BASEBALL_PROMPT;
import static baseball.constant.OutputMessage.MORE_GAME;
import static baseball.constant.OutputMessage.WELCOME;

import baseball.constant.Answer;
import baseball.model.Baseballs;
import baseball.model.GameResult;
import baseball.view.InputView;
import baseball.view.OutputView;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class BaseballController {
    private final InputView inputView;
    private final OutputView outputView;

    public BaseballController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.println(WELCOME.getMessage());

        do {
            Baseballs computer = generateComputerBaseballs();

            readBaseballsTillAllStrike(computer);
        } while (readAnswerToMoreGame() == YES);
    }

    private Baseballs generateComputerBaseballs() {
        List<Integer> computer = new ArrayList<>();
        StringBuilder numbers = new StringBuilder(BASEBALL_NUMBER_COUNT);
        while (computer.size() < BASEBALL_NUMBER_COUNT) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
                numbers.append(randomNumber);
            }
        }

        return new Baseballs(numbers.toString());
    }

    private void readBaseballsTillAllStrike(Baseballs computer) {
        while (true) {
            Baseballs player = readBaseballs();
            GameResult gameResult = computer.playGameWith(player);

            outputView.printGameResult(gameResult);
            if (gameResult.allStrike()) {
                outputView.println(ALL_STRIKE.getMessage());
                break;
            }
        }
    }

    private Baseballs readBaseballs() {
        outputView.print(BASEBALL_PROMPT.getMessage());
        return new Baseballs(inputView.readLine());
    }

    private Answer readAnswerToMoreGame() {
        outputView.println(MORE_GAME.getMessage());
        return Answer.getMachingAnswer(inputView.readLine());
    }
}
