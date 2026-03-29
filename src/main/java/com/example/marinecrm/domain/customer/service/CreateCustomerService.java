package com.example.marinecrm.domain.customer.service;

import com.example.marinecrm.Command;
import com.example.marinecrm.domain.customer.Customer;
import com.example.marinecrm.domain.customer.CustomerRepository;
import com.example.marinecrm.domain.customer.DTO.CustomerRequest;
import com.example.marinecrm.domain.customer.DTO.CustomerResponse;
import com.example.marinecrm.domain.user.User;
import com.example.marinecrm.exceptions.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCustomerService implements Command<CustomerRequest, CustomerResponse> {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerResponse execute(CustomerRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Customer customer = new Customer(request, user);

        Customer saved = customerRepository.save(customer);

        return new CustomerResponse(saved);
    }
}
