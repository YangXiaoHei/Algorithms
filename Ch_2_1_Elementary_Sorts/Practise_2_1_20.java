package Ch_2_1_Elementary_Sorts;

import static Tool.ArrayGenerator.*;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_1_20 {
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
        int[] arr = ints(1, 30);
        int[] copy = copy(arr);
        shell(arr);
        StdOut.printf("compares : %d\n", compares);
        StdOut.println(Arrays.toString(copy));
    }
}
