package 第二章_初级排序算法;

import static 第二章_初级排序算法.Text_Array.*;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_1_19 {
    private static int compares;
    public static void shell(int[] a) {
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                int v = a[i], j;
                for (j = i - h; j >= 0 && less(v, a[j]); j -= h) 
                    a[j + h] = a[j];
                a[j + h] = v;
            }
            h /= 3;
        }
    }
    public static boolean less(int a, int b) { compares++; return a < b; }
    public static void main(String[] args) {
        int[] arr = null, copy = null;
        while (true) {
            compares = 0;
            arr = intRandom_bounds(1,100);
            copy = intCopy_arr(arr);
            shell(arr);
            if (compares > 998)  break;
        }
        StdOut.printf("compares : %d\n", compares);
        StdOut.println(Arrays.toString(copy));
    }
    // output
    /*
     *  compares : 999
        [48, 46, 54, 97, 83, 69, 76, 25, 10, 5, 87, 12, 21, 99, 61, 33, 30, 47, 57, 4, 36, 42, 98, 66, 100, 17, 94, 81, 11, 77, 24, 89, 73, 53, 38, 7, 29, 8, 27, 23, 56, 70, 60, 85, 39, 65, 9, 75, 15, 67, 64, 22, 51, 82, 43, 3, 37, 91, 45, 13, 34, 63, 74, 71, 95, 55, 80, 92, 2, 19, 62, 40, 84, 41, 50, 88, 86, 59, 28, 44, 72, 68, 14, 35, 93, 26, 18, 78, 31, 58, 96, 6, 1, 90, 49, 16, 52, 79, 32, 20]
     * 
     * 
     */
}
