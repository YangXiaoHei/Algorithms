package 第二章_快速排序;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_3_09 {
  public static int parition_selectMaxAsPivot(int[] a, int lo, int hi) {
      if (lo >= hi) 
          throw new IllegalArgumentException("lo >= hi!");
      int max = a[0], index = 0;
      for (int i = 0; i < a.length; i++)
          if (a[i] > max) { max = a[i]; index = i; }
      exch(a, hi, index);
      int i = lo - 1, j = hi, v = a[hi];
      while (true) {
          while (a[++i] < v);
          while (j > lo && a[--j] > v);
          if (i >= j) break;
          exch(a, i, j);
      }
      exch(a, i, hi);
      return i;
  }
  public static int parition_selectMinAsPivot(int[] a, int lo, int hi) {
      if (lo >= hi) 
          throw new IllegalArgumentException("lo >= hi!");
      int min = a[0], index = 0;
      for (int i = 0; i < a.length; i++)
          if (a[i] < min) { min = a[i]; index = i; }
      exch(a, lo, index);
      int i = lo, j = hi + 1, v = a[lo];
      while (true) {
          while (i < hi && a[++i] < v);
          while (a[--j] > v);
          if (i >= j) break;
          exch(a, i, j);
      }
      exch(a, j, lo);
      return j;
  }
  private static void exch(int[] a, int i, int j) {
      int t = a[i]; a[i] = a[j]; a[j] = t;
  }
  public static void main(String[] args) {
      int[] a = intsVrg(10, 1, 2);
      int[] copy = intsCopy(a);
      
      print(a);
      int i = parition_selectMaxAsPivot(a, 0, a.length - 1);
      StdOut.printf("枢轴被交换到了 %d\n", i);
      print(a);
      
      print(copy);
      int j = parition_selectMinAsPivot(copy, 0, copy.length - 1);
      StdOut.printf("枢轴被交换到了 %d\n", j);
      print(copy);
      
      /*
       * 对于值只有两种选择的数组来说
       * 由于快速排序的切分算法，会实现 a[lo ... j - 1] <= v <= a[j + 1 ... hi] 的效果
       * 即一次切分后，位于切分元素左侧的值都不大于 v, 位于切分元素右侧的值都不小于 v
       * 所以假如一开始选择了小的值作为枢轴，那么一次分割后，枢轴左侧的值全部都相同，并且都等于枢轴
       * 假如一开始选择了大的值作为枢轴，那么一次分割后，枢轴右侧的值全部都相同，并且都等于枢轴
       * 
       *    🔥 选择最大值最为枢轴，可以看到，枢轴右侧的值都相同且都等于枢轴
       *    0   1   2   3   4   5   6   7   8   9   
            1   1   2   1   1   2   2   1   2   1   
            枢轴被交换到了 7
            
            0   1   2   3   4   5   6   7   8   9   
            1   1   1   1   1   2   1   2   2   2   

            
            🔥 选择最小值作为枢轴，可以看到，枢轴左侧的值都相同且都等于枢轴
            0   1   2   3   4   5   6   7   8   9   
            1   1   2   1   1   2   2   1   2   1   
            枢轴被交换到了 3
            
            0   1   2   3   4   5   6   7   8   9   
            1   1   1   1   1   2   2   2   2   1   
 
       * 
       * 对于值只有三种选择的数组来说
       * 
       * 如果选择了最小值作为枢轴，同上
       * 如果选择了最大值作为枢轴，同上
       * 如果选择了中间值作为枢轴，如果这个中间值只有一个，那一轮切分后，就整体有序了
       * 如果中间值不止一个，那么左右两边啥情况都有可能
       * 
       */
  }
}
