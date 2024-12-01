package baseball.model;

import static baseball.constant.ErrorMessage.BASEBALL_NUMBER_IS_NOT_DIGIT;
import static baseball.constant.ErrorMessage.DUPLICATED_BASEBALL_NUMBER;
import static baseball.constant.ErrorMessage.INVALID_BASEBALL_NUMBER_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("== Baseballs 테스트 ==")
public class BaseballsTest {
    @Nested
    @DisplayName("-- 기능 테스트 --")
    class FeatureTest {
        @ParameterizedTest
        @DisplayName("볼, 스트라이크 개수를 잘 구할 수 있다.")
        @MethodSource("getGameResultArguments")
        void 게임_결과(String computerNumbers, String myNumbers, GameResult expected) {
            Baseballs computer = new Baseballs(computerNumbers);
            Baseballs me = new Baseballs(myNumbers);
            GameResult result = computer.playGameWith(me);

            assertAll(
                    () -> assertThat(result.ballCount()).isEqualTo(expected.ballCount()),
                    () -> assertThat(result.strikeCount()).isEqualTo(expected.strikeCount())
            );
        }

        static Stream<Arguments> getGameResultArguments() {
            return Stream.of(
                    Arguments.of("425", "123", new GameResult(0, 1)),
                    Arguments.of("425", "456", new GameResult(1, 1)),
                    Arguments.of("425", "789", new GameResult(0, 0)),
                    Arguments.of("425", "425", new GameResult(0, 3)),
                    Arguments.of("425", "542", new GameResult(3, 0))
            );
        }
    }

    @Nested
    @DisplayName("-- 예외 테스트 --")
    class ExceptionTest {
        @ParameterizedTest
        @DisplayName("길이가 3이 아니면 예외를 발생시킬 수 있다.")
        @ValueSource(strings = {"0", "1234", "42"})
        void 잘못된_길이(String numbers) {
            assertThatThrownBy(() -> new Baseballs(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_BASEBALL_NUMBER_COUNT.getMessage());
        }

        @ParameterizedTest
        @DisplayName("숫자가 아니면 예외를 발생시킬 수 있다.")
        @ValueSource(strings = {"wow", "1v2", "1st"})
        void 숫자_아니면_예외(String numbers) {
            assertThatThrownBy(() -> new Baseballs(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BASEBALL_NUMBER_IS_NOT_DIGIT.getMessage());
        }

        @ParameterizedTest
        @DisplayName("숫자가 중복되면 예외를 발생시킬 수 있다.")
        @ValueSource(strings = {"112", "777", "333"})
        void 숫자_중복_예외(String numbers) {
            assertThatThrownBy(() -> new Baseballs(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(DUPLICATED_BASEBALL_NUMBER.getMessage());
        }
    }
}
