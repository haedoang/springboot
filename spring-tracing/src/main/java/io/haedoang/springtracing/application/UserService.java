package io.haedoang.springtracing.application;

import brave.Tracer;
import io.haedoang.springtracing.domain.User;
import io.haedoang.springtracing.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final Tracer tracer;
    private final UserRepository userRepository;

    private final AuditService auditService;

    public List<User> getUsers() {
        log.info("invoke userService.gerUsers {} {}", tracer.currentSpan().context().traceId(), tracer.currentSpan().context().spanId());

        auditService.saveAudit(this.getClass().getSimpleName());
        auditService.saveAudit2(this.getClass().getSimpleName());
        return userRepository.getUsers();
    }
}
