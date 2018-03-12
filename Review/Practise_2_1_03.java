package Review;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_1_03 {
    private static int compares;
    public static boolean less(int a, int b) {
        if (a < b) compares++;
        return a < b;
    }
    public static int selection(int[] a) {
        compares = 0;
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) 
                if (less(a[j], a[min])) min = j;
            if (i != min) {
                int t = a[min];
                a[min] = a[i];
                a[i] = t;
            }
        }
        return compares;
    }
    public static void main(String[] args) {
        int a[] = ints(0, 100);
        int des[] = descendInts(100, 0);
        
        StdOut.printf("平均比较成功次数 : %d\n", selection(a));
        StdOut.printf("最多比较成功次数 : %d\n", selection(des));
    }
    // output
    /*
     *  平均比较成功次数 : 344
        最多比较成功次数 : 2550

     */
}
