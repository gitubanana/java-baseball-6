package baseball.constant;

import static baseball.constant.Answer.NO;
import static baseball.constant.Answer.YES;
import static baseball.constant.BaseballProperty.NO_MESSAGE;
import static baseball.constant.BaseballProperty.YES_MESSAGE;
import static baseball.constant.ErrorMessage.INVALID_ANSWER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("== Answer 테스트 ==")
public class AnswerTest {
    @ParameterizedTest
    @DisplayName("YES와 NO를 잘 찾을 수 있다.")
    @MethodSource("getMatchingAnswerArguments")
    void 답변_찾기(String message, Answer expected) {
        assertThat(Answer.getMachingAnswer(message))
                .isEqualTo(expected);
    }

    static Stream<Arguments> getMatchingAnswerArguments() {
        return Stream.of(
                Arguments.of(YES_MESSAGE, YES),
                Arguments.of(NO_MESSAGE, NO)
        );
    }

    @ParameterizedTest
    @DisplayName("답변이 올바르지 않을 때 예외를 발생시킬 수 있다.")
    @ValueSource(strings = {"", "go", "nope"})
    void 잘못된_답변(String message) {
        assertThatThrownBy(() -> Answer.getMachingAnswer(message))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ANSWER.getMessage());
    }
}
