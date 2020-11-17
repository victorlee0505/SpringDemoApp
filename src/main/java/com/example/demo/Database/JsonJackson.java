package com.example.demo.Database;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.demo.Database.Entity.Country;
import com.example.demo.Database.Entity.Top;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Use Jackson to parse Json. need to have Value Object class.
 */
public class JsonJackson {

        /**
         * GET Request caller
         * @param uri
         * @return
         */
        public String getJson(String uri) {

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);
    
            return entity.getBody();
    
        }
    
        /**
         * VO based JSON parsing. Turning JSON message in to an Java Object
         * @param body
         * @return
         */
        public Top parsingJackson(String body) {
    
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Top top;
            try {
                // Mapping JSON to Entity Class
                top = mapper.readValue(body, Top.class);
                return top;
            } catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    
            return null;
        }
    
        /**
         * Parsing JSON as a Tree
         * @param body
         * @return
         */
        public JsonNode parsingNode(String body) {
    
            JsonFactory factory = new JsonFactory();
    
            ObjectMapper mapper = new ObjectMapper(factory);
            JsonNode rootNode;
            try {
                rootNode = mapper.readTree(body);
                return rootNode;
            } catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    
            return null;
        }
    
        public static void main(String[] args) {

            String uri = "https://jsonmock.hackerrank.com/api/countries";

            JsonJackson jj = new JsonJackson();
            String body = jj.getJson(uri);
    
    
            // Parse into VO
            // Two level: TOP has DATA field that contains Country
            Top top = jj.parsingJackson(body);
            List<Country> data = top.getData();
    
            for (Country country : data) {
                if (country.getName().contains("do")) {
                    System.out.println("Expecting Result: Andorra");
                    System.out.println("Result: " + country.getName());
                }
            }
    
    
            //Parse as Tree
            JsonNode rootNode = jj.parsingNode(body);
    
            //TOP level;
            Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
            // while (fieldsIterator.hasNext()) {
            //     Map.Entry<String,JsonNode> field = fieldsIterator.next();
            //     System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
            // }
    
            //Country Level
            JsonNode dataNode = rootNode.get("data");
    
            if(dataNode.isArray()){
                for (JsonNode jsonNode : dataNode) {
                    System.out.println("Expecting 10 Country's names");
                    System.out.println(jsonNode.get("name"));
                }
            }
        }
    
}
