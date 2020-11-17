package com.example.demo.Algorithm;

public class Classic {

    /**
     * inverted triangle in least steps
     * 
     * @param side
     * @return
     */
    public static double leastMove(int side) {

        return Math.floor(side * (side + 1) / 6);

    }

    final static int STEP = 1;
    final static int JUMP = 2;

    /**
     * nCr Combination of moving forward by step and jump
     * 
     * @param distance
     * @return
     */
    public static int moveForward(int distance) {

        if (distance == 1) {
            return STEP;
        }

        if (distance == 2) {
            return JUMP;
        }

        return moveForward(distance - STEP) + moveForward(distance - JUMP);

    }

}
