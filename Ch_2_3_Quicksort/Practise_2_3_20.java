package Ch_2_3_Quicksort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_3_20 {
   static class Stack {
       private static int[] items = new int[1];
       private static int size;
       void resize(int newSize) {
           int[] newItems = new int[newSize];
           for (int i = 0; i < size; i++)
               newItems[i]= items[i];
           items = newItems;
       }
       boolean isEmpty() { return size == 0; }
       Stack push(int item) {
           if (size == items.length)
               resize(size * 2);
           items[size++] = item; 
           return this;
       }
       int pop() {
           if (size == 0) 
               throw new RuntimeException("empty stack");
           int t = items[--size];
           if (size > 0 && size == items.length / 4)
               resize(items.length / 2);
           return t;
       }
   }
   public static double quick(int[] a) {
       Stopwatch timer = new Stopwatch();
       Stack stk = new Stack();
       stk.push(0).push(a.length - 1);
       quick(a, stk);
       return timer.elapsedTime();
   }
   private static void quick(int[] a,Stack stk) {
       while (!stk.isEmpty()) {
           int hi = stk.pop(), lo = stk.pop();
           // 入栈范围的大小判断避免了在这里写的 if (lo >= hi) continue
           int i = lo, j = hi + 1, v = a[lo];
           while (true) {
               while (i < hi && a[++i] < v);
               while (a[--j] > v);
               if (i >= j) break;
               int t = a[i]; a[i] = a[j]; a[j] = t;
           }
           int t = a[j]; a[j] = a[lo]; a[lo] = t;
           if (j + 1 < hi)  // 如果单元素或者范围是负数，就过，免得初入栈引起的扩容和缩容操作
               stk.push(j + 1).push(hi); // 先放右子数组
           if (lo < j - 1)
               stk.push(lo).push(j - 1); // 再放左子数组
       }
   }
   public static double _quick(int[] a) {
       Stopwatch timer = new Stopwatch();
       _quick(a, 0, a.length - 1);
       return timer.elapsedTime();
   }
   private static void _quick(int[] a, int lo, int hi) {
       if (lo >= hi) return;
       int i = lo, j = hi + 1, v = a[lo];
       while (true) {
           while (i < hi && a[++i] < v);
           while (a[--j] > v);
           if (i >= j) break;
           int t = a[i]; a[i] = a[j]; a[j] = t;
       }
       int t = a[lo]; a[lo] = a[j]; a[j] = t;
       _quick(a, lo, j - 1);
       _quick(a, j + 1, hi);
   }
   public static void main(String[] args) {
       int[] a = ints(0, 10000000);
       int[] copy = copy(a);
       StdOut.printf("非递归 耗时 ：%.3f\n", quick(a));
       StdOut.printf("递归 耗时 : %.3f\n", _quick(copy));
       assert isSorted(a);
       assert isSorted(copy);
   }
   // output
   /*
    *   非递归 耗时 ：2.226
        递归 耗时 : 2.460
        
        非递归 耗时 ：2.178
        递归 耗时 : 2.599
        
        非递归 耗时 ：2.763
        递归 耗时 : 3.162
    */
    
}
