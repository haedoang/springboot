package io.haedoang.springbootretry.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * author : haedoang
 * date : 2022/10/05
 * description :
 */
@SpringBootTest
class MyServiceImplTest {

    @Autowired
    private MyServiceImpl myService;

    @BeforeEach
    void setUp() {
        myService.initCount();
    }

    @Test
    @DisplayName("서비스를 호출 시 카운트가 증가한다")
    public void invokeService() {
        // when
        myService.retryService("hello");

        // then
        assertThat(myService.getCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("Retryable은 예외가 발생할 시 재수행을 한다")
    public void throwExceptionUsingRetryable() {
        // then
        assertThatThrownBy(() -> myService.retryService("ERROR"))
                .isInstanceOf(RuntimeException.class);

        assertThat(myService.getCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("recovery는 예외 발생 시 재시도 이후에도 실패하는 경우 호출된다")
    public void throwExceptionWithRecovery() throws SQLException {
        // when
        myService.retryServiceWithRecovery("ERROR");

        // then
        assertThat(myService.getCount()).isEqualTo(30)
                .as("예외를 throw 했으나 @Recover 에서 예외를 캐치하여 호출됨");
    }
}