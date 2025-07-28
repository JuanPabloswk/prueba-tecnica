package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.request.InventoryCreateDTO;
import com.example.inventoryservice.dto.response.ProductWithInventoryDTO;
import com.example.inventoryservice.dto.response.InventoryResponseDTO;
import com.example.inventoryservice.utils.JsonApiData;

public interface InventoryService {

    JsonApiData<InventoryResponseDTO> createInventory(InventoryCreateDTO inventoryCreateDTO);

    JsonApiData<ProductWithInventoryDTO> getProductWithInventory(Long productId);
}
