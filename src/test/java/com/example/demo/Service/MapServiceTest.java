package com.example.demo.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MapServiceTest {

    @Test
    public void print_givenHashMap_whenSumParallel_thenError() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> sumList = MapService.parallelSum100(map, 100);

        System.out.println("Each index should be equal to 100, but it is not, hence not good");
        for (int i = 0; i < sumList.size(); i++) {
            System.out.println("index : " + i + ", " + sumList.get(i));
        }

        /**
         * Why is that? each index was execute +1 10 times over 4 concurrent threads
         * imagine when we start to add +1 by 4 threads at the same time, each thread read 0 then +1
         * the result expected to be 4 but you see they only add 0 + 1 = 1 for all threads.
         */
    }

    @Test
    public void givenHashMap_whenSumParallel_thenError() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> sumList = MapService.parallelSum100(map, 1000);

        assertNotEquals(1, sumList
                .stream()
                .distinct()
                .count());
        long wrongResultCount = sumList
                .stream()
                .filter(num -> num != 100)
                .count();

        assertTrue(wrongResultCount > 0);
        System.out.println("wrongResultCount = " + wrongResultCount);
    }

    @Test
    public void givenConcurrentMap_whenSumParallel_thenCorrect()
            throws Exception {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        List<Integer> sumList = MapService.parallelSum100(map, 1000);

        assertEquals(1, sumList
                .stream()
                .distinct()
                .count());
        long wrongResultCount = sumList
                .stream()
                .filter(num -> num != 100)
                .count();

        assertEquals(0, wrongResultCount);
        System.out.println("wrongResultCount = " + wrongResultCount);
    }
}
