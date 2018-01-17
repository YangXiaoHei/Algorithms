package Ch_2_3_Quicksort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_3_19 {
    public static double quick3(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick3(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick3(int[] a, int lo, int hi) {
        if (hi - lo + 1 < 30) {
            for (int i = lo; i <= hi; i++) {
                int t = a[i], j;
                for (j = i - 1; j >= 0 && t < a[j]; j--)
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
            return;
        }
        int mid = (lo + hi) >>> 1;
        if (a[mid] < a[lo]) { int t = a[lo]; a[lo] = a[mid]; a[mid] = t; }
        if (a[hi] < a[lo])  { int t = a[hi]; a[hi] = a[lo]; a[lo] = t; }
        if (a[hi] < a[mid]) { int t = a[hi]; a[hi] = a[mid]; a[mid] = t; }
        { int t = a[hi - 1]; a[hi - 1] = a[mid]; a[mid] = t; }
        int v = a[hi - 1], i = lo - 1, j = hi - 1;
        while (true) {
            while (a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
        int t = a[hi - 1]; a[hi - 1] = a[i]; a[i] = t;
        quick3(a, lo, i - 1);
        quick3(a, i + 1, hi);
    }
    public static double quick5(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick5(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick5(int[] a, int lo, int hi) {
        int len = hi - lo + 1;
        if (len < 30) {
            for (int i = lo; i <= hi; i++) {
                int t = a[i], j;
                for (j = i - 1; j >= 0 && t < a[j]; j--)
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
            return;
        }
        int e3 = (lo + hi) >> 1;
        int fifth = len >> 2;
        int e1 = lo;
        int e2 = e3 - fifth;
        int e4 = e3 + fifth;
        int e5 = hi;
        
        if (a[e2] < a[e1]) { int t = a[e2]; a[e2] = a[e1]; a[e1] = t; }
        if (a[e3] < a[e2]) { int t = a[e3]; a[e3] = a[e2]; a[e2] = t; 
            if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
        }
        if (a[e4] < a[e3]) { int t = a[e4]; a[e4] = a[e3]; a[e3] = t;
            if (t < a[e2]) { a[e3] = a[e2]; a[e2] = t;
                if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
            }
        }
        if (a[e5] < a[e4]) { int t = a[e5]; a[e5] = a[e4]; a[e4] = t;
            if (t < a[e3]) { a[e4] = a[e3]; a[e3] = t;
                if (t < a[e2]) { a[e3] = a[e2]; a[e2] = t;
                    if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
                }
            }
        }
        int t = a[hi - 1]; a[hi - 1] = a[e3]; a[e3] = t;
        
        int v = a[hi - 1], i = lo - 1, j = hi - 1;
        while (true) {
            while (a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            t = a[i]; a[i] = a[j]; a[j] = t;
        }
        t = a[hi - 1]; a[hi - 1] = a[i]; a[i] = t;
        quick5(a, lo, i - 1);
        quick5(a, i + 1, hi);
    }
    public static void main(String[] args) {
        int[] a = ints(0, 4000000);
        int[] copy = intsCopy(a);
        StdOut.printf("3取样切分 : %.3f\n", quick3(a));
        StdOut.printf("5取样切分 : %.3f\n", quick5(copy));
        assert isSorted(copy);
        assert isSorted(a);
    }
    // output
    /*
     *  3取样切分 : 0.800
        5取样切分 : 0.818
     */
}
