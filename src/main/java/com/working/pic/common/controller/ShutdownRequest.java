package com.working.pic.common.controller;

import java.time.LocalDateTime;

public record ShutdownRequest(
	LocalDateTime requestTime,
	int delay
) {
}
