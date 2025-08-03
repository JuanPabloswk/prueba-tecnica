package com.example.inventoryservice.dto.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestDTO {

    @NotNull
    private Long inventoryId;
    @NotNull
    @Min(1)
    private Long quantity;
}
