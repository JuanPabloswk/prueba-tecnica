package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.request.InventoryCreateDTO;
import com.example.inventoryservice.dto.request.InventoryUpdateDTO;
import com.example.inventoryservice.dto.response.InventoryResponseDTO;
import com.example.inventoryservice.dto.response.InventoryUpdateResponseDTO;
import com.example.inventoryservice.dto.response.ProductWithInventoryDTO;
import com.example.inventoryservice.service.InventoryService;
import com.example.inventoryservice.utils.JsonApiData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/products")
    public ResponseEntity<JsonApiData<InventoryResponseDTO>> createInventory(@RequestBody InventoryCreateDTO inventoryCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.createInventory(inventoryCreateDTO));
    }

    @GetMapping("/product-info/{id}")
    public ResponseEntity<JsonApiData<ProductWithInventoryDTO>> getProductInfo(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.getProductWithInventory(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JsonApiData<InventoryUpdateResponseDTO>> updateInventory(@PathVariable Long id, @RequestBody InventoryUpdateDTO inventoryUpdateDTO) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, inventoryUpdateDTO));
    }
}
