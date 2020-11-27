package com.example.demo.Security;

import static com.example.demo.TestUtil.TestUtil.printClassEnd;
import static com.example.demo.TestUtil.TestUtil.printClassStart;
import static com.example.demo.TestUtil.TestUtil.printEnd;
import static com.example.demo.TestUtil.TestUtil.printStart;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthControllerTest {
    private static final Logger LOG = LoggerFactory.getLogger(AuthControllerTest.class);

    @Autowired
    private Auth0TokenService tokenService;

    @BeforeClass
    public static void classStart() {
        printClassStart();
    }

    /** Print end of test class. */
    @AfterClass
    public static void classEnd() {
        printClassEnd();
    }

    @Test
    public void testToken() {
        printStart();
        String token = tokenService.getManagementApiToken();
        assertTrue(token.length() > 0);

        try {
            Thread.sleep(10000);
            token = tokenService.getManagementApiToken();
            assertTrue(token.length() > 0);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        printEnd();
    }

    @Test
    public void testDecodeToken() {
        printStart();
        String token = tokenService.getManagementApiToken();
        assertTrue(token.length() > 0);

        DecodedJWT jwt = JWT.decode(token);

        LOG.info("Token: {}", jwt.getAlgorithm());
        LOG.info("Token: {}", jwt.getAudience());
        LOG.info("Token: {}", jwt.getClaims());
        LOG.info("Token: {}", jwt.getIssuedAt());
        LOG.info("Token: {}", jwt.getExpiresAt());

        printEnd();
    }

    @Test
    public void testisTokenActive() {
        printStart();

        boolean result = tokenService.isTokenActive();

        assertTrue(result);

        
        printEnd();
    }
}
