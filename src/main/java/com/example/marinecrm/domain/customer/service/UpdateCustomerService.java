package com.example.marinecrm.domain.customer.service;

import com.example.marinecrm.Command;
import com.example.marinecrm.domain.customer.Customer;
import com.example.marinecrm.domain.customer.CustomerRepository;
import com.example.marinecrm.domain.customer.DTO.CustomerResponse;
import com.example.marinecrm.domain.customer.DTO.CustomerUpdateRequest;
import com.example.marinecrm.domain.user.User;
import com.example.marinecrm.exceptions.ForbiddenException;
import com.example.marinecrm.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCustomerService implements Command<CustomerUpdateRequest, CustomerResponse> {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerResponse execute(CustomerUpdateRequest request) {
        Customer customer = customerRepository.findById(request.id()).orElseThrow(() ->
                new ResourceNotFoundException("Funcionário não encontrado " + request.id()));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if (!customer.getUser().getCompany().getId().equals(user.getCompany().getId())) {
            throw new ForbiddenException();
        }
        
        customer.update(request);

        Customer saved = customerRepository.save(customer);

        return new CustomerResponse(saved);
    }
}
