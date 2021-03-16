package com.zubaray.data.binder.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zubaray.data.binder.models.MyDataDto;

public class TestUtils {

    public static String buildUrlEncodedFormEntity(String... params) {
        if ((params.length % 2) > 0) {
            throw new IllegalArgumentException("Need to give an even number of parameters");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < params.length; i += 2) {
            if (i > 0) {
                result.append('&');
            }
            try {
                result.append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).append('=')
                        .append(URLEncoder.encode(params[i + 1], StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return result.toString();
    }

    public static MyDataDto getValidDto() {
        MyDataDto dto = new MyDataDto();
        dto.setName("jason");
        dto.setAge(5);
        return dto;
    }

    public static String getValidSnakeCaseDto() {
        return "{" + "\"first_name\": \"snake\"," + "\"last_name\": \"case\"" + "}";
    }

    public static String getRequestJson(Object anObject) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(anObject );
        return requestJson;
    }
}
