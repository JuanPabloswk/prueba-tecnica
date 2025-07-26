package com.example.productservice.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonApiData<T> {

    private String type;
    private String id;
    private T attributes;
}
