package Ch_2_1_Elementary_Sorts;

import static Tool.ArrayGenerator.*;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_1_19 {
    private static int compares;
    public static void shell(int[] a) {
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                int v = a[i], j;
                for (j = i - h; j >= 0 && less(v, a[j]); j -= h) 
                    a[j + h] = a[j];
                a[j + h] = v;
            }
            h /= 3;
        }
    }
    public static boolean less(int a, int b) { compares++; return a < b; }
    public static void test() {
        int[] arr = null, copy = null;
      double[] test = new double[10]; 
      int N = 10000, k = N;
      while (k > 0) {
          compares = 0;
          arr = ints(1,10);
          arr = new int[] {8,  2,    10,   4,  7,   3,  9,    5,  6,  1, };
          copy = copy(arr);
          shell(arr);
          if (compares > 36)  {
              for (int i = 0; i < 10; i++)
                  test[i] += copy[i];
              k--;
          }
      }
      for (int i = 0; i < 10; i++)
          test[i] /= (N * 1.0);
      print(test);
    }
    public static void main(String[] args) {
        int[] arr = null, copy = null;
        while (true) {
            compares = 0;
            arr = ints(1,10);
            copy = copy(arr);
            shell(arr);
            if (compares > 36) break;
        }
        StdOut.printf("compares : %d\n", compares);
        StdOut.println(Arrays.toString(copy));
    }
    // output
    /*
     *  对于 100 个数字的随机排列，我能找到的使希尔排序比较次数最大的序列如下
     * 
     *  compares : 999
        [48, 46, 54, 97, 83, 69, 76, 25, 10, 5, 87, 12, 21, 99, 61, 33, 30, 47, 57, 4, 36, 42, 98, 66, 100, 17, 94, 81, 11, 77, 24, 89, 73, 53, 38, 7, 29, 8, 27, 23, 56, 70, 60, 85, 39, 65, 9, 75, 15, 67, 64, 22, 51, 82, 43, 3, 37, 91, 45, 13, 34, 63, 74, 71, 95, 55, 80, 92, 2, 19, 62, 40, 84, 41, 50, 88, 86, 59, 28, 44, 72, 68, 14, 35, 93, 26, 18, 78, 31, 58, 96, 6, 1, 90, 49, 16, 52, 79, 32, 20]
     * 
     *  对于 10 个数字的随机排列，我能找到的使希尔排序比较次数最大的序列如下
     * 
     *  compares : 37
     *  [8, 3, 10, 4, 7, 2, 9, 5, 6, 1]
     * 
     *  compares : 37
        [9, 3, 7, 5, 10, 1, 6, 4, 8, 2]
        
        compares : 37
        [8, 3, 10, 4, 6, 2, 9, 5, 7, 1]

        compares : 37
        [10, 3, 7, 5, 9, 2, 6, 4, 8, 1]

        compares : 37
        [10, 3, 6, 5, 8, 1, 7, 4, 9, 2]
        
        compares : 37
        [8, 1, 7, 5, 10, 3, 6, 4, 9, 2]
        
        // 找出了 一万个比较次数为 37 的序列，每个索引上出现的平均值如下
        0    1    2    3    4    5    6    7    8    9    
        7.3  2.2  8.5  5.5  7.2  2.3  8.5  5.5  6.5  1.5  

        0    1    2    3    4    5    6    7    8    9    
        8    2    10   4    7    3    9    5    6    1  

     */
}
