package com.example.marinecrm.domain.sale.DTO;

import java.util.UUID;

public record SalesUpdateRequest(UUID id, SalesRequest payload) {
}
