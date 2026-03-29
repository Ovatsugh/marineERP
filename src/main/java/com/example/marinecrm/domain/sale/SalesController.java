package com.example.marinecrm.domain.sale;

import com.example.marinecrm.domain.sale.DTO.SalesRequest;
import com.example.marinecrm.domain.sale.DTO.SalesResponse;
import com.example.marinecrm.domain.sale.DTO.SalesUpdateRequest;
import com.example.marinecrm.domain.sale.services.*;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SalesController {

    private final CreateSaleService createSaleService;
    private final DeleteSaleService deleteSaleService;
    private final GetAllSaleService getAllSaleService;
    private final GetSaleByIdService getSaleByIdService;
    private final UpdateSaleService updateSaleService;

    @PostMapping()
    public ResponseEntity<SalesResponse> createSale(@Validated @RequestBody SalesRequest request) {
        return ResponseEntity.ok(createSaleService.execute(request));
    }

    @GetMapping()
    public ResponseEntity<Page<SalesResponse>> getSales(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(getAllSaleService.execute(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(deleteSaleService.execute(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesResponse> getSaleById(@PathVariable UUID id) {
        return ResponseEntity.ok(getSaleByIdService.execute(id));
    }

//    verificar implementação de update, talvez seja necessário criar um DTO específico pra isso, ou usar o SalesRequest mesmo, mas aí tem que ser verificado quais campos são obrigatórios e quais não são, e se o SalesRequest é adequado pra isso
//    @PutMapping("/{id}")
//    public ResponseEntity<SalesResponse> updateSale(@PathVariable UUID id, @Validated @RequestBody SalesRequest request) {
//        return ResponseEntity.ok(updateSaleService.execute(new SalesUpdateRequest(id, request)));
//    }
}
