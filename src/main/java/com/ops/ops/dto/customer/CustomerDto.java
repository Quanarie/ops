package com.ops.ops.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerDto {

    private String name;

    private String address;

    private String nickname;

    private String phoneNumber;

}
