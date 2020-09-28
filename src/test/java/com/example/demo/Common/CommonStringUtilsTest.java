package com.example.demo.Common;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CommonStringUtilsTest {
    
    @Test 
    public void testContainChar() {
        String name = "Tiffany";
        char target = 'i';
        boolean actual = CommonStringUtils.containChar(name, target);
        assertTrue(actual);
    }
    
    @Test 
    public void testContainStrNoCase() {
        String name = "Tiffany";
        String target = "I";
        boolean actual = CommonStringUtils.containStrNoCase(name, target);
        assertTrue(actual);
    }
    
}
