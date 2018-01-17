package Ch_2_3_Quicksort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_3_05 {
    /*
     * 针对大量重复元素，当然是三向切分的 parition 更好啦~
     */
    public static void quick(int[] a) {
        quick(a, 0, a.length - 1);
    }
    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int lt = lo, i = lo + 1, gt = hi, v = a[lo];
        while (i <= gt) {
            if      (a[i] > v) exch(a, i, gt--);
            else if (a[i] < v) exch(a, lt++, i++);
            else    i++;
        }
        quick(a, lo, lt - 1);
        quick(a, gt + 1, hi);
    }
    private static void exch(int[] a, int i, int j) { 
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        int[] a = intsVrgWithEachAmount(1000, 1, 2);
        quick(a);
        assert isSorted(a);
        StdOut.println("排序完成");
    }
    // output
    /*
     * 排序完成

     */
}
