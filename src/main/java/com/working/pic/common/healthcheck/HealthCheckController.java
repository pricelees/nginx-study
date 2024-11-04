package com.working.pic.common.healthcheck;

import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    private final AtomicBoolean isTerminated = new AtomicBoolean(false);

    @GetMapping("/healthcheck")
    public ResponseEntity<Void> healthCheck() {
        if (isTerminated.get()) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
        return ResponseEntity.ok().build();
    }
}
