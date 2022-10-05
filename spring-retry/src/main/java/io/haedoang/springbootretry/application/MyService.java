package io.haedoang.springbootretry.application;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.sql.SQLException;

/**
 * author : haedoang
 * date : 2022/10/05
 * description :
 */
public interface MyService {

    /**
     * @param sql
     * @Description RuntimeException이 발생하는 경우 재시도를 한다
     */
    @Retryable(value = RuntimeException.class)
    void retryService(String sql);

    @Retryable(value = SQLException.class)
    void retryServiceWithRecovery(String sql) throws SQLException;

    @Recover
    void recover(SQLException e, String sql);
}
