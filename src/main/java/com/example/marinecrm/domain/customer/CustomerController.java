package com.example.marinecrm.domain.customer;

import com.example.marinecrm.domain.customer.DTO.CustomerRequest;
import com.example.marinecrm.domain.customer.DTO.CustomerResponse;
import com.example.marinecrm.domain.customer.DTO.CustomerUpdateRequest;
import com.example.marinecrm.domain.customer.service.*;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CreateCustomerService createCustomerService;
    private final DeleteCustomerService deleteCustomerService;
    private final GetAllCustomerService getAllCustomerService;
    private final GetCustomerByIdService getCustomerByIdService;
    private final UpdateCustomerService updateCustomerService;

    @PostMapping()
    public ResponseEntity<CustomerResponse> createCustomer(@Validated @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(createCustomerService.execute(request));
    }

    @GetMapping()
    public ResponseEntity<Page<CustomerResponse>> getCustomers(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(getAllCustomerService.execute(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(deleteCustomerService.execute(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable UUID id) {
        return ResponseEntity.ok(getCustomerByIdService.execute(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable UUID id, @Validated @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(updateCustomerService.execute(new CustomerUpdateRequest(id, request)));
    }
}
