package io.haedoang.springelasticsearch.product.application.dto.ErrorResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
public class ErrorResponse {
    private String message;

    public static ErrorResponse valueOf(String message) {
        return new ErrorResponse(message);
    }
}
