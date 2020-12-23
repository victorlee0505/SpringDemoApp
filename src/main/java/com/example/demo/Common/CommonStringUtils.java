package com.example.demo.Common;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;

public class CommonStringUtils {

    public static boolean containChar(String input, char target) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean containStrNoCase(String input, String target) {
        String x = "Hello world";
        boolean result = false;
        result = StringUtils.containsIgnoreCase(input, target);
        return result;
    }

    public static String convertToString(Map<String, String> map) {
        return StringUtils.join(map);
    }

    public static String convertToJson(Map<String, String> map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
    }
    
}
