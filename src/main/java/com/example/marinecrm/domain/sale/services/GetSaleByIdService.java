package com.example.marinecrm.domain.sale.services;

import com.example.marinecrm.Command;
import com.example.marinecrm.domain.sale.SaleRepository;
import com.example.marinecrm.domain.sale.DTO.SalesResponse;
import com.example.marinecrm.domain.user.User;
import com.example.marinecrm.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetSaleByIdService implements Command<UUID, SalesResponse> {

    private final SaleRepository saleRepository;

    @Override
    @Transactional(readOnly = true)
    public SalesResponse execute(UUID id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return saleRepository.findByIdAndUser_Company_Id(id, user.getCompany().getId())
                .map(SalesResponse::new)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada: " + id));
    }
}
