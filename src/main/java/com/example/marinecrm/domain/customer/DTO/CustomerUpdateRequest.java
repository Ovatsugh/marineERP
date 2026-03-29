package com.example.marinecrm.domain.customer.DTO;

import java.util.UUID;

public record CustomerUpdateRequest(UUID id, CustomerRequest payload) {

}
