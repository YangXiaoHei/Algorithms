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
        Integer[] a = reverseOrder(20, 0);
        StdOut.println("======= 排序开始 ========");
        printWithIndexs(a);
        sort(a);
        StdOut.println("======= 排序完成 ========");
        printWithIndexs(a);
        StdOut.printf("比较次数 : %d\n", compares);
    }
    // output
    /*
     *  ======= 排序开始 ========
     *  0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   
        19   17   14   14   14   13   13   12   11   10   8    7    7    7    6    6    5    5    3    3    
        
        h = 13
        i 递增为 13
        交换了 a[13] = 7 和 a[0] = 19
        i 递增为 14
        交换了 a[14] = 6 和 a[1] = 17
        i 递增为 15
        交换了 a[15] = 6 和 a[2] = 14
        i 递增为 16
        交换了 a[16] = 5 和 a[3] = 14
        i 递增为 17
        交换了 a[17] = 5 和 a[4] = 14
        i 递增为 18
        交换了 a[18] = 3 和 a[5] = 13
        i 递增为 19
        交换了 a[19] = 3 和 a[6] = 13
        
        在 h = 13 情况下完成第 1 轮交换
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   
        7    6    6    5    5    3    3    12   11   10   8    7    7    19   17   14   14   14   13   13   
  
        h = 4
        i 递增为 4
        交换了 a[4] = 5 和 a[0] = 7
        i 递增为 5
        交换了 a[5] = 3 和 a[1] = 6
        i 递增为 6
        交换了 a[6] = 3 和 a[2] = 6
        i 递增为 7
        i 递增为 8
        i 递增为 9
        i 递增为 10
        i 递增为 11
        交换了 a[11] = 7 和 a[7] = 12
        i 递增为 12
        交换了 a[12] = 7 和 a[8] = 11
        i 递增为 13
        i 递增为 14
        i 递增为 15
        i 递增为 16
        i 递增为 17
        交换了 a[17] = 14 和 a[13] = 19
        i 递增为 18
        交换了 a[18] = 13 和 a[14] = 17
        i 递增为 19
        交换了 a[19] = 13 和 a[15] = 14
        
        在 h = 4 情况下完成第 2 轮交换
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   
        5    3    3    5    7    6    6    7    7    10   8    12   11   14   13   13   14   19   17   14   
        
        h = 1
        i 递增为 1
        交换了 a[1] = 3 和 a[0] = 5
        i 递增为 2
        交换了 a[2] = 3 和 a[1] = 5
        i 递增为 3
        i 递增为 4
        i 递增为 5
        交换了 a[5] = 6 和 a[4] = 7
        i 递增为 6
        交换了 a[6] = 6 和 a[5] = 7
        i 递增为 7
        i 递增为 8
        i 递增为 9
        i 递增为 10
        交换了 a[10] = 8 和 a[9] = 10
        i 递增为 11
        i 递增为 12
        交换了 a[12] = 11 和 a[11] = 12
        i 递增为 13
        i 递增为 14
        交换了 a[14] = 13 和 a[13] = 14
        i 递增为 15
        交换了 a[15] = 13 和 a[14] = 14
        i 递增为 16
        i 递增为 17
        i 递增为 18
        交换了 a[18] = 17 和 a[17] = 19
        i 递增为 19
        交换了 a[19] = 14 和 a[18] = 19
        交换了 a[18] = 14 和 a[17] = 17
        
        在 h = 1 情况下完成第 3 轮交换
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   
        3    3    5    5    6    6    7    7    7    8    10   11   12   13   13   14   14   14   17   19   
        
        ======= 排序完成 ========
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   
        3    3    5    5    6    6    7    7    7    8    10   11   12   13   13   14   14   14   17   19   
     * 
     * 
     * 
     */
}
