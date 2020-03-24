package com.example.demo;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SampleTest
{
    // Complete the birthdayCakeCandles function below.
    //Get ax element from the array and check how many times it repeated in the same array:
    static int birthdayCakeCandles(int[] ar)
    {
        int max = 0;
        int count = 0;
        for (int i = 0; i < ar.length; i++)
        {
            if (ar[i] > max)
            {
                max = ar[i];
            }
        }
        for (int i = 0; i < ar.length; i++)
        {
            if (ar[i] == max)
            {
                count++;
            }
        }
        return count;

    }

    static String timeConversion(String str)
    {

        String op = "";
        // Get hours
        int h1 = (int) str.charAt(1) - '0';
        int h2 = (int) str.charAt(0) - '0';
        int hh = (h2 * 10 + h1 % 10);

        // If time is in "AM"
        if (str.charAt(8) == 'A')
        {
            if (hh == 12)
            {
                op += "00";
                for (int i = 2; i <= 7; i++)
                    op += str.charAt(i);
            }
            else
            {
                for (int i = 0; i <= 7; i++)
                    op += str.charAt(i);
            }
        }

        // If time is in "PM"
        else
        {
            if (hh == 12)
            {
                op += "12";
                for (int i = 2; i <= 7; i++)
                    op += str.charAt(i);
            }
            else
            {
                hh = hh + 12;
                op += hh;
                for (int i = 2; i <= 7; i++)
                    op += str.charAt(i);
            }
        }
        return op;
    }

     static List<Integer> gradingStudents(List<Integer> grades)
    {
        // Write your code here
        List<Integer> newGrades = new ArrayList<>();
        grades.add(73);
        for (Integer grade : grades)
        {
            if (grade < 40)
            {
                newGrades.add(grade);
            }
            else
            {
                int nextMultipleOf5 = ((grade / 5) + 1) * 5;
                if (nextMultipleOf5 - grade < 3)
                    grade = nextMultipleOf5;
                newGrades.add(grade);
            }

        }
        return newGrades;
    }
}
