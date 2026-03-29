package com.example.marinecrm.domain.sale.services;

import com.example.marinecrm.Command;
import com.example.marinecrm.domain.sale.Sale;
import com.example.marinecrm.domain.sale.SaleRepository;
import com.example.marinecrm.domain.user.User;
import com.example.marinecrm.exceptions.ForbiddenException;
import com.example.marinecrm.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteSaleService implements Command<UUID, Void> {

    private final SaleRepository saleRepository;

    @Override
    @Transactional
    public Void execute(UUID id) {
        Sale sale = saleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Venda não encontrada: " + id));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!sale.getUser().getCompany().getId().equals(user.getCompany().getId())) {
            throw new ForbiddenException();
        }

        saleRepository.delete(sale);
        return null;
    }
}
