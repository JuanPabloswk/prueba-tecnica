package com.example.inventoryservice.service.impl;

import com.example.inventoryservice.exception.ProductInventoryAlreadyExistsException;
import com.example.inventoryservice.client.ProductClient;
import com.example.inventoryservice.dto.request.InventoryCreateDTO;
import com.example.inventoryservice.dto.response.InventoryResponseDTO;
import com.example.inventoryservice.dto.response.ProductResponseDTO;
import com.example.inventoryservice.dto.response.ProductWithInventoryDTO;
import com.example.inventoryservice.mapper.InventoryMapper;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.service.InventoryService;
import com.example.inventoryservice.utils.JsonApiData;
import com.example.inventoryservice.utils.JsonApiResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductClient productClient;
    private final InventoryMapper inventoryMapper;

    @Override
    public JsonApiData<InventoryResponseDTO> createInventory(InventoryCreateDTO inventoryCreateDTO) {

        productClient.getProductInfo(inventoryCreateDTO.getProductId());

        if (inventoryRepository.existsByProductId(inventoryCreateDTO.getProductId())) {
            throw new ProductInventoryAlreadyExistsException("product inventory already exists ");
        }

        Inventory inventory = inventoryMapper.toInventory(inventoryCreateDTO);
        Inventory savedInventory = inventoryRepository.save(inventory);

        InventoryResponseDTO inventoryResponseDTO = inventoryMapper.toResponseDTO(savedInventory);

        return JsonApiResponse.build("inventory", savedInventory.getId().toString(), inventoryResponseDTO);
    }


    @Override
    public JsonApiData<ProductWithInventoryDTO> getProductWithInventory(Long productId) {

        ProductResponseDTO product = Optional.ofNullable(productClient.getProductInfo(productId).getBody())
                .orElseThrow(() -> new EntityNotFoundException("Product not found")).getAttributes();

        Long availableQuantity = inventoryRepository.findByProductId(productId)
                .map(Inventory::getQuantity).orElse(0L);

        ProductWithInventoryDTO productWithInventoryDTO = inventoryMapper.productWithInventoryDTO(product, availableQuantity);

        return JsonApiResponse.build("product-inventory", productId.toString(), productWithInventoryDTO);
    }
}
