package com.example.demo.MiscAlg;

/**
 * Length of all employee ID if id 1 is 1 in length & id 10 is 2 & id 100 is 3
 * 
 * What is the length of all 238 employee's ids
 */
public class CountString {

    public static int lengthofId(int noOfEmployee){

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= noOfEmployee; i++) {
            sb.append(i);
        }

        String result = sb.toString();

        return result.length();
    }

    public static void main(String[] args) {

        System.out.println(CountString.lengthofId(283));
    }

}
