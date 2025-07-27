package com.example.productservice.service.impl;

import com.example.productservice.dto.request.ProductCreateDTO;
import com.example.productservice.dto.response.ProductResponseDTO;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import com.example.productservice.utils.JsonApiData;
import com.example.productservice.utils.JsonApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public JsonApiData<ProductResponseDTO> createProduct(ProductCreateDTO productCreateDTO) {
        Product product = productMapper.toProduct(productCreateDTO);
        Product savedProduct = productRepository.save(product);
        ProductResponseDTO responseDTO = productMapper.toProductResponseDTO(savedProduct);

        return JsonApiResponse.buildProduct(String.valueOf(responseDTO.getId()), responseDTO);
    }

    @Override
    public JsonApiData<ProductResponseDTO> getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ProductResponseDTO responseDTO = productMapper.toProductResponseDTO(product);

        return JsonApiResponse.buildProduct(String.valueOf(responseDTO.getId()), responseDTO);
    }

    @Override
    public List<JsonApiData<ProductResponseDTO>> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponseDTO)
                .map(productResponseDTO -> JsonApiResponse.buildProduct(String.valueOf(productResponseDTO.getId()), productResponseDTO))
                .toList();
    }
}
