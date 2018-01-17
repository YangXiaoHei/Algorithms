package Ch_2_1_Elementary_Sorts;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_1_36 {
    public static double shell(int[] a) {
        a = intsCopy(a);
        Stopwatch timer = new Stopwatch();
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                int t = a[i], j;
                for (j = i - h; j >= 0 && t < a[j]; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
            h /= 3;
        }
        return timer.elapsedTime();
    }
    public static int[] halfZeroHalfOne(int N) {
        return intsVrgWithEachAmount(N / 2, 1, 0);
    }
    public static int[] halfZeroQuarterOneQuarterTwo(int N) {
        int[] arr1 = intsVrgWithEachAmount(N / 4, 1, 2);
        int[] arr2 = allSameInts(N / 2, 0);
        int[] combine = new int[arr1.length + arr2.length];
        int i = 0;
        while (i < arr1.length) { combine[i] = arr1[i]; i++; }
        while (i < arr2.length + arr1.length) { combine[i] = arr2[i - arr1.length]; i++; }
        shuffle(combine);
        return combine;
    }
    public static int[] halfZeroHalfRandom(int N) {
        int[] arr1 = allSameInts(N / 2, 0);
        int[] arr2 = ints(N / 2, 0, 10000);
        int[] combine = new int[arr1.length + arr2.length];
        int i = 0;
        while (i < arr1.length) { combine[i] = arr1[i]; i++; }
        while (i < arr2.length + arr1.length) { combine[i] = arr2[i - arr1.length]; i++; }
        shuffle(combine);
        return combine;
    }
    public static void main(String[] args) {
        StdOut.printf("对照试验 :\t\t %.3f\n", shell(ints(8000000, 0, 1000000)));
        StdOut.printf("一半0 一半1 :\t\t %.3f\n", shell(halfZeroHalfOne(8000000)));
        StdOut.printf("一半0 1/4 1 1/4 2 :\t %.3f\n", shell(halfZeroQuarterOneQuarterTwo(8000000)));
        StdOut.printf("一半0 一半随机 :\t\t %.3f\n", shell(halfZeroHalfRandom(8000000)));
    }
    // output
    /*
     *  对照试验 :              2.000
        一半0 一半1 :           0.218
        一半0 1/4 1 1/4 2 :    0.260
        一半0 一半随机 :         0.907

     */
}
