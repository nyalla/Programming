package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SampleTest
{
    public static void main(String args[])
    {
    /*    int base = 670;
//outcomes  - 5268,2568,2658,2685
// 670      - 5670,6570,6750,6705
        String numSt = "";
        int SIZE = 0;
        while (base > 0)
        {
            numSt = numSt + base % 10;
            base = base / 10;
            ++SIZE;
        }

        int MAX_VALUE = 0;
        int POINT = numSt.length() - 1;
        int POSSIBILITIES = SIZE + 1;
        String NEW_VALUE = "";
        while (POSSIBILITIES > 0)
        {
            for (int i = numSt.length() - 1; i >= 0; i--)
            {
                if (i == POINT)
                    NEW_VALUE = NEW_VALUE + "5";
                NEW_VALUE = NEW_VALUE + numSt.charAt(i);
            }
            if (Integer.parseInt(NEW_VALUE) > MAX_VALUE)
                MAX_VALUE = Integer.parseInt(NEW_VALUE);
            POSSIBILITIES--;
            NEW_VALUE = "";
            POINT--;
        }
        System.out.println(MAX_VALUE);
*/



        List<String> li1 = new ArrayList<>();
        li1.add("epjack");
        li1.add("epkate");
        li1.add("jack");
        li1.add("kate");
        li1.add("epsawyer");

        Optional<String> val = li1.stream().filter(i -> i.startsWith("ep")).findFirst();
        String st = val.isPresent() ? val.get() : "NO";
        System.out.println(st);
    Object a="";
        String val1=null;
        for(String i : li1){
            if(String.valueOf(a).startsWith("ep"))
                val1=i;
            break;
        }
        //Boolean st = val.isPresent() ? true : false;
        if(val1!=null){
           // return true;
        }else{
           // return false;
        }

    }
}
