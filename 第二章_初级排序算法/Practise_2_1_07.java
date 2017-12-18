package 第二章_初级排序算法;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_1_07 {
    /*
     * 思路 :
     * 
     * 逆序数组对于插入排序的比较次数和交换次数均为 1 + 2 + 3 + 4 + ... + N - 1 = N(N-1)/2 ~ N^2/2
     * 对于选择排序来说，交换是 N 次，比较时 ~N^2/2 次，因此选择排序更快
     * 
     * 5 4 3 2 1
     * 
     * 4 5 3 2 1
     * 
     * 4 3 5 2 1
     * 3 4 5 2 1
     * 
     * 3 4 2 5 1
     * 3 2 4 5 1
     * 2 3 4 5 1
     * 
     * 2 3 4 1 5
     * 2 3 1 4 5
     * 2 1 3 4 5
     * 1 2 3 4 5
     * 
     */
    public static void insertion(int[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            int b = a[i], j;
            for (j = i - 1; j >= 0 && b < a[j]; j--) 
                a[j + 1] = a[j];
            a[j + 1] = b;
        }
    }
    public static void selection(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = a[i];
            for (int j = i; j < N; j++)
                if (a[j] < a[min]) min = j;
            int t = a[i];
            a[i] = a[min];
            a[min] = t;
        }    
    }
    public static void main(String[] args) {
        int[] arr = descendInts(10, 0);
        int[] copy = intsCopy(arr);
        double t1, t2;
        
        print(arr);
        print(copy);
        
        Stopwatch timer = new Stopwatch();
        insertion(arr);
        t1 = timer.elapsedTime();
        
        timer = new Stopwatch();
        selection(copy);
        t2 = timer.elapsedTime();
        
        StdOut.printf("插入排序用时 : %.3f\n选择排序用时 : %.3f\n", t1, t2);
    }
    // output
    /*
     *  插入排序用时 : 8.688
        选择排序用时 : 1.795
     * 
     */
}
