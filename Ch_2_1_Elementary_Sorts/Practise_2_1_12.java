package Ch_2_1_Elementary_Sorts;

import edu.princeton.cs.algs4.*;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import java.util.*;

public class Practise_2_1_12 {
    static long compares = 0;
    public static void shell(Double[] a) {
       Stopwatch timer = new Stopwatch();
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        StdOut.printf("=========== 规模 : %d ============\n", N);
        while (h >= 1) {
            compares = 0;
            for (int i = h; i < N; i++) {
                Double t = a[i]; int j;
                for (j = i - h; j >= 0 && less(t, a[j]); j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
            StdOut.printf("h = %6d \t %d\n",h, compares / N);
            h /= 3; 
        }
        StdOut.println();
        StdOut.println();
    }
    public static boolean less(Comparable v, Comparable w) {
        compares++;
        return v.compareTo(w) < 0;
    }
    public static void main(String[] args) {
        for (int i = 2; i < 8; i++) {
            Double[] d = Doubles((int)Math.pow(10, i));
            shell(d);
            assert isSorted(d);
        }
    }
    // output
    /*
     *  =========== 规模 : 100 ============
        h =     40            1
        h =     13            2
        h =      4            2
        h =      1            3
        
        
        =========== 规模 : 1000 ============
        h =    364            1
        h =    121            2
        h =     40            2
        h =     13            3
        h =      4            3
        h =      1            3
        
        
        =========== 规模 : 10000 ============
        h =   9841            0
        h =   3280            1
        h =   1093            2
        h =    364            2
        h =    121            3
        h =     40            4
        h =     13            5
        h =      4            4
        h =      1            3
        
        
        =========== 规模 : 100000 ============
        h =  88573            0
        h =  29524            1
        h =   9841            2
        h =   3280            2
        h =   1093            3
        h =    364            4
        h =    121            5
        h =     40            7
        h =     13           10
        h =      4            4
        h =      1            3
        
        
        =========== 规模 : 1000000 ============
        h = 797161            0
        h = 265720            1
        h =  88573            2
        h =  29524            2
        h =   9841            3
        h =   3280            4
        h =   1093            5
        h =    364            7
        h =    121           11
        h =     40           14
        h =     13            9
        h =      4            5
        h =      1            3
        
        
        =========== 规模 : 10000000 ============
        h = 7174453               0
        h = 2391484               1
        h = 797161            2
        h = 265720            3
        h =  88573            3
        h =  29524            4
        h =   9841            5
        h =   3280            7
        h =   1093           10
        h =    364           15
        h =    121           20
        h =     40           24
        h =     13           12
        h =      4            5
        h =      1            3


     */
}   
