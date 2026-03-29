package com.example.marinecrm.domain.sale.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;


public record SalesRequest(
        @NotNull(message = "Cliente é obrigatório") UUID customerId,
        String notes,
        @NotEmpty(message = "A venda deve ter ao menos um item") @Valid List<ItemSaleRequest> items
) {
}
