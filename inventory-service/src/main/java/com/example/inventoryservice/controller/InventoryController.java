package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.request.InventoryCreateDTO;
import com.example.inventoryservice.dto.response.InventoryResponseDTO;
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
        JsonApiData<InventoryResponseDTO> inventoryResponseDTO = inventoryService.createInventory(inventoryCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryResponseDTO);
    }

    @GetMapping("/product-info/{id}")
    public ResponseEntity<JsonApiData<ProductWithInventoryDTO>> getProductInfo(@PathVariable Long id) {
        JsonApiData<ProductWithInventoryDTO> productWithInventoryDTO = inventoryService.getProductWithInventory(id);
        return ResponseEntity.ok(productWithInventoryDTO);
    }
}
