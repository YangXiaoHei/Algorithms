package Ch_2_4_Priority_Queues;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class Practise_2_4_39 {
    public static double heap(int[] a) {
        int N = a.length;
        Stopwatch timer = new Stopwatch();
        for (int i = N >> 1; i > 0; i--) {
            int k = i;
            int key = a[k - 1];
            while ((k << 1) <= N) {
                int j = k << 1;
                if (j < N && a[j - 1] < a[j]) j++;
                if (key >= a[j - 1]) break;
                a[k - 1] = a[j - 1];
                k = j;
            }
            a[k - 1] = key; 
        }
        double t1 = timer.elapsedTime();
        while (N > 1) {
            int t = a[0];
            a[0] = a[N - 1];
            a[N - 1] = t;
            --N;
            int k = 1;
            int key = a[0];
            while ((k << 1) <= N) {
                int j = k << 1;
                if (j < N && a[j - 1] < a[j]) j++;
                if (key >= a[j - 1]) break;
                a[k - 1] = a[j - 1];
                k = j;
            }
            a[k - 1] = key;
        }
        return t1;
    }
    public static void main(String[] args) {
        int N = 10000000;
        int[] a = ints(0, N - 1);
        Stopwatch timer = new Stopwatch();
        double t1 = heap(a);
        double t2 = timer.elapsedTime();
        StdOut.printf("规模 : %d 堆构建 : %.3f 总排序时长 : %.3f  堆构建 / 总排序时长 : %.3f\n",
                N, t1, t2, t1 / t2);
        assert isSorted(a);
    }
    // output
    /*
     * 规模 : 1000000 堆构建 : 0.019 总排序时长 : 0.277  堆构建 / 总排序时长 : 0.069
       规模 : 10000000 堆构建 : 0.133 总排序时长 : 6.152  堆构建 / 总排序时长 : 0.022

     */
}
