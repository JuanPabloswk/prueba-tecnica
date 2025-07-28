package com.example.inventoryservice.mapper;

import com.example.inventoryservice.dto.request.InventoryCreateDTO;
import com.example.inventoryservice.dto.request.InventoryUpdateDTO;
import com.example.inventoryservice.dto.response.InventoryResponseDTO;
import com.example.inventoryservice.dto.response.InventoryUpdateResponseDTO;
import com.example.inventoryservice.dto.response.ProductResponseDTO;
import com.example.inventoryservice.dto.response.ProductWithInventoryDTO;
import com.example.inventoryservice.model.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public Inventory toInventory(InventoryCreateDTO inventoryCreateDTO) {
        Inventory inventory = new Inventory();
        inventory.setProductId(inventoryCreateDTO.getProductId());
        inventory.setQuantity(inventoryCreateDTO.getQuantity());

        return inventory;
    }

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
}
