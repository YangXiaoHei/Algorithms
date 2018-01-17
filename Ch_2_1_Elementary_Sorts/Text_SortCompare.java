package Ch_2_1_Elementary_Sorts;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Text_SortCompare {
    public static double time(String alg, Integer[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Text_Insertion.sort_improve(a);
        if (alg.equals("Selection")) Text_Selection.sort(a);
        return timer.elapsedTime();
    }
    /*
     * 通过改变逆序对的比例，可以发现，逆序对数量越接近于数组元素数量，那么插入排序相对于选择排序的优势就越小
     * 这里的插入排序选用了性能提升版本，如果是强行交换数组元素的话，那么在逆序对数量接近数组元素个数的情况下
     * 插入排序几乎不会比选择排序快，可以看出，数组交换的成本很高昂
     */
    public static double timeRandomInput(String alg, int N, int T, double factor) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            Integer[] arr = IntegersPartialOrder(N, -100000, 100000, factor);
            total += time(alg, arr);
        }
        return total;
    }
    public static void main(String[] args) {
        String alg1 = "Insertion";
        String alg2 = "Selection";
        int N = 10000;
        int T = 5;
        double factor = 1;
        double t1 = timeRandomInput(alg1, N, T, factor);
        double t2 = timeRandomInput(alg2, N, T, factor);
        StdOut.printf("逆序对比例 : %.1f%%\n插入排序比选择排序快 %.3f 倍\n",factor * 100, t2 / t1);
    }
    // output
    /*
     *  逆序对比例 : 10%
        插入排序比选择排序快 3.135 倍

        逆序对比例 : 20%
        插入排序比选择排序快 2.349 倍

        逆序对比例 : 30%
        插入排序比选择排序快 1.803 倍
        
        逆序对比例 : 40%
        插入排序比选择排序快 1.543 倍
        
        逆序对比例 : 50%
        插入排序比选择排序快 1.474 倍
        
        逆序对比例 : 60%
        插入排序比选择排序快 1.379 倍
        
        逆序对比例 : 70%
        插入排序比选择排序快 1.191 倍
        
        逆序对比例 : 80%
        插入排序比选择排序快 1.282 倍

        逆序对比例 : 90%
        插入排序比选择排序快 1.187 倍

        逆序对比例 : 100%
        插入排序比选择排序快 1.135 倍

     */
}
