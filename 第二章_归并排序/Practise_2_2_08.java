package 第二章_归并排序;

import static 第二章_初级排序算法.Text_Array.*;
import java.util.Arrays;
import edu.princeton.cs.algs4.*;

public class Practise_2_2_08 {
    private static int[] aux;
    private static int compares;
    public static int merge_B(int[] a) {
        compares = 0;
        aux = new int[a.length];
        merge_B(a, 0, a.length - 1);
        return compares;
    }
    public static int merge_A(int[] a) {
        compares = 0;
        aux = new int[a.length];
        merge_A(a, 0, a.length - 1);
        return compares;
    }
    private static void merge_A(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        merge_A(a, lo, mid);
        merge_A(a, mid + 1, hi);
        mergeSort(a, lo, mid, hi);
    }
    private static void merge_B(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        merge_B(a, lo, mid);
        merge_B(a, mid + 1, hi);
        if (less(a[mid], a[mid + 1])) return;
        mergeSort(a, lo, mid, hi);
    }
    private static void mergeSort(int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)
            if      (i > mid)           a[k] = aux[j++];
            else if (j > hi)            a[k] = aux[i++];
            else if (less(a[j], a[i]))  a[k] = aux[j++];
            else                        a[k] = aux[i++];     
    }
    private static boolean less(int a, int b) {
        compares += 2;
        return a < b;
    }
    public static void main(String[] args) {
        /*
         * 当 N 已经排序完毕时
         * 
         * 比较次数为 T(N) = T(N/2) + 1 (T(1) = 0)
         * 
         * 也就是说最后一步 merge N 个元素的过程被忽略，那么归并 N 个元素的所有比较次数也就被忽略
         * 整体的比较次数为 上一次归并 N/2 个元素的比较次数加上最后一次 a[mid] < a[mid + 1] 的比较次数
         * 
         * 举例说明 :
         * T(2) = T(1) + 1 仅有 a[mid] < a[mid + 1] 1 次比较
         * T(4) = T(2) + 1  
         * T(8) = T(4) + 1 = T(2) + 2
         * T(16) = T(8) + 1 = T(2) + 3
         * T(2^k) = T(2) + k - 1 = T(1) + 1 + k - 1 = k
         * 
         * 但是假如在最坏情况下，比如一个纯逆序的数组那么总比较次数为 (N + 1)*log(N)
         * 
         * 我们可以用下面的例子证明
         */
        int N = 1024; // 这里尽量设置成 2 的幂次方，因为生成测试数组时我使用了 log(N) 的尺寸
        int[] arr = descendInts(N, 0);
        int[] copy = intsCopy(arr);
        int comparesA = merge_A(arr);
        int comparesB = merge_B(copy);
        StdOut.printf("最不适合改进条件下的比较次数 : A : %d  改进后 : %d\n", comparesA, comparesB);
        
        arr = forMergeSortTest(N);
        copy = intsCopy(arr);
        comparesA = merge_A(arr);
        comparesB = merge_B(copy);
        StdOut.printf("接近最适合改进条件下的比较次数 : A : %d  改进后 : %d\n", comparesA, comparesB);
        
        arr = ints(N);
        Arrays.sort(arr);
        comparesA = merge_A(arr);
        comparesB = merge_B(copy);
        StdOut.printf("最适合改进条件情况下的比较次数 : A : %d  改进后 : %d\n", comparesA, comparesB);
    
        arr = ints(N);
        copy = intsCopy(arr);
        comparesA = merge_A(arr);
        comparesB = merge_B(copy);
        StdOut.printf("平均情况下的比较次数 : A : %d  改进后 : %d\n", comparesA, comparesB);
    }
    // output
    /*
     *  最不适合改进条件下的比较次数 : A : 11264  改进后 : 13312
        接近最适合改进条件下的比较次数 : A : 10240  改进后 : 3070
        最适合改进条件情况下的比较次数 : A : 10240  改进后 : 2046
        平均情况下的比较次数 : A : 15446  改进后 : 16728

     */
}
