package com.example.demo.MiscAlg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem statement: Write a function that, given a list and a target sum,
 * returns zero-based indices of any two distinct elements whose sum is equal to
 * the target sum.
 * If there are no such elements, the function should return null.
 * For example,
 * findTwoSum(new int[] { 3, 1, 5, 7, 5, 9 }, 10) should return a single
 * dimensional array with two elements and contain any of the following pairs of
 * indices:
 * 0 and 3 (or 3 and 0) as 3 + 7 = 10
 * 1 and 5 (or 5 and 1) as 1 + 9 = 10
 * 2 and 4 (or 4 and 2) as 5 + 5 = 10
 * 
 */

public class TwoSum {

    public static int[] findTwoSum(int[] input, int sum) {

        Map<Integer, Integer> hmap = new HashMap<>();

        for (int i = 0; i < input.length; i++) {

            // include index , value if not memoized
            if (!hmap.containsKey(i)) {
                hmap.put(i, input[i]);
            }

            int remain = sum - input[i];

            // look if a remainder exist in map.
            if (hmap.containsValue(remain)) {
                // look fo index of remainder
                for (Map.Entry<Integer, Integer> set : hmap.entrySet()) {
                    if (set.getValue().equals(remain)) 

                        if (i != set.getKey()) {
                            return new int[] { i, set.getKey() };
                        }
                    }
                }

            }

        return null;
    }

    public static void main(String[] args) {

        int[] input = new int[] { 3, 1, 5, 7, 5, 9 };

        int[] pair = findTwoSum(input, 10);

        System.out.println(Arrays.toString(pair));

    }

}
