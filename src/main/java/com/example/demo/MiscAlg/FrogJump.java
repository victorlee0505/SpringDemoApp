package com.example.demo.MiscAlg;

import java.util.HashMap;
import java.util.Map;

/**
 * A frog only moves forward, but it can move in steps 1 inch long or in jumps 2
 * inches long. A frog can cover the same distance using different combinations
 * of steps and jumps.
 * 
 * Write a function that calculates the number of different combinations a frog
 * can use to cover a given distance.
 * 
 * For example, a distance of 3 inches can be covered in three ways:
 * step-step-step, step-jump, and jump-step.
 */

public class FrogJump {

    final static long STEP = 1;
    final static long JUMP = 2;

    /**
     * Classic recurssion fibonnaci mod 
     * nCr Combination of moving forward by step and jump
     * 
     * @param distance
     * @return
     */
    public static long moveForward(long distance) {

        if (distance == 0) {
            return 0l;
        }

        if (distance == 1) {
            return STEP;
        }

        if (distance == 2) {
            return JUMP;
        }

        return moveForward(distance - STEP) + moveForward(distance - JUMP);

    }

    // DP Memo
    public static Map<Long, Long> memo = new HashMap<Long, Long>();

    /**
     * nCr Combination of moving forward by step and jump memoized
     * 
     * @param distance
     * @return
     */
    public static long moveForwardDP(long distance) {

        if (!memo.containsKey(distance)) {
            memo.put(distance, moveForwardDP(distance - STEP) + moveForwardDP(distance - JUMP));
        }

        return memo.get(distance);
    }

    public static long moveForwardBottomUp(long distance) {

        // base case
        memo.put(0l, 0l); // distance = 0 , no move
        memo.put(1l, 1l); // distance = 1 , STEP
        memo.put(2l, 2l); // distance = 2 , STEP STEP OR JUMP = 2 ways

        for (long i = 3; i <= distance; i++) {
            memo.put(distance, moveForwardDP(distance - STEP) + moveForwardDP(distance - JUMP));
        }

        return memo.get(distance);
    }

    public static void main(String[] args) {

        long distance = 45;
        long ways = FrogJump.moveForward(distance);
        System.out.println("distance= " + distance + " can be cover in " + ways + " ways");

        long waysDP = FrogJump.moveForwardBottomUp(distance);
        System.out.println("distance= " + distance + " can be cover in " + waysDP + " ways");
    }
}
