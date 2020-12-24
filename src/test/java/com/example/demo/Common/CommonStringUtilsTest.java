package com.example.demo.Common;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;
import org.json.JSONObject;
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

        Map<String, String> jsonMap = new HashMap<String, String>();

        // jsonMap.put("body", "test1");

        try {
            String jsonParam = CommonStringUtils.convertToJson(params);
            System.out.println(jsonParam);
            jsonMap.put("parameterMap", jsonParam);
            System.out.println(CommonStringUtils.convertToJson(jsonMap));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void testJSONArray() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("application", "vss");
        parameters.put("region", "M");
        parameters.put("operator", "id");

        try {
            JSONObject bodyJson = new JSONObject();
            bodyJson.put("body", "test1");


            JSONObject parameterJson = new JSONObject();
            for (Map.Entry<String, String> param : parameters.entrySet()) {
                parameterJson.put(param.getKey(), param.getValue());
            }

            bodyJson.put("parameterMap", parameterJson);

            String json = bodyJson.toString();

            System.out.println(json);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
