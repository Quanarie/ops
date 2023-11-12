package com.ops.ops.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerDto {

    private Long id;

    private String name;

    private String phoneNumber;

    private String address;

}
