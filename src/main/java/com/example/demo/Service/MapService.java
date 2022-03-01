package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class is to experiment concurrent hash map functionality, detail in
 * junit test
 */
public class MapService {

    /**
     * This is multi-threaded incremental (+1) calculation on each K/V
     * for each executionTimes , put test = 0 , then start 10 Processes with 4 threads
     * each Process add 10 to test , hence : 0 + 10 * 10 => test = 100
     * 
     * then add each executionTimes.test to List (ex executionTimes = 100 , then List size is 100)
     * 
     * @param map
     * @param executionTimes
     * @return
     * @throws InterruptedException
     */
    public static List<Integer> parallelSum100(Map<String, Integer> map, int executionTimes) throws InterruptedException {
        List<Integer> sumList = new ArrayList<>(executionTimes);
        for (int i = 0; i < executionTimes; i++) {
            map.put("test", 0);
            ExecutorService executorService = Executors.newFixedThreadPool(4);
            for (int j = 0; j < 10; j++) {
                executorService.execute(() -> {
                    for (int k = 0; k < 10; k++)
                        map.computeIfPresent(
                                "test",
                                (key, value) -> value + 1);
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            sumList.add(map.get("test"));
        }
        return sumList;
    }

}
