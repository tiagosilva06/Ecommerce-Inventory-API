package com.project.ecommerce.inventory.dto;

public record ErrorResponse(int status, String message, String timeStamp) {
}
