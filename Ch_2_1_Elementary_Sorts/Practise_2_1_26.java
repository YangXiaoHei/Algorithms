package Ch_2_1_Elementary_Sorts;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_1_26 {
    public static double shell(int[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                int t = a[i], j;
                for (j = i - h; j >= 0 && a[j + h] < a[j]; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
            h /= 3;
        }
        return timer.elapsedTime();
    }
    public static double shell(Integer[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                Integer t = a[i], j;
                for (j = i - h; j >= 0 && a[j + h].compareTo(a[j]) < 0; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
            h /= 3;
        }
        return timer.elapsedTime();
    }
    public static void main(String[] args) {
        Integer[] arr = Integers(0, 4000000);
        int[] arr1 = IntegerToInt(arr);
        StdOut.printf("基本数据类型 : %f\n", shell(arr1));
        StdOut.printf("包装类 : %f\n", shell(arr));
    }
    // output
    /*
     *  基本数据类型 : 0.224000
        包装类 : 5.014000
     */
}
