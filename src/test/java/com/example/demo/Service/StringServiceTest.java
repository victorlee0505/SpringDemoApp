package com.example.demo.Service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StringServiceTest {

    /**
     *  This is a test class that use Spring Component (StringService), 
     *  hence we need to bring Test class as part of the Spring by annotating
     *  @SpringBootTest
     *  @RunWith(SpringJUnit4ClassRunner.class)
     */

    @Autowired StringService stringService;
    
    @Test 
    public void testStringService() {
        String name = "Tiffany";
        String target = "ff";
        String replace = "aa";
        String expected = "Tiaaany";
        String actual = stringService.replaceString(name, target, replace);
        assertEquals(expected, actual);
    }

}
