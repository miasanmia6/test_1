package Gateway_Server_1400.controller;

import Gateway_Server_1400.entity.CommonResult;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConsumerServiceFallback {
    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @GetMapping("/fallbackA")
    public ResponseEntity fallbackA() {
        log.info(circuitBreakerRegistry.circuitBreaker("backendA").getState().toString());
        return ResponseEntity.ok("服务不可用，降级");
    }
}
