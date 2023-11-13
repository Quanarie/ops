package com.ops.ops.rest.dto.customer.responces;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerDto {

    private String name;

    private String address;

    private String username;

    private String phoneNumber;

}
