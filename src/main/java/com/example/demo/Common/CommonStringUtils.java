package com.example.demo.Common;

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
    
}
