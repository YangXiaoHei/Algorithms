package 第二章_快速排序;

import edu.princeton.cs.algs4.*;
import static 第二章_初级排序算法.Text_Array.*;

public class Text_QuickSort {
    public static void quick(int[] a) {
        
        quick(a, 0, a.length - 1);
    }
    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) {
            StdOut.printf("lo = %d hi = %d ---返回---\n", lo, hi);
            return;
        }
        int j = partition(a, lo, hi);
        StdOut.printf("将进入第一层 {lo = %d hi = %d}\n", lo, j - 1);
        quick(a, lo, j - 1);
        StdOut.printf("从 {lo = %d, hi = %d} 退出，将进入第二层 {lo = %d hi = %d}\n",lo, j - 1, j + 1, hi);
        quick(a, j + 1, hi);
    }
    private static int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        int v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (j > lo && v < a[--j]);
            
            if (i >= j) {
                break;
            } else {
                print(a);
                
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
                
                StdOut.printf("lo = %d 切分结果 : i = %d j = %d\n",lo, i, j);
            }
        }
        
        print(a);
        
        int t = a[lo];
        a[lo] = a[j];
        a[j] = t;
        
        StdOut.printf("切分结束 j = %d\n", j);
        StdOut.print("//////////////////////////////");
        print(a);
        StdOut.println("//////////////////////////////");
        
        return j;
    }
    public static void main(String[] args) {
        int[] a = ints(0, 10);
        print(a);
        quick(a);
    }
    // output
    /*
     * 
        0       1       2       3       4       5       6       7       8       9       10      
        2       5       0       6       3       9       4       1       7       8       10      
        
        0       1       2       3       4       5       6       7       8       9       10      
        2       5       0       6       3       9       4       1       7       8       10      
        lo = 0 切分结果 : i = 1 j = 7
        
        0       1       2       3       4       5       6       7       8       9       10      
        2       1       0       6       3       9       4       5       7       8       10      
        切分结束 j = 2
        //////////////////////////////
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       6       3       9       4       5       7       8       10      
        //////////////////////////////
        将进入第一层 {lo = 0 hi = 1}
        
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       6       3       9       4       5       7       8       10      
        切分结束 j = 0
        //////////////////////////////
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       6       3       9       4       5       7       8       10      
        //////////////////////////////
        将进入第一层 {lo = 0 hi = -1}
        lo = 0 hi = -1 ---返回---
        从 {lo = 0, hi = -1} 退出，将进入第二层 {lo = 1 hi = 1}
        lo = 1 hi = 1 ---返回---
        从 {lo = 0, hi = 1} 退出，将进入第二层 {lo = 3 hi = 10}
        
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       6       3       9       4       5       7       8       10      
        lo = 3 切分结果 : i = 5 j = 7
        
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       6       3       5       4       9       7       8       10      
        切分结束 j = 6
        //////////////////////////////
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       4       3       5       6       9       7       8       10      
        //////////////////////////////
        将进入第一层 {lo = 3 hi = 5}
        
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       4       3       5       6       9       7       8       10      
        切分结束 j = 4
        //////////////////////////////
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       3       4       5       6       9       7       8       10      
        //////////////////////////////
        将进入第一层 {lo = 3 hi = 3}
        lo = 3 hi = 3 ---返回---
        从 {lo = 3, hi = 3} 退出，将进入第二层 {lo = 5 hi = 5}
        lo = 5 hi = 5 ---返回---
        从 {lo = 3, hi = 5} 退出，将进入第二层 {lo = 7 hi = 10}
        
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       3       4       5       6       9       7       8       10      
        切分结束 j = 9
        //////////////////////////////
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       3       4       5       6       8       7       9       10      
        //////////////////////////////
        将进入第一层 {lo = 7 hi = 8}
        
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       3       4       5       6       8       7       9       10      
        切分结束 j = 8
        //////////////////////////////
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       3       4       5       6       7       8       9       10      
        //////////////////////////////
        将进入第一层 {lo = 7 hi = 7}
        lo = 7 hi = 7 ---返回---
        从 {lo = 7, hi = 7} 退出，将进入第二层 {lo = 9 hi = 8}
        lo = 9 hi = 8 ---返回---
        从 {lo = 7, hi = 8} 退出，将进入第二层 {lo = 10 hi = 10}
        lo = 10 hi = 10 ---返回---
     */
}
