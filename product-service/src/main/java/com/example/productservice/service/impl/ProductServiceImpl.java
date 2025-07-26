package com.example.productservice.service.impl;

import com.example.productservice.dto.request.ProductCreateDTO;
import com.example.productservice.dto.response.ProductResponseDTO;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDTO createProduct(ProductCreateDTO productCreateDTO) {
        Product product = productMapper.toProduct(productCreateDTO);
        return productMapper.toProductResponseDTO(productRepository.save(product));
    }
}
