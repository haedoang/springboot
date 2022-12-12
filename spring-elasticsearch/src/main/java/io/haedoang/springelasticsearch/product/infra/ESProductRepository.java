package io.haedoang.springelasticsearch.product.infra;

import io.haedoang.springelasticsearch.product.application.dto.ProductSaveRequest;
import io.haedoang.springelasticsearch.product.application.dto.ProductUpdateRequest;
import io.haedoang.springelasticsearch.product.domain.Product;
import io.haedoang.springelasticsearch.util.ConvertUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ESProductRepository {
    public static AtomicLong counter = new AtomicLong();
    public static final String SCHEMA_NAME = "product";
    public static final String DOCUMENT_TYPE = "doc";
    private final RestHighLevelClient restHighLevelClient;

    public Product findById(String id) {
        try {
            GetResponse response = restHighLevelClient.get(new GetRequest(SCHEMA_NAME, DOCUMENT_TYPE, id), RequestOptions.DEFAULT);
            if (!response.isExists()) {
                throw new NoSuchElementException("데이터가 존재하지 않습니다 id: " + id);
            }
            return ConvertUtils.documentToObject(response.getSourceAsString(), Product.class);
        } catch (IOException e) {
            log.error("error: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    public Product update(String id, ProductUpdateRequest request) {
        try {
            UpdateResponse response = restHighLevelClient.update(new UpdateRequest(SCHEMA_NAME, DOCUMENT_TYPE, id)
                    .doc(ConvertUtils.objectToDocument(request), XContentType.JSON));

            if (response.status() != RestStatus.OK) {
                throw new RuntimeException("업데이트에 실패하였습니다. " + response.getResult().getLowercase());
            }

            return request.toEntity(id);
        } catch (IOException e) {
            log.error("error: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public Product save(ProductSaveRequest request) {
        try {
            Product product = request.toEntity(counter.incrementAndGet());
            IndexResponse response = restHighLevelClient.index(new IndexRequest(SCHEMA_NAME, DOCUMENT_TYPE, String.valueOf(product.getId()))
                    .source(ConvertUtils.objectToDocument(product), XContentType.JSON));

            if (response.status() != RestStatus.CREATED) {
                throw new RuntimeException("저장에 실패하였습니다. " + response.getResult().getLowercase());
            }

            return request.toEntity(counter.get());
        } catch (IOException e) {
            log.error("error: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteById(String id) {
        try {
            DeleteResponse response = restHighLevelClient.delete(new DeleteRequest(SCHEMA_NAME, DOCUMENT_TYPE, id), RequestOptions.DEFAULT);
            if (response.status() == RestStatus.NOT_FOUND) {
                throw new RuntimeException("데이터가 존재하지 않습니다 id: " + id);
            }
        } catch (IOException e) {
            log.error("error: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }
}
