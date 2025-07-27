package com.example.productservice.service;

import com.example.productservice.dto.request.ProductCreateDTO;
import com.example.productservice.dto.response.ProductResponseDTO;
import com.example.productservice.utils.JsonApiData;

import java.util.List;

public interface ProductService {

    JsonApiData<ProductResponseDTO> createProduct(ProductCreateDTO productCreateDTO);
    JsonApiData<ProductResponseDTO> getProductById(Long id);
    List<JsonApiData<ProductResponseDTO>> getAllProducts();
}
