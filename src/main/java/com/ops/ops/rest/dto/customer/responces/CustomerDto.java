package com.ops.ops.rest.dto.customer.responces;

import com.ops.ops.rest.dto.customer.CustomerRole;
import lombok.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String name;

    private String address;

    private String username;

    private String phoneNumber;

    private CustomerRole role;

}
