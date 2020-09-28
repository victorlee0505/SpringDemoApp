package com.example.demo.Common;

public class CommonStringUtils {

    public static boolean containChar(String input, char target) { 
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == target) {
                return true;
            } 
        }
        return false;
    } 
    
}
