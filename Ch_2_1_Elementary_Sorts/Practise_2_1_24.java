package Ch_2_1_Elementary_Sorts;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_1_24 {
    public static double insertion_A(int[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length;
        int min = Integer.MAX_VALUE, minIndex = 0;
        for (int i = 0; i < a.length; i++)
            if (a[i] < min) {
                min = a[i];
                minIndex = i;
            }
        int t = a[0];
        a[0] = a[minIndex];
        a[minIndex] = t;
        for (int i = 2; i < N; i++) {
            for (int j = i; a[j] < a[j - 1]; j--) {
                t = a[j];
                a[j] = a[j - 1];
                a[j - 1] = t;
            }
        }
        return timer.elapsedTime();
    }
    public static double insertion_B(int[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length;
        for (int i = 1; i < N; i++)
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                int t = a[j];
                a[j] = a[j - 1];
                a[j - 1] = t;
            }
        return timer.elapsedTime();
    }
    public static void main(String[] args) {
        int[] arr = ints(1, 100000);
        int[] copy1 = copy(arr);
        StdOut.printf("哨兵法 : %f\n", insertion_A(arr));
        StdOut.printf("普通法 : %f\n", insertion_B(copy1));
    }
    // output
    /*
     *  哨兵法 : 1.725000
        普通法 : 4.027000
     */
}
