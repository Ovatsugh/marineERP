package com.example.marinecrm.domain.customer.service;

import com.example.marinecrm.Query;
import com.example.marinecrm.domain.customer.CustomerRepository;
import com.example.marinecrm.domain.customer.DTO.CustomerResponse;
import com.example.marinecrm.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetAllCustomerService implements Query<Pageable, Page<CustomerResponse>> {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerResponse> execute(Pageable page) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return customerRepository.findByUser_Company_Id(user.getCompany().getId(), page)
                .map(CustomerResponse::new);
    }
}
