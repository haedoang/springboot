package io.haedoang.springelasticsearch.product.application;

import io.haedoang.springelasticsearch.product.application.dto.ProductResponse;
import io.haedoang.springelasticsearch.product.application.dto.ProductSaveRequest;
import io.haedoang.springelasticsearch.product.application.dto.ProductUpdateRequest;
import io.haedoang.springelasticsearch.product.domain.Product;
import io.haedoang.springelasticsearch.product.infra.ESProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
