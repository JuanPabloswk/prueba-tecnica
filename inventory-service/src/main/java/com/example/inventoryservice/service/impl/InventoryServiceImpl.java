package com.example.inventoryservice.service.impl;

import com.example.inventoryservice.dto.request.InventoryUpdateDTO;
<<<<<<< HEAD
import com.example.inventoryservice.dto.response.InventoryUpdateResponseDTO;
import com.example.inventoryservice.exception.InventoryNotFoundException;
import com.example.inventoryservice.exception.ProductInventoryAlreadyExistsException;
=======
import com.example.inventoryservice.dto.request.PurchaseRequestDTO;
import com.example.inventoryservice.dto.response.*;
import com.example.inventoryservice.exception.*;
>>>>>>> feature/purchase-endpoint
import com.example.inventoryservice.client.ProductClient;
import com.example.inventoryservice.dto.request.InventoryCreateDTO;
import com.example.inventoryservice.mapper.InventoryMapper;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.service.InventoryService;
import com.example.inventoryservice.utils.JsonApiData;
import com.example.inventoryservice.utils.JsonApiResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

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
    public JsonApiData<ProductWithInventoryResponseDTO> getProductWithInventory(Long productId) {

        ProductResponseDTO product = Optional.ofNullable(productClient.getProductInfo(productId).getBody())
                .orElseThrow(() -> new EntityNotFoundException("Product not found")).getAttributes();

        Long availableQuantity = inventoryRepository.findByProductId(productId)
                .map(Inventory::getQuantity).orElse(0L);

        ProductWithInventoryResponseDTO productWithInventoryResponseDTO = inventoryMapper.toProductWithInventoryResponseDTO(product, availableQuantity);

        return JsonApiResponse.build("product-inventory", productId.toString(), productWithInventoryResponseDTO);
    }

    public JsonApiData<InventoryUpdateResponseDTO> updateInventory(Long id, InventoryUpdateDTO inventoryUpdateDTO) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));

        inventoryMapper.toInventoryUpdate(inventoryUpdateDTO, inventory);

        Inventory updatedInventory = inventoryRepository.save(inventory);

        InventoryUpdateResponseDTO responseDTO = inventoryMapper.toUpdateResponseDTO(updatedInventory);
        return JsonApiResponse.build("product-inventory", updatedInventory.getId().toString(), responseDTO);
    }

    @Override
    public JsonApiData<PurchaseResponseDTO> processPurchase(PurchaseRequestDTO purchaseRequest) {

        Inventory inventory = inventoryRepository.findById(purchaseRequest.getInventoryId())
                .orElseThrow(() -> new ProductNotInInventoryException("Product not in inventory"));


        ProductResponseDTO product = productClient.findProductById(inventory.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        if (inventory.getQuantity() < purchaseRequest.getQuantity()) {
            throw new InsufficientStockException(
                    "Insufficient stock. Available: " + inventory.getQuantity() +
                            ", Requested: " + purchaseRequest.getQuantity());
        }

        inventory.setQuantity(inventory.getQuantity() - purchaseRequest.getQuantity());
        inventoryRepository.save(inventory);

        String purchaseId = UUID.randomUUID().toString();
        BigDecimal total = product.getPrice().multiply(BigDecimal.valueOf(purchaseRequest.getQuantity()));

        PurchaseResponseDTO purchaseResponseDTO = inventoryMapper.toPurchaseResponseDTO(
                inventory, product, purchaseRequest, total, purchaseId);

        return JsonApiResponse.build("purchase", purchaseId, purchaseResponseDTO);
    }

    public JsonApiData<InventoryUpdateResponseDTO> updateInventory(Long id, InventoryUpdateDTO inventoryUpdateDTO) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));

        inventoryMapper.toInventoryUpdate(inventoryUpdateDTO, inventory);

        Inventory updatedInventory = inventoryRepository.save(inventory);

        InventoryUpdateResponseDTO responseDTO = inventoryMapper.toUpdateResponseDTO(updatedInventory);
        return JsonApiResponse.build("product-inventory", updatedInventory.getId().toString(), responseDTO);
    }
}
