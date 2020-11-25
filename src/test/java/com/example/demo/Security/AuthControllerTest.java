package com.example.demo.Security;

import static com.example.demo.TestUtil.TestUtil.printClassEnd;
import static com.example.demo.TestUtil.TestUtil.printClassStart;
import static com.example.demo.TestUtil.TestUtil.printEnd;
import static com.example.demo.TestUtil.TestUtil.printStart;
import static org.junit.Assert.assertTrue;

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
    private AuthController authController;

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
    public void testToken(){
        printStart();
        String token = authController.getManagementApiToken();
        assertTrue(token.length()>0);
        printEnd();
    }
}
