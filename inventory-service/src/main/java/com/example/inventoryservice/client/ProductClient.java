package com.example.inventoryservice.client;

import com.example.inventoryservice.dto.response.ProductResponseDTO;
import com.example.inventoryservice.utils.JsonApiData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8081" )
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ResponseEntity<JsonApiData<ProductResponseDTO>> getProductInfo(@PathVariable("id") Long id);
}
