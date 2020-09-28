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
}
