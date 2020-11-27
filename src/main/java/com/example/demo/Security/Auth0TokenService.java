package com.example.demo.Security;

import java.time.LocalDateTime;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class Auth0TokenService {

    private static final Logger LOG = LoggerFactory.getLogger(Auth0TokenService.class);

    @Value("${com.auth0.clientId}")
    private String clientId;
    @Value("${com.auth0.clientSecret}")
    private String clientSecret;
    @Value("${com.auth0.audience}")
    private String audience;
    @Value("${com.auth0.token.url}")
    private String tokenUrl;

    private Auth0Token token;

    @PostConstruct
    public void setup(){
        getManagementApiToken();
    }

    public String getManagementApiToken() throws HttpClientErrorException{

        if(this.token != null){
            LOG.info("Token Exist");
            if(isTokenActive()){
                return this.token.getAccess_token();
            }
        }

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("audience", audience);
        params.add("grant_type", "client_credentials");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Auth0Token> result = restTemplate.postForEntity(tokenUrl, params, Auth0Token.class);

        this.token = result.getBody();

        LOG.info("Token : {}", this.token.getAccess_token());
        LOG.info("Type : {}", this.token.getToken_type());
        LOG.info("Expiry : {}", this.token.getExpires_in());

        return this.token.getAccess_token();
    }

    public boolean isTokenActive(){

        if(this.token != null){

            DecodedJWT jwt = JWT.decode(this.token.getAccess_token());

            LocalDateTime now = LocalDateTime.now();

            LocalDateTime issueAt = LocalDateTime.ofInstant(jwt.getIssuedAt().toInstant(), TimeZone.getTimeZone("America/New_York").toZoneId());
            LOG.info("issueAt: [{}]", issueAt);
            LocalDateTime expiresAt = LocalDateTime.ofInstant(jwt.getExpiresAt().toInstant(), TimeZone.getTimeZone("America/New_York").toZoneId());
            LOG.info("expiresAt: [{}]", expiresAt);

            if( now.isAfter(issueAt) && now.isBefore(expiresAt)){
                return true;
            }
        }
        return false;
    }

    public DecodedJWT getJWTToken() {
        return getJWTToken(null);
    }

    public DecodedJWT getJWTToken(String bearerToken) {
        if(bearerToken != null) {
            return JWT.decode(bearerToken);
        } else {
            return JWT.decode(this.token.getAccess_token());
        }
    }

}
