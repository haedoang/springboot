package io.haedoang.springelasticsearch.product.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;

    private Product(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static Product valueOf(Long id, String name, BigDecimal price) {
        return new Product(id, name, price);
    }
}
