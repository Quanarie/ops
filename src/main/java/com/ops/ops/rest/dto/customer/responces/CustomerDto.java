package com.ops.ops.rest.dto.customer.responces;

import com.ops.ops.rest.dto.customer.CustomerRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String name;

    private String address;

    private String username;

    private String phoneNumber;

    private CustomerRole role;

}
