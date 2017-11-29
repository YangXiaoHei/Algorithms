package 第二章_初级排序算法;

import static 第二章_初级排序算法.Text_Array.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_1_05 {
    /*
     * 思路 : 
     * 
     * 一个已经排序就位的数组，逆序对数量为0，因此每次内循环判断条件都为 false
     */
    public static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++)
            for (int j = i; j > 0 && condition(j, a[j], a[j - 1]); j--) {
                int t = a[j];
                a[j] = a[j - 1];
                a[j - 1] = t;
            }
    }
    public static boolean condition(int j, int aj, int aj1) {
        StdOut.println(j > 0 && aj < aj1);
        return j > 0 && aj < aj1;
    }
    public static void main(String[] args) {
        int[] arr = sortedArr(10);
        insertionSort(arr);
    }
    // output
    /*
     *  false
        false
        false
        false
        false
        false
        false
        false
        false

     * 
     */
}
