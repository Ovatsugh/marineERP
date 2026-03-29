package com.example.marinecrm.domain.sale.services;

import com.example.marinecrm.Command;
import com.example.marinecrm.domain.sale.Sale;
import com.example.marinecrm.domain.sale.SaleRepository;
import com.example.marinecrm.domain.sale.DTO.SalesResponse;
import com.example.marinecrm.domain.sale.DTO.SalesUpdateRequest;
import com.example.marinecrm.domain.user.User;
import com.example.marinecrm.exceptions.ForbiddenException;
import com.example.marinecrm.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateSaleService implements Command<SalesUpdateRequest, SalesResponse> {

    private final SaleRepository saleRepository;

    @Override
    @Transactional
    public SalesResponse execute(SalesUpdateRequest request) {
        Sale sale = saleRepository.findById(request.id()).orElseThrow(() ->
                new ResourceNotFoundException("Venda não encontrada: " + request.id()));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!sale.getUser().getCompany().getId().equals(user.getCompany().getId())) {
            throw new ForbiddenException();
        }

        sale.update(request);

        Sale saved = saleRepository.save(sale);

        return new SalesResponse(saved);
    }
}
