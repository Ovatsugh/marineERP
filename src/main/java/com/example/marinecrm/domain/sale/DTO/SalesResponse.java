package com.example.marinecrm.domain.sale.DTO;

import java.math.BigDecimal;
import java.util.UUID;
import com.example.marinecrm.domain.sale.Sale;

public record SalesResponse(UUID id, UUID customerId, BigDecimal amount, String notes) {
    public SalesResponse(Sale sales) {
        this(sales.getId(), sales.getCustomer().getId(), sales.getAmount(), sales.getNotes());
    }
}
