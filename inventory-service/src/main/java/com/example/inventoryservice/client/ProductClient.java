package com.example.inventoryservice.client;

import com.example.inventoryservice.dto.response.ProductResponseDTO;
import com.example.inventoryservice.utils.JsonApiData;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "product-service", url = "http://localhost:8080")
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ResponseEntity<JsonApiData<ProductResponseDTO>> getProductInfo(@PathVariable("id") Long id);

    default Optional<ProductResponseDTO> findProductById(Long id) {
        try {
            ResponseEntity<JsonApiData<ProductResponseDTO>> response = getProductInfo(id);
            return Optional.ofNullable(response.getBody())
                    .map(JsonApiData::getAttributes);
        } catch (FeignException.NotFound e) {
            return Optional.empty();
        }
    }
}
