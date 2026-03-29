package com.example.marinecrm.domain.customer.DTO;

import com.example.marinecrm.domain.customer.Customer;

import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String name,
        String phone,
        String cpf
) {
    public CustomerResponse(Customer customer) {
        this(customer.getId(), customer.getName() , customer.getPhone(), customer.getCpf());
    }

}
