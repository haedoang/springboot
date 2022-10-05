package io.haedoang.springbootretry.application;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * author : haedoang
 * date : 2022/10/05
 * description :
 */
@Service
public class MyServiceImpl implements MyService {
    public static final AtomicLong counter = new AtomicLong();
    public static final String ERROR = "ERROR";

    @Override
    public void retryService(String sql) {
        counter.incrementAndGet();
        System.out.println("MyService.retryService invoke");

        if (sql.equals(ERROR)) throw new RuntimeException();

        System.out.println("MyService.retryService finished");
    }

    @Override
    public void retryServiceWithRecovery(String sql) throws SQLException {
        counter.incrementAndGet();
        System.out.println("MyService.retryServiceWithRecovery invoke");

        if (sql.equals(ERROR)) throw new SQLException();

        System.out.println("MyService.retryServiceWithRecovery finished");
    }

    @Override
    public void recover(SQLException e, String sql) {
        System.out.println("MyService.recover invoke");
        System.out.println("sql: " + sql);
        counter.set(counter.get() * 10);
        System.out.println("MyService.recover invoke");
    }

    public Long getCount() {
        return counter.get();
    }

    public void initCount() {
        counter.set(0L);
    }
}
