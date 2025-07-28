package com.example.inventoryservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryCreateDTO {

    private Long productId;
    private Long quantity;
}
