package com.example.demo.MiscAlg;

import java.util.HashSet;
import java.util.Set;

public class MergeUnique {
    
    public static String[] uniqueNames(String[] names1, String[] names2) {
        Set<String> uniqueSet = new HashSet<String>();

        for (String name : names1) {
            uniqueSet.add(name);
        }

        for (String name : names2) {
            uniqueSet.add(name);
        }

        return uniqueSet.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] names1 = new String[] {"Ava", "Emma", "Olivia"};
        String[] names2 = new String[] {"Olivia", "Sophia", "Emma"};

        System.out.println(String.join(", ", MergeUnique.uniqueNames(names1, names2))); // should print Ava, Emma, Olivia, Sophia
    }
}
