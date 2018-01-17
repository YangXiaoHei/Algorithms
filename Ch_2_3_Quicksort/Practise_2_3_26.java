package Ch_2_3_Quicksort;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_3_26 {
    public static Map<Integer, Integer> quick(int[] a, int M) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        quick(a, 0, a.length - 1, M, map);
        return map;
    }
    private static void quick(int[] a, int lo, int hi, int M, Map<Integer, Integer> map) {
        if (hi - lo + 1 < M) {
            map.put(hi - lo + 1, map.get(hi - lo + 1) == null ? 1 : map.get(hi - lo + 1) + 1);
            for (int i = lo; i <= hi; i++) {
                int t = a[i], j;
                for (j = i - 1; j >= 0 && t < a[j]; j--)
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
            return;
        }
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
        int t = a[j]; a[j] = a[lo]; a[lo] = t;
        quick(a, lo, j - 1, M, map);
        quick(a, lo, j + 1, M, map);
    }
    public static void draw(int N, int M) {
        
        // 0 ~ N - 1 的无重复随机值，从直方图可以看出，划分及其不均匀
        int[] a = ints(0, N - 1);
        
        // 全部值都相同的数组，可以看出，这种数组让快速排序的划分更加均匀
//        int[] a = allSameInts(N - 1, 1);
        
        Map<Integer, Integer> map = quick(a, M);
        
        // 供确认绘制的直方图是否正确
        StdOut.println(map);
        
        // 找出最大的 X 和 Y
        int maxSize = 0, maxCount = 0;
        int[] sizes = IntegerToInt(map.keySet().toArray());
        int[] counts = IntegerToInt(map.values().toArray());
        for (int i : sizes)
            if (i > maxSize) maxSize = i;
        for (int i : counts)
            if (i > maxCount) maxCount = i;
        StdDraw.setXscale(-1, maxSize + maxSize * 0.1);
        StdDraw.setYscale(-1, maxCount + maxCount * 0.1);
        
        // 绘制直方图
        for (int i = 0; i <= maxSize; i++) {
            int count = map.get(i) == null ? 0 : map.get(i);
            double x = i;
            double y = count / 2.0;
            double rw = 1.0 / 2;
            double rh = count / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);;
        }
    }
    public static void main(String[] args) {
        draw(10000000, 25);
    }
}   
