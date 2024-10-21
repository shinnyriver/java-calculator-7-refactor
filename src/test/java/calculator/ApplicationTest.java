package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 기본_구분자_사용() {
        assertSimpleTest(() -> {
            run("1,2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 특수문자_구분자_사용() {
        assertSimpleTest(() -> {
            run("//]\\n1]2,3]4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 예외_테스트_음수() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_문자() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,2,a"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_구분자1() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,2,3,"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_구분자2() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_커스텀_구분자1() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//\n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_커스텀_구분자2() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//|1|2|3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}