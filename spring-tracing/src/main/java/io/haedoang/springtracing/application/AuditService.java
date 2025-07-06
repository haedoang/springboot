package io.haedoang.springtracing.application;

import brave.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditService {

    private final Tracer tracer;

    @Async
    public void saveAudit(String className) {
        log.info("invoke auditService.saveAudit {} {}", tracer.currentSpan().context().traceId(), tracer.currentSpan().context().spanId());
    }

    @Async("taskExecutor2")
    public void saveAudit2(String className) {
        log.info("invoke auditService.saveAudit2 {} {}", tracer.currentSpan().context().traceId(), tracer.currentSpan().context().spanId());
    }
}
