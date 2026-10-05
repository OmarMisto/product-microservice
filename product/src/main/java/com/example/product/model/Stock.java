package com.example.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stockId;// primary key of the stock in the product db
    @Column(nullable = false,unique = true)
    private long productStockId;//product id in the stock db
    @OneToMany(mappedBy = "stock",cascade = CascadeType.ALL)
    private ArrayList<Product> product;
    @Column(nullable = false,unique = true)
    private long storeId;
}
