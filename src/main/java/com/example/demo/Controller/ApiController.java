package com.example.demo.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * First Example of REST api
 * Request Mapping to localhost:8080/api/greeting
 */
@RestController
@RequestMapping("/api/greeting")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiController {

    private static final Logger LOG = LoggerFactory.getLogger(ApiController.class);

    /**
     * Default greeting as String
     * http://localhost:8080/api/greeting
     */
    @GetMapping
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "204", content = @Content(mediaType = "text/plain"), description = "204"),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "text/plain"), description = "500 Please try again. If error persist, contact administrator") })
    @SuppressWarnings("nls")
    public String getDefault() {

        LOG.info("/api/greeting Started");

        String result = "Hello World from Demo";

        LOG.info("/api/greeting Finished");

        return result;

    }

    /**
     * Default greeting as JSON
     * http://localhost:8080/api/greeting/get
     * @return
     */
    @GetMapping(value = { "/get" }, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "204", content = @Content(mediaType = "text/plain"), description = "204"),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "text/plain"), description = "500 Please try again. If error persist, contact administrator") })
    @SuppressWarnings("nls")
    public ResponseEntity<Map<String, String>> getGreeting2() {
        LOG.info("/api/greeting/get Started");
        ResponseEntity<?> entRes = null;
        Map<String, String> response = new HashMap<String, String>();
        response.put("Service", "/api/greeting/get");

        try {
            response.put("Status", "Success");
            response.put("StatusCode", "200");
            response.put("Message", "Hello World from Demo");
        }
        catch (Exception e) {
            // LOG.error("Exception encountered, return 500", e);
            response.put("Status", "Failed");
            response.put("StatusCode", "500");
            response.put("Message", "Failed.");
        }

        entRes = ResponseEntity.status(HttpStatus.OK).body(Collections.unmodifiableMap(response));
        @SuppressWarnings("unchecked")
        final ResponseEntity<Map<String, String>> resEnt = (ResponseEntity<Map<String, String>>) entRes;

        LOG.info("/api/greeting/get Finished");
        return resEnt;
    }

    /**
     * POST greeting as JSON
     * http://localhost:8080/api/greeting/post
     * @return
     */
    @PostMapping(value = { "/post" }, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
                            @ApiResponse(responseCode = "204", content = @Content(mediaType = "text/plain"), description = "204"),
                            @ApiResponse(responseCode = "500", content = @Content(mediaType = "text/plain"), description = "500 Please try again. If error persist, contact administrator") 
                        })
    @SuppressWarnings("nls")
    public ResponseEntity<Map<String, String>> getGreeting3(final @RequestBody(required = false) Map<String,String> body) {

        LOG.info("/api/greeting/post Started");

        ResponseEntity<?> entRes = null;
        Map<String, String> response = new HashMap<String, String>();
        response.put("Service", "/api/greeting/post");

        String name = null;

        if(body != null){
            if(body.containsKey("Name")){
                name = body.get("Name");
            } else {
                name = "No Name Key";
            }
        } else {
            name = "No POST Body";
        }

        try {
            response.put("Status", "Success");
            response.put("StatusCode", "200");
            response.put("Message", "Hello World from Demo, " + name);
        }
        catch (Exception e) {
            // LOG.error("Exception encountered, return 500", e);
            response.put("Status", "Failed");
            response.put("StatusCode", "500");
            response.put("Message", "Failed.");
        }

        entRes = ResponseEntity.status(HttpStatus.OK).body(Collections.unmodifiableMap(response));
        @SuppressWarnings("unchecked")
        final ResponseEntity<Map<String, String>> resEnt = (ResponseEntity<Map<String, String>>) entRes;

        LOG.info("/api/greeting/post Finished");
        return resEnt;
    }
}
