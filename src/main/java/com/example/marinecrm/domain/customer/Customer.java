package com.example.marinecrm.domain.customer;

import com.example.marinecrm.domain.customer.DTO.CustomerRequest;
import com.example.marinecrm.domain.customer.DTO.CustomerUpdateRequest;
import com.example.marinecrm.domain.sale.Sale;
import com.example.marinecrm.domain.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "customers")
@NoArgsConstructor
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Sale> sales;

    @Column(name = "phone")
    private String phone;

    @Column(name = "cpf")
    private  String cpf;


    public Customer(CustomerRequest request, User user) {
        this.name = request.name();
        this.user = user;
        this.phone = request.phone();
        this.cpf = request.cpf();
    }

    public void update(CustomerUpdateRequest request) {
        this.name = request.payload().name();
        this.phone = request.payload().phone();
    }
}
