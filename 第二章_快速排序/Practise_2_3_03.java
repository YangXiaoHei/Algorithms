package 第二章_快速排序;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_3_03 {
    private static int exchanges;
    private static int max;
    public static void searchMax(int[] a) {
        for (int i = 1, max = a[0]; i < a.length; i++)
            if (a[i] > max) max = a[i]; 
    }
    public static void quick (int[] a) {
        quick(a, 0, a.length - 1);
    }
    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = parition(a, lo, hi);
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    private static void exch(int[] a, int i, int j) {
        if (max == a[i] || max == a[j]) exchanges++;
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static int parition(int[] a, int lo, int hi) {
        int i = lo - 1, j = lo, v = a[hi];
        while (j < hi) {
            if (a[j] < v) 
                exch(a, j, ++i);
            j++; 
        }
        exch(a, ++i, hi);
        return i;
    }
    public static void infiniteLoopSearch() {
        while (true) {
            int[] a = ints(0, 5); // 生成一个元素取值在 [0, 5] 无重复的随机打乱序列
            int[] copy = intsCopy(a); 
            exchanges = 0;
            searchMax(a);
            quick(a);
            assert isSorted(a);
            if (exchanges > 4) {
                print(copy);
                StdOut.printf("最大元素 %d 的交换次数为 : %d\n", max, exchanges);
                break;
            }
        }
    }
    public static void main(String[] args) {
        infiniteLoopSearch();
    }
    // output
    /*
     *  单向扫描
        
        最大元素最多会被交换 N - 1 次
        
        0   1   2   3   4   5   
        5   1   3   0   2   4   
        最大元素 5 的交换次数为 : 5
     * 
     * 
     *  双向扫描
     *   
     *  最大元素最多会被交换 floor(N/2) 次
     * 
     * 
     *  0   1   2   3   4   5   
        1   5   3   0   2   4   
        最大元素 5 的交换次数为 : 3
     * 
     *  0   1   2   3   4   5   6   7   8   9   
        1   9   3   0   5   2   7   4   6   8   
        最大元素 9 的交换次数为 : 5
     * 
     * 
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  
        1   13  4   2   0   6   3   9   5   11  8   7   10  12  
        最大元素 13 的交换次数为 : 6
      
     
        0   1   2   3   4   5   6   7   8   9   10  
        2   9   10  4   1   6   3   8   5   7   0   
        最大元素 10 的交换次数为 : 5
        
        
        0   1   2   3   4   5   6   7   8   9   10  
        1   10  3   0   5   2   7   4   6   9   8   
        最大元素 10 的交换次数为 : 5
        
        
        0   1   2   3   4   5   6   7   8   
        1   8   3   0   6   7   2   4   5   
        最大元素 8 的交换次数为 : 4  
        
        


     */
}
