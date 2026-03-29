package com.example.marinecrm.domain.sale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
    Page<Sale> findByUser_Company_Id(UUID companyId, Pageable pageable);
    Optional<Sale> findByIdAndUser_Company_Id(UUID id, UUID companyId);
}
