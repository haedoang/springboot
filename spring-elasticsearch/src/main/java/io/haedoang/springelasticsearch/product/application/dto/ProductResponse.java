package io.haedoang.springelasticsearch.product.application.dto;

import io.haedoang.springelasticsearch.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Getter
@ToString
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;

    public static ProductResponse valueOf(Long id, String name, BigDecimal price) {
        return new ProductResponse(id, name, price);
    }

    public static ProductResponse valueOf(Product entity) {
        return new ProductResponse(entity.getId(), entity.getName(), entity.getPrice());
    }
}
