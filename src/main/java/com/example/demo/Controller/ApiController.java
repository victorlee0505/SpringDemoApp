package com.example.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
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
}
