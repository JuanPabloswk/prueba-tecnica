package com.example.productservice.controller;

import com.example.productservice.dto.request.ProductCreateDTO;
import com.example.productservice.dto.response.ProductResponseDTO;
import com.example.productservice.utils.JsonApiData;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    public final ProductService productService;

    @PostMapping
    public ResponseEntity<JsonApiData<ProductResponseDTO>> create(@RequestBody ProductCreateDTO dto) {
        return ResponseEntity.ok(productService.createProduct(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonApiData<ProductResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<JsonApiData<ProductResponseDTO>>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
