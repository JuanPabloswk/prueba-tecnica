package com.example.inventoryservice.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonApiError {

    private int status;
    private String code;
    private String title;
    private String detail;
    private String source;
    private LocalDateTime timestamp;
}
