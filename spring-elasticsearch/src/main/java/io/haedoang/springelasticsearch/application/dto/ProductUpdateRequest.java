package io.haedoang.springelasticsearch.application.dto;

import io.haedoang.springelasticsearch.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@ToString
@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class ProductUpdateRequest {
    private String name;
    private BigDecimal price;

    public Product toEntity(String id) {
        return Product.valueOf(Long.valueOf(id), name, price);
    }
}
