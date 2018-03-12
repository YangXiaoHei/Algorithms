package Review;

import static Tool.ArrayGenerator.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_1_02 {
    private static int exchs = 0;
    public static void selection(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++)
                if (a[j] < a[min]) min = j;
            if (min != i) { // 交换次数会比 N 稍微少一些，微不足道的小优化
                ++exchs;
                int t = a[min];
                a[min] = a[i];
                a[i] = t;
            }
        }
    }
    public static void main(String[] args) {
        /*
         * 交换次数稳定是 N 次
         */
        int a[] = ints(0, 100);
        selection(a);
        StdOut.printf("交换次数 %d\n", exchs); 
    }
    // output
    /*
     * 交换次数 97

     */
}
