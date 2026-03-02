package com.project.ecommerce.inventory.controller;

import com.project.ecommerce.inventory.dto.InventoryMovementRequestDto;
import com.project.ecommerce.inventory.dto.ProductResponseDto;
import com.project.ecommerce.inventory.service.InventoryMovementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/movement")
public class InventoryMovementController {

    @Autowired
    private final InventoryMovementService inventoryService;

    @PostMapping("/entry")
    public ResponseEntity<ProductResponseDto> entryProducts(@RequestBody @Valid InventoryMovementRequestDto request){

        var response = inventoryService.entry(request.productId(), request.quantity());
        return ResponseEntity.ok(new ProductResponseDto(response));
    }

    @PostMapping("/exit")
    public ResponseEntity<ProductResponseDto> exitProducts(@RequestBody @Valid InventoryMovementRequestDto request){
        var response = inventoryService.exit(request.productId(), request.quantity());
        return ResponseEntity.ok(new ProductResponseDto(response));
    }
}
