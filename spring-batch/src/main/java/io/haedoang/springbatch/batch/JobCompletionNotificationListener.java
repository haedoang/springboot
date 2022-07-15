package io.haedoang.springbatch.batch;

import io.haedoang.springbatch.domain.People;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("beforeJob invoked");
        super.beforeJob(jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("afterJob invoked");
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED!! Time to verify the results");

            jdbcTemplate.query("SELECT person_id, first_name, last_name FROM people",
              (rs, row) -> new People(rs.getLong(1), rs.getString(2), rs.getString(3))
            ).forEach(it -> log.info("Found <{}> in the database.", it));
        }
    }
}
