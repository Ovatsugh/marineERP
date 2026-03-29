package com.example.marinecrm.domain.sale;

import com.example.marinecrm.domain.product.Product;
import com.example.marinecrm.domain.sale.DTO.ItemSaleRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "item_sales")
public class ItemSale {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private BigDecimal amount;

    public ItemSale(ItemSaleRequest request, Sale sale, Product product) {
        this.sale = sale;
        this.product = product;
        this.quantity = request.quantity();
        this.amount = product.getPrice().multiply(BigDecimal.valueOf(request.quantity()));
    }
}
