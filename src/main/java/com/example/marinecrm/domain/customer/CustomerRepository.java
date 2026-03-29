package com.example.marinecrm.domain.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Page<Customer> findByUser_Company_Id(UUID companyId, Pageable pageable);
    Optional<Customer> findByIdAndUser_Company_Id(UUID id, UUID companyId);
}
