package io.haedoang.springelasticsearch.application;

import io.haedoang.springelasticsearch.application.dto.ProductResponse;
import io.haedoang.springelasticsearch.application.dto.ProductSaveRequest;
import io.haedoang.springelasticsearch.application.dto.ProductUpdateRequest;
import io.haedoang.springelasticsearch.domain.Product;
import io.haedoang.springelasticsearch.infra.ESProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ESProductRepository productRepository;


    public ProductResponse findById(String id) {
        Product product = productRepository.findById(id);
        return ProductResponse.valueOf(product);
    }

    public ProductResponse save(ProductSaveRequest request)  {
        Product product = productRepository.save(request);
        return ProductResponse.valueOf(product);
    }

    public ProductResponse update(String id, ProductUpdateRequest request)  {
        Product product = productRepository.update(id, request);
        return ProductResponse.valueOf(product);
    }

    public void deleteById(String id)  {
        productRepository.deleteById(id);
    }
}
