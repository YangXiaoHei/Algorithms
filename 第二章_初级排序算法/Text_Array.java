package 第二章_初级排序算法;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Text_Array {
    /*
     * 生成一个从 0 到 N 的自然增长序列的 Integer 数组
     * 
     * @param N 元素上界，能取到
     */
    public static Integer[] sourceArr(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        return sourceArr(0, N);
    }
    /*
     * 返回一个已经排序完毕元素类型为 int 的数组
     * 
     * @param N 元素上界，能取到
     */
    public static int[] sortedArr(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        int[] arr = sourceArrIntRandom(N);
        Arrays.sort(arr);
        return arr;
    }
    /*
     * 返回一个个元素都相等的 int 数组
     * 
     * @param N 元素数量
     * @param key 值
     */
    public static int[] allIdenticalArr(int N, int key) {
        if (N <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        int[] arr = new int[N];
        Arrays.fill(arr, key);
        return arr;
    }
    
    /*
     * 生成一个从 lo 到 hi 的自然增长序列的 int 数组
     * 
     * @param lo 元素下界，能取到
     * @param hi 元素上界，能取到
     */
    public static int[] sourceArrInt(int lo, int hi) {
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot be greater or equal than hi!");
        int N = hi - lo + 1;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) 
            arr[i] = lo++;
        return arr;
    }
    /*
     * 生成一个从 lo 到 hi 的自然增长序列的 Integer 数组
     * 
     * @param lo 元素下界，能取到
     * @param hi 元素上界，能取到
     */
    public static Integer[] sourceArr(int lo, int hi) {
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot be greater or equal than hi!");
        int N = hi - lo + 1;
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) 
            arr[i] = lo++;
        return arr;
    }
    /*
     * 产生一个 元素类型为 Integer 的随机数组, 默认元素上下界为 [-100, 100)
     * 
     * @param N 数组尺寸
     */
    public static Integer[] sourceArrRandom(int N) {
        if (N < 0)
            throw new IllegalArgumentException("array size cannot be negative!");
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) 
            arr[i] = new Integer(StdRandom.uniform(-100, 100));
        return arr;
    }
    /*
     * 产生一个 元素类型为 int 的随机数组, 默认元素上下界为 [-100, 100)
     * 
     * @param N 数组尺寸
     */
    public static int[] sourceArrIntRandom(int N) {
        Integer[] arr = sourceArrRandom(N);
        int[] arri = new int[N];
        for (int i = 0; i < N; i++)
            arri[i] = arr[i].intValue();
        return arri;
    }
    /*
     * 产生一个 元素类型为 Integer 的随机数组
     * 
     * @param N 数组尺寸
     * @param lo 随机数下界，可以取到
     * @param hi 随机数上界，不能取到
     */
    public static Integer[] sourceArrRandom(int N, int lo, int hi) {
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
     * 通过已有的 Integer 数组拷贝一个新的 Integer 数组，
     * 注意这里并非只拷贝了数组里的指针，而是重新分配了每个元素的内存地址
     * 
     * @param src 待拷贝的源数组
     * @return 拷贝后的新数组
     */
    public static Integer[] copy(Integer[] src) {
        if (src == null)
            throw new IllegalArgumentException("source array cannot be null!");
        Integer[] copy = new Integer[src.length];
        for (int i = 0; i < src.length; i++) 
            copy[i] = src[i].intValue();
        return copy;
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
        return partialOrder(N, lo, hi, 0.2);
    }
    /*
     * 产生一个 元素类型为 Integer 的部分有序数组，会随机产生指定比列的逆序对
     * 
     * @param N 数组尺寸
     * @param lo 随机数下界，可以取到
     * @param hi 随机数上界，不能取到
     */
    public static Integer[] partialOrder(int N, int lo, int hi, double scale) {
        if (N < 2)
            throw new IllegalArgumentException("array size cannot less than 2!");
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot be greater or equal than hi!");
        Integer[] arr = sourceArrNoDupli(N, lo, hi);
        Arrays.sort(arr);
        int reverse = (int)(N * scale);
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
     * 返回一个每个元素任意数量的 int 数组，数组中每个值可以由可变参数指定
     * 
     * @param N 数组尺寸
     * @param ... 数组中需要填充的元素值
     */
    public static int[] threeValueArr(int N, int ...values) {
        int count = values.length;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = values[StdRandom.uniform(count)];
        return arr;
    }
    /*
     * 产生一个 元素类型为 Integer 的逆序数组，元素从 hi 到 lo 递减
     * 
     * @param N 数组尺寸
     * @param lo 元素下界，可以取到
     * @param hi 元素上界，可以取到
     */
    public static Integer[] reverseOrder(int hi, int lo) {
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot greater or equal than hi!");
        int N = hi - lo + 1;
        Integer[] arr = sourceArr(lo, hi);
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
     * 产生一个 元素类型为 Integer 的逆序数组，元素从 hi 到 lo 递减
     * 
     * @param N 数组尺寸
     * @param lo 元素下界，可以取到
     * @param hi 元素上界，可以取到
     */
    public static int[] reverseOrderInt(int hi, int lo) {
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot greater or equal than hi!");
        int N = hi - lo + 1;
        int[] arr = sourceArrInt(lo, hi);
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
    public static void printWithIndexs(int[] a) {
        if (a == null)
            throw new NullPointerException();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-5d", i);
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-5d", a[i]);
        StdOut.println();
    }
}
