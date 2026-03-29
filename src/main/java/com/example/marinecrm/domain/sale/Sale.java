package com.example.marinecrm.domain.sale;


import com.example.marinecrm.domain.company.Company;
import com.example.marinecrm.domain.customer.Customer;
import com.example.marinecrm.domain.product.Product;
import com.example.marinecrm.domain.sale.DTO.SalesRequest;
import com.example.marinecrm.domain.sale.DTO.SalesUpdateRequest;
import com.example.marinecrm.domain.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "sales")
public class Sale {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "sale")
    @JsonManagedReference
    private List<ItemSale> itemSales;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "notes")
    private String notes;

    public Sale(SalesRequest request, User user, Customer customer, BigDecimal amount) {
        this.user = user;
        this.customer = customer;
        this.amount = amount;
        this.notes = request.notes();
    }

    public void update(SalesUpdateRequest request) {
        this.notes = request.payload().notes();
    }
}
