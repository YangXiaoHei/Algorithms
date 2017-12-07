package 第二章_初级排序算法;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_2_1_12 {
    static long compares = 0;
    public static void shell(Double[] a) {
       Stopwatch timer = new Stopwatch();
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) 
                for (int j = i; j >= h && less(a[j], a[j - 1]); j--) {
                    Double t = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = t;
                }
            StdOut.printf("h = %d\tcompare/size = %d\n",h, compares / N);
            compares = 0;
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
        for (int i = 2; i < 10; i++) 
            shell(Doubles((int)Math.pow(10, i)));
    }
    // output
    /*
     *  h = 40  compare/size = 9
        h = 13  compare/size = 10
        h = 4   compare/size = 5
        h = 1   compare/size = 1
        
        
        h = 364 compare/size = 105
        h = 121 compare/size = 95
        h = 40  compare/size = 39
        h = 13  compare/size = 17
        h = 4   compare/size = 6
        h = 1   compare/size = 2
        
        
        h = 9841    compare/size = 0
        h = 3280    compare/size = 1135
        h = 1093    compare/size = 855
        h = 364     compare/size = 335
        h = 121     compare/size = 121
        h = 40      compare/size = 41
        h = 13      compare/size = 13
        h = 4       compare/size = 7
        h = 1       compare/size = 3
        
        
        h = 88573   compare/size = 323
        h = 29524   compare/size = 12111
        h = 9841    compare/size = 7926
        h = 3280    compare/size = 3061
        h = 1093    compare/size = 1047
        h = 364     compare/size = 353
        h = 121     compare/size = 129  
        h = 40      compare/size = 44
        h = 13      compare/size = 14
        h = 4       compare/size = 6
        h = 1       compare/size = 2

     */
}   
