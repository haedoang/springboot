package io.haedoang.springelasticsearch.ui;

import io.haedoang.springelasticsearch.application.ProductService;
import io.haedoang.springelasticsearch.application.dto.ErrorResponse.ErrorResponse;
import io.haedoang.springelasticsearch.application.dto.ProductResponse;
import io.haedoang.springelasticsearch.application.dto.ProductSaveRequest;
import io.haedoang.springelasticsearch.application.dto.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("id") String id) {
        ProductResponse response = productService.findById(id);

        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody ProductSaveRequest request) {
        ProductResponse response = productService.save(request);

        return ResponseEntity.created(URI.create("/api/v1/products/" + response.getId()))
                .body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable("id") String id, @RequestBody ProductUpdateRequest request) {
        ProductResponse response = productService.update(id, request);

        return ResponseEntity.ok()
                .body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        productService.deleteById(id);

        return ResponseEntity.noContent()
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
        log.error("error: {}", e.getMessage());
        return ResponseEntity.badRequest().body(ErrorResponse.valueOf(e.getMessage()));
    }
}
