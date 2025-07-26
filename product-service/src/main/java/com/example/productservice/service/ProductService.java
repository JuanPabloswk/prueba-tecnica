package com.example.productservice.service;

import com.example.productservice.dto.request.ProductCreateDTO;
import com.example.productservice.dto.response.ProductResponseDTO;

public interface ProductService {

    ProductResponseDTO createProduct(ProductCreateDTO productCreateDTO);
}
