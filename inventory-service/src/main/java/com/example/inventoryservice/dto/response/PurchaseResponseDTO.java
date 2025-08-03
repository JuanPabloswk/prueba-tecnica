package com.example.inventoryservice.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseResponseDTO {

    @JsonIgnore
    private String purchaseId;
    private Long productId;
    private String productName;
    private Long quantityPurchased;
    private Long remainingStock;
    private BigDecimal totalAmount;
    private LocalDateTime purchaseDate;
}
