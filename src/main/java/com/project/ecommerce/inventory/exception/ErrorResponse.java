package com.project.ecommerce.inventory.exception;

import java.time.LocalDateTime;

public record ErrorResponse(int status, String message, LocalDateTime timeStamp) {
}
