package com.education.listen.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonMapper() {
        throw new IllegalAccessError("Cannot access JsonMapper.class");
    }

    public static String toJsonString(Object o) {

        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            jsonString = o.toString();
        }
        return jsonString;
    }

    public static <T> T toObject(String jsonString, Class<T> valueType) throws IOException {
        return objectMapper.readValue(jsonString, valueType);
    }
}
