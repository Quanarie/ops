package com.ops.ops.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String title;

    private float price;

}
