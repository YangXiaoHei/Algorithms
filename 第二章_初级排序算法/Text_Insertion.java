package 第二章_初级排序算法;

import edu.princeton.cs.algs4.StdOut;
/*
 * 插入排序 :
 * 
 * 从位置 1 开始，向前查询是否有逆序对，如果有，交换位置，这样的结果是将查询开始位置的元素插入到了一个合适的位置
 * 尽管这个当前合适的位置，有可能在后续的插入中被顶到后边
 * 
 * 插入排序的优势在于处理以下三种情况 :
 * 
 * 数组中每个元素距离它的最终位置都不远
 * 一个有序的大数组接一个小数组
 * 数组中只有几个元素的位置不正确
 * 
 * 插入排序在最好情况下，即元素已排序过的情况下，只需要 N-1 次比较和 0 次交换
 * 插入排序在最坏的情况下，即元素序列完全逆序的情况下，需要 
 * 1 + 2 + 3 + ... + N-1 = N(N-1)/2 ~ N^2/2 次 比较 和 N(N-1)/2 ~N^2/2 次交换
 * 
 * 插入算法每次判断出 前者 比 后者大时，都交换元素，此时涉及到三个动作，int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
 * 其实只需要将大的元素往右移，就可以只访问两次数组，性能可以大幅提高
 * 
 */
public class Text_Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
        }
    }
    public static void sort_i(Comparable[] a) {
        int N = a.length;
        boolean needInsert = false;
        for (int i = 1; i < N; i++) {
            Comparable b = a[i];
            int j;
            needInsert = false;
            for (j = i - 1; j >= 0; j--) {
                if (less(b, a[j])) {
                    a[j + 1] = a[j];
                    needInsert = true;
                }
            }
            if (needInsert)
                a[++j] = b;
        }
    }
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        Integer[] arr = Text_RandomArray.sourceArr(10);
//        Integer[] arr = new Integer[] {4, 3, 2, 1};
        sort_i(arr);
        for (int i = 0; i < arr.length; i++) 
            StdOut.print(arr[i] + " ");
        StdOut.println();
    }
}
