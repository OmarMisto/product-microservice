package com.example.product.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;
    @Column(nullable = false)
    private String categoryName;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "productCategory",fetch = FetchType.EAGER)
    @BatchSize(size = 50)
    private ArrayList<Product> products;
}
