package io.haedoang.springelasticsearch.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ConvertUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T, R> R documentToObject(String document, Class<R> typeToken) throws JsonProcessingException {
        return objectMapper.readValue(document, typeToken);
    }

    public static <T> String objectToDocument(T t) throws JsonProcessingException {
        return objectMapper.writeValueAsString(t);
    }
}
