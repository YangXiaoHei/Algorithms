package 第二章_初级排序算法;

import edu.princeton.cs.algs4.*;
import static 第二章_初级排序算法.Text_Array.*;
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
 * 其实只需要将大的元素往右移，就可以只访问两次数组，性能可以提高 1 ~ 2 倍
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
    public static void sort_improve(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            if (less(a[i], a[i - 1])) {
                Comparable t = a[i];
                int j;
                for (j = i - 1; j >= 0 && less(t, a[j]); j--) 
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
        }
    }
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void performenceTest(int T, int N) {
        double avrg = 0, avrgImpo = 0;
        Stopwatch timer = null;
        for (int i = 0; i < T; i++) {
            Integer[] arr = IntegerRandom_size(N);
            Integer[] copy = IntegerCopy_arr(arr);
            
            timer = new Stopwatch();
            sort(arr);
            avrg += timer.elapsedTime();
            
            timer = new Stopwatch();
            sort_improve(copy);
            avrgImpo += timer.elapsedTime();
        }
        StdOut.printf("对规模为 %d 的数组进行排序，【改善】性能是【未改善】性能的 %f 倍\n", N, avrg / avrgImpo);
    }
    public static void main(String[] args) {
        Integer[] arr = IntegerRandom_size_bounds(30, 0, 50);
        StdOut.println("======= 排序开始 ========");
        print(arr);
        sort_improve(arr);
        StdOut.println("======= 排序完成 ========");
        print(arr);
        
        StdOut.println("======= 性能改善 ========");
        performenceTest(4, 40000);
    }
    // output
    /*
     * 
     *   ======= 排序开始 ========
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26   27   28   29   
        26   14   0    40   11   34   32   31   48   13   49   21   35   30   43   23   34   49   11   0    19   37   27   29   16   21   19   30   49   31   
        ======= 排序完成 ========
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26   27   28   29   
        0    0    11   11   13   14   16   19   19   21   21   23   26   27   29   30   30   31   31   32   34   34   35   37   40   43   48   49   49   49   
        ======= 性能改善 ========
        对规模为 40000 的数组进行排序，【改善】性能是【未改善】性能的 2.302630 倍
     * 
     */
}
