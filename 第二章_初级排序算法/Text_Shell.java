package 第二章_初级排序算法;

import edu.princeton.cs.algs4.StdOut;
import static 第二章_初级排序算法.Text_Array.*;

/*
 * h 递增序列为 3 * k + 1 = 1 4 13 40 121 364
 * 
 * 首先找到一个在 h = 1 4 13 40 121 364 ... 序列中满足 h >= N / 3 的最小值
 * 
 * 然后开始在序列 h = h/3 h/9 h/27 h/81 ... 1 下依次在子区域 [0, N - h] [h, N]
 * 
 * 完整运行轨迹的清晰绘制请查看 https://github.com/YangXiaoHei/Algorithms4/blob/master/第二章_初级排序算法/希尔排序完整轨迹.png
 * 
 */
public class Text_Shell {
    private static int compares = 0;
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) 
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) 
                    exch(a, j, j - h);
            h /= 3;
        }
    }
    public static boolean less(Comparable v, Comparable w) {
        compares++;
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        Integer[] a = integerReverseOrder_bounds(20, 0);
        StdOut.println("======= 排序开始 ========");
        print(a);
        sort(a);
        StdOut.println("======= 排序完成 ========");
        print(a);
        StdOut.printf("比较次数 : %d\n", compares);
    }
    // output
    /*
     *  ======= 排序开始 ========
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   20   
        20   19   18   17   16   15   14   13   12   11   10   9    8    7    6    5    4    3    2    1    0    
        ======= 排序完成 ========
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   20   
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   20   
        比较次数 : 74
   
     * 
     */
}
