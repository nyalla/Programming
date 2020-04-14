package com.example.demo;

import java.lang.reflect.Array;
import java.util.*;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;


public class SampleTest
{
    private static Long aaa;

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

    static void countApplesAndOranges(int s, int t, int a, int b, int[] apples, int[] oranges)
    {
        int fallenApples = 0;
        int fallenOranges = 0;

        for (int i = 0; i < apples.length; i++)
        {
            if (a + apples[i] >= s && a + apples[i] <= t)
                fallenApples++;
        }

        for (int i = 0; i < oranges.length; i++)
        {
            if (b + oranges[i] >= s && b + oranges[i] <= t)
                fallenOranges++;
        }
        System.out.println(fallenApples);
        System.out.print(fallenOranges);
    }

    // Complete the kangaroo function below.
    static String kangaroo(int x1, int v1, int x2, int v2)
    {
        while (x1 <= x2)
        {
            if (x1 == x2)
            {
                return "YES";
                //System.exit(0);
            }
            x1 += v1;
            x2 += v2;
        }
        return "NO";
    }


//5,99,139
//5,72,662

    static int getMoneySpent(int[] keyboards, int[] drives, int b)
    {
        /*
         * Write your code here.
         */
        int max = 0;
        for (int i = 0; i < keyboards.length; i++)
        {

            for (int j = 0; i < drives.length; j++)
            {
                int sum = keyboards[i] + drives[j];
                if (sum < b && sum < max)
                    max = sum;
            }
        }

        return max == 0 ? -1 : max;

    }


    // Complete the cutTheSticks function below.
    static int[] cutTheSticks(int[] arrq)
    {
        int arr[] = {5, 4, 4, 2, 2, 8};
        int sum = 0;
        int[] cuts = new int[arr.length];

        for (int i = 0; i < arr.length; i++)
            sum = sum + arr[i];

        int index = 0;
        while (sum > 0)
        {
            int first = 0;

            for (int i = 0; i < arr.length; i++)
                if (arr[i] == 0) continue;
                else
                    first = arr[i] < arr[i++] ? arr[i] : arr[i++];

            int cnt = 0;
            for (int i = 0; i < arr.length; i++)
                if (arr[i] > 0)
                {
                    cnt++;
                    arr[i] = arr[i] - first;
                }

            cuts[index++] = cnt;
            sum = 0;
            for (int i = 0; i < arr.length; i++)
                sum = sum + arr[i];
        }
        return arr;
    }


    public static int nonDivisibleSubset(int k, List<Integer> s)
    {
        // Write your code here
        k = 7;
        s.addAll(Arrays.asList(278, 576, 496, 727, 410, 124, 338, 149, 209, 702, 282, 718, 771, 575, 436));
        TreeSet<Integer> numSet = new TreeSet<Integer>();
        for (int i = 0; i < s.size(); i++)
        {
            //System.out.println("IIII index  " + i);
            for (int j = i + 1; j < s.size(); j++)
            {
                int rem = (s.get(i) + s.get(j)) % k;
                if (rem != 0)
                {
                    numSet.add(s.get(i));
                    numSet.add(s.get(j));
                    //System.out.println("(" + s.get(i) + "," + s.get(j) + ")");
                    System.out.println(s.get(i) + "\t" + s.get(j));
                }
            }
        }
        //System.out.println(numSet);
        return numSet.size();
    }

/*
Two different numbers n1 and n2 are divisible by k if and only if:
        (n1 % k) + (n2 % k) = k
    We can verify this using some examples:
    n1 = 10, n2 = 12, k = 3
    The sum of these numbers is not divisible:
        10 % 3 + 12 % 3 = 1 (NOT equal to k = 3)
    Similarly,
    n1 = 10, n2 = 11, k = 3
    The sum of these numbers is divisible:
        10 % 3 + 11 % 3 = 3 (EQUAL to k = 3)
    There are some special conditions to note such as:
        1. Remainders for some numbers are 0
        2. Remainders for some numbers are equal to k / 2 (only applicable for even values of k)
    In both the above cases, we will consider only one of the numbers falling into one of above conditions to avoid counting both.

    We can solve this problem by computing modulo of array numbers with K.
    if sum of two numbers is divisible by K, then if one of them gives remainder i, other will give remainder (K – i).
    First we store frequencies of numbers giving specific remainder in a frequency array of size K.
    Then we loop for all remainders i and include max(f[i], f[K – i]).
    Why? a subset with no pair sum divisible by K must include either elements with remainder f[i] or with remainder f[K – i].
    Since we want to maximize the size of subset, we pick maximum of two sizes.
    */

    static int nonDivisibleSubset(int k, int[] Sa)
    {
        // **** declare and populate array of remainders ****
        k = 7;
        //int[] S = new int[] {1, 7, 2, 4};
        List<Integer> S = new ArrayList<>(Arrays.asList(278, 576, 496, 727, 410, 124, 338, 149, 209, 702, 282, 718, 771, 575, 436));
        int[] remainderArr = new int[k];

        for (int n : S)
        {
            System.out.println("n % k (" + n + " % " + k + "): " + n % k);
            remainderArr[n % k]++;
        }

        // ???? ????
        System.out.print("remainderArr: ");
        for (int s : remainderArr)
            System.out.print(s + " ");
        System.out.println("\n");

        // **** set initial number of elements in the subset ****
        int zeroRemainder = remainderArr[0];

        // ???? ????
        System.out.println("        zeroRemainder: " + zeroRemainder);

        // **** consider only one of the numbers ****
        int numOfElementsInSubset = (zeroRemainder > 0) ? 1 : 0;

        // ???? ????
        System.out.println("numOfElementsInSubset: " + numOfElementsInSubset + "\n");

        // **** ****
        for (int i = 1; i <= (k / 2); i++)
        {

            // ???? ????
            System.out.println("i: " + i + " k - i: " + (k - i));

            // **** ****
            if (i != k - i)
            {
                numOfElementsInSubset += Math.max(remainderArr[i], remainderArr[k - i]);
            }
            else
            {
                numOfElementsInSubset++;
            }

            // ???? ????
            System.out.println("numOfElementsInSubset: " + numOfElementsInSubset);
        }

        // **** return the number of elements in the subset ****
        return numOfElementsInSubset;
    }

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n)
    {

       /* s = "a";
        n = 100000;
        String holder = "";
        int countA = 0;
        //char[] charA = s.toCharArray();
        if (s.length() == 1 && s.equals("a"))
            return n;
        System.out.println("Before " + holder.length());
        while (holder.length() < n)
        {
            holder = holder + s;
        }
        System.out.println("After " + holder.length());
        //long endIndex=holder.length()-n;
        holder = holder.substring(0, (int) n);

        System.out.println("After sub string " + holder.length());

        for (int i = 0; i < holder.length(); i++)
        {
            if (holder.charAt(i) == 'a')
                countA++;
        }
        System.out.println(countA);
        return countA;*/

        long countForSubstring = 0;
        long totalCount = 0;
        //Determines how many a's are in a substring
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == 'a')
            {
                countForSubstring++;
            }
        }


        //Determines how many complete substrings we will analyze
        long divisor = n / s.length();

        totalCount += divisor * countForSubstring;


        //Determines how many characters in we will analyze towards the end of our n range
        long remainder = n % s.length();

        for (int i = 0; i < remainder; i++)
        {
            if (s.charAt(i) == 'a')
            {
                totalCount++;
            }
        }
        return totalCount;

    }


    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(char[] c)
    {
        /*int jumps = 0;
        int currentStep = 0;
        System.out.println("Current Step: " + currentStep + "\t"+"Jumps: "+jumps);
        int cnt=0;


        while (currentStep < c.length-1)
        {
            //test comment
            if (c.length-currentStep!=1 && c[currentStep + 2] == '0')
            {
                currentStep += 2;
                jumps++;
            }
            else
            {
                currentStep += 1;
                jumps++;
            }
            System.out.println("Current Step: " + currentStep + "\t"+"Jumps: "+jumps);
        }
        System.out.println("final: " + jumps);
        return jumps;*/
        int jumps = 0;

        int i = 0;
        while (i < c.length - 3) //goes through all clouds up until the last jump
        {
            i += (c[i + 2] == 0) ? 2 : 1;
            jumps++;
        }

        jumps++;//This is the last jump that will be either a 1 or 2 comment

        return jumps;
    }


    // Complete the equalizeArray function below.
    static int equalizeArray(int[] arr)
    {

        Arrays.stream(arr).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .ifPresent(i -> getVal(Long.valueOf(i.getValue())));

        return (int) (arr.length - aaa);
    }

    public static void getVal(Long aa)
    {
        aaa = aa;
    }



    /*
        There are a number of people who will be attending ACM-ICPC World Finals. Each of them may be well versed in a number of topics. Given a list of topics known by each attendee, you must determine the maximum number of topics a 2-person team can know. Also find out how many ways a team can be formed to know that many topics. Lists will be in the form of bit strings, where each string represents an attendee and each position in that string represents a field of knowledge, 1 if its a known field or 0 if not.

    For example, given three attendees' data as follows:

    10101
    11110
    00010
    These are all possible teams that can be formed:

    Members Subjects
    (1,2)   [1,2,3,4,5]
    (1,3)   [1,3,4,5]
    (2,3)   [1,2,3,4]
                */
    static int[] acmTeam(String[] topic)
    {

        int size = topic.length;
        int maxTopics = 0;
        int maxTeams = 0;
        int noOfDigits = topic[0].length();
        Map<Integer, Integer> holder = new HashMap<>();
        for (int i = 0; i < size; i++)
        {
            for (int j = i + 1; j < size; j++)
            {
                int same = 0;
                for (int k = 0; k < noOfDigits; k++)
                {
                    if (topic[i].charAt(k) == '1' || topic[j].charAt(k) == '1')
                    {
                        same++;
                    }
                    /*if (holder.containsKey(same))
                        holder.put(same,holder.get(same)+1);
                    else
                        holder.put(same,1);*/
                }
                if (same >= maxTopics)
                {
                    maxTeams = same == maxTopics ? maxTeams + 1 : 1;
                    maxTopics = same;

                }
            }

        }
        int[] c = new int[]{maxTopics, maxTeams};

        return c;
    }


    public static void main(String args[])
    {
        //int[] cuts = new int[0];
        //List<Integer> sss = new ArrayList<>();
        //char[] c = new char[] {'0', '0', '1', '0', '0', '1', '0'};
        //int[] c = new int[]{1, 3, 4, 3, 4, 3, 2, 3, 3, 3, 3, 3};
        //acmTeam(new String[]{"10101", "11100", "11010", "00101"});
    }
    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles)
    {
            /*
                    0   0   0   x
                    0   0   0   0
                    0   0   0   0
                    0   0   0   0
                            */
        int[][] board = new int[n][n];
        int[][] totalCells = new int[2][n * n];
        board[r_q][c_q] = 1;
        int count = 0;
        //count rows
        count = count + n - 1;
        //count columns
        count = count + n - 1;


        return 1;

    }

}