package com.example.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_gen")
    @SequenceGenerator(name = "product_gen",allocationSize = 30,sequenceName = "product_sec")
    private long productId;
    @Column(nullable = false)
    private String productName;
    @Lob
    private String description;
    private String productNo;
    private String brand;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private ArrayList<ProductImage> productImages;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private ProductCategory productCategory;
    @Column(name = "price",nullable = false)
    private BigDecimal price;
    private int discountPercentage;
    @ManyToOne
    @JoinColumn(name = "stockId")
    private Stock stock;
}
