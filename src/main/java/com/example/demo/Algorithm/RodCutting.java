package com.example.demo.Algorithm;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RodCutting {

    private static final Logger LOG = LoggerFactory.getLogger(RodCutting.class);

    // Reference Table
    public static Map<Integer, Integer> priceTable = new HashMap<Integer, Integer>();

    public static Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public static Map<Integer, Integer> size = new HashMap<Integer, Integer>();

    /**
     * Normal Recursion No detail on cut
     * 
     * @param length
     * @return
     */
    public static int cutRod(int length) {
        // Base case
        if (length <= 0) {
            return 0;
        }

        int q = Integer.MIN_VALUE;

        for (int i = 1; i <= length; i++) {
            q = maxProfit(q, (priceTable.get(i) + cutRod(length - i)));
            LOG.info("i[{}] , currentMax [{}]", i, q);
        }

        return q;
    }

    public static int maxProfit(int currentMax, int newMax) {

        if (currentMax < newMax) {
            return newMax;
        }
        return currentMax;
    }

    // Dynamic Programming

    public static int cuttingRod(int length) {

        int totalRev = 0;

        if (length > priceTable.size()) {
            // find the best cut for extra long rod
            int bestCut = cutRodBottomUp(priceTable.size());
            while (length > priceTable.size()) {
                totalRev += bestCut;
                LOG.info("cut [{}]", size.get(priceTable.size()));
                length = length - priceTable.size();
            }

            totalRev += memo.get(length);

        } else {
            totalRev += cutRodBottomUp(length);
        }

        while (length > 0) {
            LOG.info("cut [{}]", size.get(length));
            length = length - size.get(length);
        }

        return totalRev;
    }

    public static int cutRodBottomUp(int length) {
        // Base Case: Nothing left to cut, no value
        memo.put(0, 0);

        for (int i = 1; i <= length; i++) {
            memo.put(i, null);
        }

        int revenue = cutRodAux(length);

        return revenue;
    }

    public static int cutRodAux(int length) {

        if (memo.get(length) == null) {
            // do something
            int q = Integer.MIN_VALUE;
            for (int i = 1; i <= length; i++) {

                int temp = priceTable.get(i) + cutRodAux(length - i);

                if (q < temp) {
                    q = temp;
                    size.put(length, i);
                }
                LOG.info("i[{}] , currentMax [{}]", i, q);
                memo.put(length, q);
            }
        }

        return memo.get(length);

    }

    /**
     * Initialize Price Table
     */
    public static void refTableSetup() {
        priceTable.put(1, 1);
        priceTable.put(2, 5);
        priceTable.put(3, 8);
        priceTable.put(4, 9);
        priceTable.put(5, 10);
        priceTable.put(6, 17);
        priceTable.put(7, 17);
        priceTable.put(8, 20);
        priceTable.put(9, 24);
        priceTable.put(10, 30);
    }

    public static void main(String[] args) {

        refTableSetup();
        // System.out.println(cutRod(5));

        // System.out.println(cutRodBottomUp(9));

        System.out.println(cuttingRod(29));

    }

}
