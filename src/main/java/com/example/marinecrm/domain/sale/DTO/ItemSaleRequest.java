package com.example.marinecrm.domain.sale.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ItemSaleRequest(
        @NotNull(message = "Produto é obrigatório") UUID productId,
        @Positive(message = "Quantidade deve ser maior que zero") Integer quantity
) {
}
