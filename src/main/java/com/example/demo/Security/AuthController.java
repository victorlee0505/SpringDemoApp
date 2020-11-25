package com.example.demo.Security;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Controller
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Value("${com.auth0.clientId}")
    private String clientId;
    @Value("${com.auth0.clientSecret}")
    private String clientSecret;
    @Value("${com.auth0.audience}")
    private String audience;
    @Value("${com.auth0.token.url}")
    private String tokenUrl;

    public String getManagementApiToken() {
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("audience", audience);
        params.add("grant_type", "client_credentials");

        // HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(params,
        //         headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Auth0Token> result = restTemplate.postForEntity(tokenUrl, params, Auth0Token.class);

        Auth0Token body = result.getBody();

        LOG.info("Token : {}", body.getAccess_token());
        LOG.info("Type : {}", body.getToken_type());
        // LOG.info("Expiry : {}", body.getExpires_in());

        return body.getAccess_token();
    }

}
