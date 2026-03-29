package com.example.marinecrm.domain.customer.service;

import com.example.marinecrm.Command;
import com.example.marinecrm.domain.customer.Customer;
import com.example.marinecrm.domain.customer.CustomerRepository;
import com.example.marinecrm.domain.customer.DTO.CustomerResponse;
import com.example.marinecrm.domain.user.User;
import com.example.marinecrm.exceptions.ForbiddenException;
import com.example.marinecrm.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCustomerService implements Command<UUID, Void> {
    private final CustomerRepository customerRepository;

    @Transactional
    public Void execute(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Cliente não encontrado: " + id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (!customer.getUser().getCompany().getId().equals(user.getCompany().getId())) {
            throw new ForbiddenException();
        }

        customerRepository.delete(customer);
        return null;
    }
}
