package com.example.productservice.utils;

import lombok.experimental.UtilityClass;


@UtilityClass
public class JsonApiResponse {

    public static <T> JsonApiData<T> build(String type, String id, T attributes) {
        return new JsonApiData<>(type, id, attributes);
    }

    public static <T> JsonApiData<T> buildProduct(String id, T attributes) {
        return build("product", id, attributes);
    }
}
