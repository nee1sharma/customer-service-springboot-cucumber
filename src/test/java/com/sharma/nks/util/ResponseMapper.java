package com.sharma.nks.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public final class ResponseMapper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private ResponseMapper() {
    }

    public static String writeValueAsString(Object value) {
        String s = null;
        try {
            s = MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static <T> T readJson(InputStream input, Class<T> clazz) {
        T value = null;
        try {
            value = MAPPER.readValue(input, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
