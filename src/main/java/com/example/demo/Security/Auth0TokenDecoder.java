package com.example.demo.Security;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.apache.commons.lang3.StringUtils;

public class Auth0TokenDecoder {

    private String token;
    private DecodedJWT jwt;

    // Payload
    private String issuer;
    private List<String> audience;
    private LocalDateTime expiresAt;
    private LocalDateTime issuedAt;
    private Map<String, Claim> claims;

    public Auth0TokenDecoder(String token) {

        if(token.length() > 0){
            this.token = extractToken(token);
            this.jwt = decodeToken();

            this.issuer = this.jwt.getIssuer();
            this.audience = this.jwt.getAudience();
            this.expiresAt = LocalDateTime.ofInstant(this.jwt.getExpiresAt().toInstant(), TimeZone.getTimeZone("America/New_York").toZoneId());
            this.issuedAt = LocalDateTime.ofInstant(this.jwt.getIssuedAt().toInstant(), TimeZone.getTimeZone("America/New_York").toZoneId());
            this.claims = this.jwt.getClaims();
        }
    }

    public String getToken() {
        return token;
    }

    public DecodedJWT getJwt(){
        return jwt;
    }

    public String getIssuer() {
        return issuer;
    }

    public List<String> getAudience() {
        return audience;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public Map<String, Claim> getClaims() {
        return claims;
    }

    public DecodedJWT decodeToken(){
        return JWT.decode(this.token);
    }

    private String extractToken(String token){
        if(StringUtils.startsWithIgnoreCase(token, "Bearer ")){
			String temp = StringUtils.substringAfter(token, " ");
			return temp;
		} else {
			return token;
		}
    }

    @Override
    public String toString() {
        return "Auth0TokenDecoder [audience=" + audience + ", claims=" + claims + ", expiresAt=" + expiresAt
                + ", issuedAt=" + issuedAt + ", issuer=" + issuer + ", token=" + token
                + "]";
    }

}
