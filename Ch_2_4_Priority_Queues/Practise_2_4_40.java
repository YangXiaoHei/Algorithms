package Ch_2_4_Priority_Queues;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_4_40 {
    public static int floyd(int[] a) {
        compares = 0;
        Stopwatch timer = new Stopwatch();
        int N = a.length;
        for (int i = N >> 1; i > 0; i--) {
            int k = i;
            int key = a[k - 1];
            while ((k << 1) <= N) {
                int j = k << 1;
                if (j < N && less(a[j - 1], a[j])) j++;
                if (!less(key, a[j - 1])) break;
                a[k - 1] = a[j - 1];
                k = j;
            }
            a[k - 1] = key;
        }
        while (N > 1) {
            int tmp = a[N - 1];
            a[N - 1] = a[0];
            --N;
            int k = 1;
            while ((k << 1) <= N) {
                int j = k << 1;
                if (j < N && less(a[j - 1], a[j])) j++;
                a[k - 1] = a[j - 1];
                k = j;
            }
            a[k - 1] = tmp;
            while (k > 1 && grea(tmp, a[(k >> 1) - 1])) {
                a[k - 1] = a[(k >> 1) - 1];
                k >>= 1;
            }
            a[k - 1] = tmp;
        }
        StdOut.printf("floyd改进方法耗时 : %.3f\n", timer.elapsedTime());
        return compares;
    }
    public static int standard(int[] a) {
        compares = 0;
        Stopwatch timer = new Stopwatch();
        int N = a.length;
        for (int i = N >> 1; i > 0; i--) {
            int k = i, tmp = a[k - 1];
            while ((k << 1) <= N) {
                int j = k << 1;
                if (j < N && less(a[j - 1], a[j])) j++;
                if (!less(tmp, a[j - 1])) break;
                a[k - 1] = a[j - 1];
                k = j;
            }
            a[k - 1] = tmp;
        }
        while (N > 1) {
            int t = a[N - 1];
            a[N - 1] = a[0];
            a[0] = t;
            --N;
            int k = 1, tmp = a[0];
            while ((k << 1) <= N) {
                int j = k << 1;
                if (j < N && less(a[j - 1], a[j])) j++;
                if (!less(tmp, a[j - 1])) break;
                a[k - 1] = a[j - 1];
                k = j;
            }
            a[k - 1] = tmp;
        }
        StdOut.printf("标准方法耗时 : %.3f\n", timer.elapsedTime());
        return compares;
    }
    private static int compares;
    public static boolean grea(int i, int j) {
        compares++;
        return i > j;
    }
    public static boolean less(int i, int j) {
        compares++;
        return i < j;
    }
    public static void main(String[] args) {
        int[] a = ints(0, 10000000);
        int[] copy = intsCopy(a);
        StdOut.printf("先沉后浮比较次数 : %d\n", floyd(a));
        StdOut.printf("两次下沉比较次数 : %d\n", standard(copy));
        assert isSorted(a);
        assert isSorted(copy);
    }
    // output
    /*
     *  floyd改进方法耗时 : 3.385
        先沉后浮比较次数 : 238623801
        标准方法耗时 : 3.683
        两次下沉比较次数 : 434649177

     */
}
