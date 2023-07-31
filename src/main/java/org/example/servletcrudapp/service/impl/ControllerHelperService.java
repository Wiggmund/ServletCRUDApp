package org.example.servletcrudapp.service.impl;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ControllerHelperService {
    public static Map<String, String> parseRequestBody(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        reader.close();

        Map<String, String> parameters = new HashMap<>();
        String[] keyValuePairs = requestBody.toString().split("&");

        for (String keyValuePair : keyValuePairs) {
            String[] keyValue = keyValuePair.split("=");
            String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
            String value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8);

            parameters.put(key, value);
        }

        return parameters;
    }
}
