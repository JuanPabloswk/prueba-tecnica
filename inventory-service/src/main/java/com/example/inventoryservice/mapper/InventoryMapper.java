package com.example.inventoryservice.mapper;

import com.example.inventoryservice.dto.request.InventoryCreateDTO;
import com.example.inventoryservice.dto.request.InventoryUpdateDTO;
<<<<<<< HEAD
import com.example.inventoryservice.dto.response.InventoryResponseDTO;
import com.example.inventoryservice.dto.response.InventoryUpdateResponseDTO;
import com.example.inventoryservice.dto.response.ProductResponseDTO;
import com.example.inventoryservice.dto.response.ProductWithInventoryDTO;
=======
import com.example.inventoryservice.dto.request.PurchaseRequestDTO;
import com.example.inventoryservice.dto.response.*;
>>>>>>> feature/purchase-endpoint
import com.example.inventoryservice.model.Inventory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class InventoryMapper {

    public Inventory toInventory(InventoryCreateDTO dto) {
        Inventory inventory = new Inventory();
        inventory.setProductId(dto.getProductId());
        inventory.setQuantity(dto.getQuantity());
        return inventory;
    }

<<<<<<< HEAD
    public void toInventoryUpdate(InventoryUpdateDTO updateDTO, Inventory inventory) {
        if (updateDTO.getProductId() != null) {
            inventory.setProductId(updateDTO.getProductId());
        }
        if (updateDTO.getQuantity() != null) {
            inventory.setQuantity(updateDTO.getQuantity());
        }
    }


    public ProductWithInventoryDTO productWithInventoryDTO(ProductResponseDTO productResponseDTO, Long inventoryQuantity) {
        ProductWithInventoryDTO dto = new ProductWithInventoryDTO();
        dto.setId(productResponseDTO.getId());
        dto.setName(productResponseDTO.getName());
        dto.setPrice(productResponseDTO.getPrice());
        dto.setDescription(productResponseDTO.getDescription());
        dto.setInventoryQuantity(inventoryQuantity);

        return dto;
=======
    public void toInventoryUpdate(InventoryUpdateDTO dto, Inventory inventory) {
        if (dto.getProductId() != null) {
            inventory.setProductId(dto.getProductId());
        }
        if (dto.getQuantity() != null) {
            inventory.setQuantity(dto.getQuantity());
        }
>>>>>>> feature/purchase-endpoint
    }

    public InventoryResponseDTO toResponseDTO(Inventory inventory) {
        InventoryResponseDTO dto = new InventoryResponseDTO();
        dto.setId(inventory.getId());
        dto.setProductId(inventory.getProductId());
        dto.setQuantity(inventory.getQuantity());
        return dto;
    }

    public InventoryUpdateResponseDTO toUpdateResponseDTO(Inventory inventory) {
        InventoryUpdateResponseDTO dto = new InventoryUpdateResponseDTO();
        dto.setProductId(inventory.getProductId());
        dto.setQuantity(inventory.getQuantity());
        return dto;
    }

    public ProductWithInventoryResponseDTO toProductWithInventoryResponseDTO(ProductResponseDTO productResponseDTO, Long inventoryQuantity) {
        ProductWithInventoryResponseDTO dto = new ProductWithInventoryResponseDTO();
        dto.setId(productResponseDTO.getId());
        dto.setName(productResponseDTO.getName());
        dto.setPrice(productResponseDTO.getPrice());
        dto.setDescription(productResponseDTO.getDescription());
        dto.setInventoryQuantity(inventoryQuantity);
        return dto;
    }

    public PurchaseResponseDTO toPurchaseResponseDTO(Inventory inventory, ProductResponseDTO productResponseDTO,
                                                     PurchaseRequestDTO purchaseRequestDTO, BigDecimal total, String purchaseId) {
        PurchaseResponseDTO dto = new PurchaseResponseDTO();
        dto.setPurchaseId(purchaseId);
        dto.setProductId(inventory.getProductId());
        dto.setProductName(productResponseDTO.getName());
        dto.setQuantityPurchased(purchaseRequestDTO.getQuantity());
        dto.setRemainingStock(inventory.getQuantity());
        dto.setTotalAmount(total);
        dto.setPurchaseDate(LocalDateTime.now());
        return dto;
    }

    public InventoryUpdateResponseDTO toUpdateResponseDTO(Inventory inventory) {
        InventoryUpdateResponseDTO dto = new InventoryUpdateResponseDTO();
        dto.setProductId(inventory.getProductId());
        dto.setQuantity(inventory.getQuantity());
        return dto;
    }
}
