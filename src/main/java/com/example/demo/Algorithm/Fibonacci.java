package com.example.demo.Algorithm;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    /**
     * Classic Fibonacci: SLOW
     */
    public static int fibonacci(int term) {

        if (term == 0) {
            return 0;
        }

        if (term == 1) {
            return 1;
        }

        return fibonacci(term - 1) + fibonacci(term - 2);
    }

    // DP Memo
    public static Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    // Use Memo to get existing result from previous calculation
    public static int fibonacciDP(int term) {
        if (memo.get(term) == null) {
            memo.put(term, fibonacciDP(term - 1) + fibonacciDP(term - 2));
        }

        return memo.get(term);
    }

    // Top Down Recursion (use more memory for recurrsion stack)
    public static int fibonacciTopDown(int term) {

        // Base Case
        memo.put(0, 0);
        memo.put(1, 1);

        // initiate NULL to memo for all terms
        for (int i = 2; i <= term; i++) {
            memo.put(i, null);
        }

        // Recurrsively drill down and build MEMO
        fibonacciDP(term);

        return memo.get(term);
    }

    // Bottom Up Iteration (fast and less memory)
    public static int fibonacciBottomUp(int term) {

        // Base Case
        memo.put(0, 0);
        memo.put(1, 1);

        // Iterate and build MEMO from base case
        for (int i = 2; i <= term; i++) {
            memo.put(term, fibonacciDP(term - 1) + fibonacciDP(term - 2));
        }

        return memo.get(term);
    }

    public static void main(String[] args) {

        int term = 45;

        // SLOW
        System.out.println(fibonacci(term));

        // TOP DOWN RECUR
        System.out.println(fibonacciTopDown(term));

        // BOTTOM UP ITERA
        System.out.println(fibonacciBottomUp(term));
    }
}
