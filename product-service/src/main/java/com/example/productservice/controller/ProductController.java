package com.example.productservice.controller;

import com.example.productservice.dto.request.ProductCreateDTO;
import com.example.productservice.dto.response.ProductResponseDTO;
import com.example.productservice.utils.JsonApiData;
import com.example.productservice.utils.JsonApiResponse;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    public final ProductService productService;

    @PostMapping
    public ResponseEntity<JsonApiData<ProductResponseDTO>> create(@RequestBody ProductCreateDTO productCreateDTO) {
        ProductResponseDTO created = productService.createProduct(productCreateDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(JsonApiResponse.build("product", String.valueOf(created.getId()), created));
    }
}
