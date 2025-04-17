package com.github.navratna.deferpay.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_catalogue")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCatalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String catalogueId;

    private String productType;
    private String description;
}
