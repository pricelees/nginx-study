package com.working.pic.common.controller;

public record ShutdownResponse(
	String requestTime,
	String completedTime
) {
}
