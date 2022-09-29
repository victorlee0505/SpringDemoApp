package com.example.demo.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

public class StringCombination {

   public static void main(String[] args) {
      // c = Arrays.asList("aa","bb","cc").toArray(new String[0]);
      // permutation(0);
      // System.out.println("Number of permutations = " + count);

      Calendar date = Calendar.getInstance();
      date.set(1990, 0, 01);

      System.out.println(StringCombination.dateToIntString(date.getTime()));

      String firstName = "";
      String middleName = "middleName";
      String lastName = "lastName";

      System.out.println(StringCombination.concatName(lastName, firstName, middleName));

   }

   static String[] c;
   static int count = 0;

   static void swap(int pos1, int pos2) {
      String temp = c[pos1];
      c[pos1] = c[pos2];
      c[pos2] = temp;
   }

   public static void permutation(int start) {
      if (start != 0) {
         for (int i = 0; i < start; i++)
            System.out.print(c[i]);
         System.out.println();
         count++;
      }

      for (int i = start; i < c.length; i++) {
         swap(start, i);
         permutation(start + 1);
         swap(start, i);
      }
   }

   public static String dateToIntString(Date date) {

      Calendar cal = Calendar.getInstance((TimeZone.getTimeZone("America/New_York")));
      cal.setTime(date);

      StringBuilder sb = new StringBuilder();
      sb.append(cal.get(Calendar.YEAR));
      if (cal.get(Calendar.MONTH) + 1 < 10) {
         sb.append(0);
      }
      sb.append(cal.get(Calendar.MONTH) + 1);
      if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
         sb.append(0);
      }
      sb.append(cal.get(Calendar.DAY_OF_MONTH));
      return sb.toString();
   }

   public static String concatName(String lastName, String firstName, String middleName) {
      StringBuilder sb = new StringBuilder();

      if (StringUtils.isNotBlank(lastName)) {
         sb.append(lastName);
      } else {

      }

      if (StringUtils.isNotBlank(firstName) || StringUtils.isNotBlank(middleName)) {
         sb.append(", ");
      }

      if (StringUtils.isNotBlank(firstName)) {
         sb.append(firstName);
      } else {

      }

      if (StringUtils.isNotBlank(middleName)) {
         if (StringUtils.isNotBlank(firstName)) {
            sb.append(" ");
         }
         sb.append(middleName);
      } else {

      }

      return sb.toString();
   }
}
