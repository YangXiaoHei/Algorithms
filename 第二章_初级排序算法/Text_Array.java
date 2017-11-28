package 第二章_初级排序算法;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Text_Array {
    /*
     * 产生一个 元素类型为 Integer 的数组, 默认元素上下界为 [-100, 100)
     * 
     * @param N 数组尺寸
     */
    public static Integer[] sourceArr(int N) {
        if (N < 0)
            throw new IllegalArgumentException("array size cannot be negative!");
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) 
            arr[i] = new Integer(StdRandom.uniform(-100, 100));
        return arr;
    }
    /*
     * 产生一个 元素类型为 Integer 的数组
     * 
     * @param N 数组尺寸
     * @param lo 随机数下界，可以取到
     * @param hi 随机数上界，不能取到
     */
    public static Integer[] sourceArr(int N, int lo, int hi) {
        if (N < 0)
            throw new IllegalArgumentException("array size cannot be negative!");
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot be greater or equal than hi!");
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) 
            arr[i] = new Integer(StdRandom.uniform(lo, hi));
        return arr;
    }
    /*
     * 产生一个 元素类型为 Integer 的无重复元素数组
     * 
     * @param N 数组尺寸
     * @param lo 随机数下界，可以取到
     * @param hi 随机数上界，不能取到
     */
    public static Integer[] sourceArrNoDupli(int N, int lo, int hi) {
        if (N < 0)
            throw new IllegalArgumentException("array size cannot be negative!");
        if (N > (hi - lo))
            throw new IllegalArgumentException(String.format("N cannot be greater than %d", hi - lo));
        Set<Integer> set = new HashSet<Integer>();
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            int r = StdRandom.uniform(lo, hi);
            while (set.contains(r))
                r = StdRandom.uniform(lo, hi);
            arr[i] = r;
            set.add(r);
        }
        return arr;
    }
    /*
     * 产生一个 元素类型为 Integer 的部分有序数组，会随机产生占数组尺寸 20% 的逆序对
     * 
     * @param N 数组尺寸
     * @param lo 随机数下界，可以取到
     * @param hi 随机数上界，不能取到
     */
    public static Integer[] partialOrder(int N, int lo, int hi) {
        if (N < 2)
            throw new IllegalArgumentException("array size cannot less than 2!");
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot be greater or equal than hi!");
        Integer[] arr = sourceArrNoDupli(N, lo, hi);
        Arrays.sort(arr);
        int reverse = N / 5;
        while (reverse-- > 0) {
            int i = StdRandom.uniform(N);
            int j = StdRandom.uniform(N);
            while (j == i)
                j = StdRandom.uniform(N);
            Integer t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
        return arr;
    }
    /*
     * 产生一个 元素类型为 Integer 的逆序数组
     * 
     * @param N 数组尺寸
     * @param lo 随机数下界，可以取到
     * @param hi 随机数上界，不能取到
     */
    public static Integer[] reverseOrder(int N, int lo, int hi) {
        if (N < 0)
            throw new IllegalArgumentException("array size cannot be negative!");
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot greater or equal than hi!");
        Integer[] arr = sourceArr(N, lo, hi);
        Arrays.sort(arr);
        int i = 0, j = N - 1;
        while (i <= j) {
            Integer t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
            i++; j--;
        }
        return arr;
    }
    /*
     * 带索引的打印一个数组
     * 
     * @param 待打印数组
     */
    public static void printWithIndexs(Comparable[] a) {
        if (a == null)
            throw new NullPointerException();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-5d", i);
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-5s", a[i].toString());
        StdOut.println();
    }
}
