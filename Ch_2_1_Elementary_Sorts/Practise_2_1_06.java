package Ch_2_1_Elementary_Sorts;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_1_06 {
    /*
     * 思路 :
     * 
     * 插入排序快，因为一次交换都不会发生，并且只比较了 N-1 次，而选择排序对输入不敏感，在此处仍然比较了 ~N^2/2 次，交换了 N 次
     */
    public static void insertion(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int b = a[i], j;
            for (j = i - 1; j >= 0 && b < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = b;
        }
    }
    public static void selection(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i; j < a.length; j++) 
                if (a[j] < a[min]) min = j;
            int t = a[i];
            a[i] = a[min];
            a[min] = t;
        }
    }
    public static void main(String[] args) {
        int[] arr = allSameInts(100000, 10);
        double t1, t2;
        Stopwatch timer = new Stopwatch();
        insertion(arr);
        t1 = timer.elapsedTime();
        
        timer = new Stopwatch();
        selection(arr);
        t2 = timer.elapsedTime();
        
        StdOut.printf("选择排序用时 : %.3f s\n插入排序用时 : %.3f s\n", t2, t1);
    }
    // output
    /*
     *  选择排序用时 : 1.153 s
        插入排序用时 : 0.002 s
     * 
     * 
     */
}
