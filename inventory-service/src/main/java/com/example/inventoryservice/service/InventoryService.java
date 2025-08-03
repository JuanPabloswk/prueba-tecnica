package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.request.InventoryCreateDTO;
import com.example.inventoryservice.dto.request.InventoryUpdateDTO;
<<<<<<< HEAD
import com.example.inventoryservice.dto.response.InventoryUpdateResponseDTO;
import com.example.inventoryservice.dto.response.ProductWithInventoryDTO;
=======
import com.example.inventoryservice.dto.request.PurchaseRequestDTO;
import com.example.inventoryservice.dto.response.InventoryUpdateResponseDTO;
import com.example.inventoryservice.dto.response.ProductWithInventoryResponseDTO;
>>>>>>> feature/purchase-endpoint
import com.example.inventoryservice.dto.response.InventoryResponseDTO;
import com.example.inventoryservice.dto.response.PurchaseResponseDTO;
import com.example.inventoryservice.utils.JsonApiData;

public interface InventoryService {

    JsonApiData<InventoryResponseDTO> createInventory(InventoryCreateDTO inventoryCreateDTO);

<<<<<<< HEAD
    JsonApiData<ProductWithInventoryDTO> getProductWithInventory(Long productId);

    JsonApiData<InventoryUpdateResponseDTO> updateInventory(Long id, InventoryUpdateDTO inventoryUpdateDTO);
=======
    JsonApiData<ProductWithInventoryResponseDTO> getProductWithInventory(Long productId);

    JsonApiData<InventoryUpdateResponseDTO> updateInventory(Long id, InventoryUpdateDTO inventoryUpdateDTO);

    JsonApiData<PurchaseResponseDTO> processPurchase(PurchaseRequestDTO purchaseRequest);
>>>>>>> feature/purchase-endpoint
}
