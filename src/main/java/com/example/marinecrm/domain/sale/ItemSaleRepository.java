package com.example.marinecrm.domain.sale;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemSaleRepository extends JpaRepository<ItemSale, UUID> {
}
