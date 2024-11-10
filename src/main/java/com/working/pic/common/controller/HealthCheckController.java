package com.working.pic.common.controller;

import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    private final AtomicBoolean isTerminated = new AtomicBoolean(false);

    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthCheck() {
        if (isTerminated.get()) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("terminated");
        }
        return ResponseEntity.ok("healthy");
    }

    @PostMapping("/terminate")
    public ResponseEntity<Void> terminate() {
        isTerminated.set(true);
        return ResponseEntity.ok().build();
    }
}
