package 第二章_初级排序算法;

import edu.princeton.cs.algs4.StdOut;
import static 第二章_初级排序算法.Text_Array.*;

/*
 * h 递增序列为 3 * k + 1 = 1 4 13 40 121 364
 */
public class Text_Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N/3) h = 3 * h + 1;
        int times = 1;
        while (h >= 1) {
            StdOut.printf("\nh = %d\n", h);
            for (int i = h; i < N; i++) {
                StdOut.printf("i 递增为 %d\n", i);
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    StdOut.printf("交换了 a[%d] = %d 和 a[%d] = %d\n", j, a[j], j - h, a[j - h]);
                    exch(a, j, j - h);
                    
                }
            }
            StdOut.printf("\n在 h = %d 情况下完成第 %d 轮交换\n", h, times++);
            printWithIndexs(a);
            StdOut.println();
            h /= 3;
        }
        
    }
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
//        Integer[] a = partialOrder(19, 1, 20);
//        printWithIndexs(a);
        Integer[] a = reverseOrder(20, 1, 20);
        printWithIndexs(a);
        sort(a);
        StdOut.println("======= 排序完成 ========");
        printWithIndexs(a);
    }
}
