package com.example.marinecrm.domain.sale.services;

import com.example.marinecrm.Query;
import com.example.marinecrm.domain.sale.Sale;
import com.example.marinecrm.domain.sale.SaleRepository;
import com.example.marinecrm.domain.sale.DTO.SalesResponse;
import com.example.marinecrm.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetAllSaleService implements Query<Pageable, Page<SalesResponse>> {

    private final SaleRepository saleRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<SalesResponse> execute(Pageable pageable) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return saleRepository.findByUser_Company_Id(user.getCompany().getId(), pageable).map(SalesResponse::new);
    }
}
