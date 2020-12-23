package com.example.demo.Common;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

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

    @Test
    public void testMaptoString() {
        Map<String, String> params = new HashMap<String, String>();

        params.put("application", "vss");
        params.put("region", "M");
        params.put("operator", "id");

        String text = CommonStringUtils.convertToString(params);
        System.out.println(text);
    }

    @Test
    public void testMaptoJson() {
        Map<String, String> params = new HashMap<String, String>();

        params.put("application", "vss");
        params.put("region", "M");
        params.put("operator", "id");

        String text;
        try {
            text = CommonStringUtils.convertToJson(params);
            System.out.println(text);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
