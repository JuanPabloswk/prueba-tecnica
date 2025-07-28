package com.example.inventoryservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonApiResponse {

    public static <T> JsonApiData<T> build(String type, String id, T attributes) {
        return new JsonApiData<>(type, id, attributes);
    }

}
