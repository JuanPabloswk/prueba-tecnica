package com.example.productservice.mapper;

import com.example.productservice.dto.request.ProductCreateDTO;
import com.example.productservice.dto.response.ProductResponseDTO;
import com.example.productservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductCreateDTO createDTO){
        Product product = new Product();
        product.setName(createDTO.getName());
        product.setPrice(createDTO.getPrice());
        product.setDescription(createDTO.getDescription());
        return product;
    }

    public ProductResponseDTO toProductResponseDTO(Product product){
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(product.getId());
        responseDTO.setName(product.getName());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setDescription(product.getDescription());
        return responseDTO;
    }


}
