package com.example.marinecrm.domain.customer.service;

import com.example.marinecrm.Command;
import com.example.marinecrm.domain.customer.CustomerRepository;
import com.example.marinecrm.domain.customer.DTO.CustomerResponse;
import com.example.marinecrm.domain.user.User;
import com.example.marinecrm.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCustomerByIdService implements Command<UUID, CustomerResponse> {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse execute(UUID id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return customerRepository.findByIdAndUser_Company_Id(id, user.getCompany().getId())
                .map(CustomerResponse::new)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));
    }
}
