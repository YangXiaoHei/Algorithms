package 第二章_初级排序算法;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Text_Array {
    public static int[] parseIntFromString(String s) {
        String[] sArr = s.split(",\\s*");
        int[] arr = new int[sArr.length];
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(sArr[i]);
        return arr;
    }
    public static Double[] parseDoubleFromString(String s) {
        String[] sArr = s.split(",\\s*");
        Double[] arr = new Double[sArr.length];
        for (int i = 0; i < arr.length; i++)
            arr[i] = Double.parseDouble(sArr[i]);
        return arr;
    }
    /*
     * 把 Integer[] 转成 int[]
     * 
     * @param iarr 待转换的数组
     * @throw 空指针异常
     */
    public static int[] IntegerToInt(Integer[] iarr) {
        if (iarr == null)
            throw new NullPointerException();
        int[] arr = new int[iarr.length];
        for (int i = 0; i < iarr.length; i++)
            arr[i] = iarr[i].intValue();
        return arr;
    }
    /*
     * 把 Object[] 转成 int[]，必须要是被类型擦除的 Integer[] 才行，否则抛出非法参数异常
     * 
     * @param oarr 待转换的数组
     * @throw 空指针异常，非法参数异常
     */
    public static int[] IntegerToInt(Object[] oarr) {
        if (oarr == null)
            throw new NullPointerException();
        for (Object o : oarr)
            if (!(o instanceof Integer))
                throw new IllegalArgumentException("wrong element type! cast fail!");
        int N = oarr.length;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = ((Integer)oarr[i]).intValue();
        return arr;
    }
    public static long[] LongTolong(Object[] oarr) {
        if (oarr == null)
            throw new NullPointerException();
        for (Object o : oarr)
            if (!(o instanceof Long))
                throw new IllegalArgumentException("wrong element type! cast fail!");
        int N = oarr.length;
        long[] arr = new long[N];
        for (int i = 0; i < N; i++)
            arr[i] = ((Long)oarr[i]).longValue();
        return arr;
    }
    
    /*
     * 生成一个从 0 到 N 的自然增长序列的 Integer 数组
     * 
     * @param N 元素上界，能取到
     * @throw 非法参数异常
     */
    public static Integer[] Integer_rightBounds(int hi) {
        if (hi <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        return Integer_bounds(0, hi);
    }
    /*
     * 生成一个元素值从 0 到 1 浮点数随机分布的数组
     * 
     * @param N 数组尺寸
     * @throw 非法参数异常
     */
    public static Double[] DoubleRandom_size(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        Double[] d = new Double[size];
        for (int i = 0; i < size; i++)
            d[i] = StdRandom.uniform();
        return d;
    }
    /*
     * 返回一个已经排序完毕元素类型为 int 的数组
     * 
     * @param N 元素上界，能取到
     * @throw 非法参数异常
     */
    public static int[] intSorted_size(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        int[] arr = intRandom_size(size);
        Arrays.sort(arr);
        return arr;
    }
    /*
     * 返回一个个元素都相等的 int 数组
     * 
     * @param N 元素数量
     * @param key 值
     * @throw 非法参数异常
     */
    public static int[] intAllSame_size_key(int size, int key) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        int[] arr = new int[size];
        Arrays.fill(arr, key);
        return arr;
    }
    
    /*
     * 生成一个从 lo 到 hi 的自然增长序列的 int 数组
     * 
     * @param lo 元素下界，能取到
     * @param hi 元素上界，能取到
     * @throw 非法参数异常
     */
    public static int[] int_bounds(int lo, int hi) {
        return IntegerToInt(Integer_bounds(lo, hi));
    }
    /*
     * 生成一个从 lo 到 hi 的自然增长序列的 Integer 数组
     * 
     * @param lo 元素下界，能取到
     * @param hi 元素上界，能取到
     * @throw 非法参数异常
     */
    public static Integer[] Integer_bounds(int lo, int hi) {
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot be greater or equal than hi!");
        int N = hi - lo + 1;
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) 
            arr[i] = lo++;
        return arr;
    }
    /*
     * 生成一个元素取值从 lo 到 hi 的乱序 Integer 数组
     * 
     * @param lo 元素下界，能取到
     * @param hi 元素上界，能取到
     * @throw 非法参数异常
     */
    public static Integer[] IntegerRandom_bounds(int lo, int hi) {
        Integer[] arr = Integer_bounds(lo, hi);
        shuffle(arr);
        return arr;
    }
    /*
     * 产生一个 元素类型为 Integer 的随机数组, 默认元素上下界为 [-100, 100)
     * 
     * @param N 数组尺寸
     * @throw 非法参数异常
     */
    public static Integer[] IntegerRandom_size(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) 
            arr[i] = new Integer(StdRandom.uniform(-100, 100));
        return arr;
    }
    /*
     * 产生一个 元素类型为 int 的随机数组, 默认元素上下界为 [-100, 100)
     * 
     * @param N 数组尺寸
     * @throw 非法参数异常
     */
    public static int[] intRandom_size(int size) {
        return IntegerToInt(IntegerRandom_size(size));
    }
    /*
     * 产生一个取值范围是 [lo, hi] 的随机顺序的数组
     * 
     * @param lo 下界 可以取到
     * @param hi 上界 可以取到
     * @throw 非法参数异常
     */
    public static int[] intRandom_bounds(int lo, int hi) {
        return IntegerToInt(IntegerRandom_bounds(lo, hi));
    }
    public static int[] intRandom_size_bounds(int size, int lo, int hi) {
        return IntegerToInt(IntegerRandom_size_bounds(size, lo, hi));
    }
    /*
     * 产生一个 元素类型为 Integer 的随机数组
     * 
     * @param N 数组尺寸
     * @param lo 随机数下界，可以取到
     * @param hi 随机数上界，不能取到
     * @throw 非法参数异常
     */
    public static Integer[] IntegerRandom_size_bounds(int size, int lo, int hi) {
        if (size < 0)
            throw new IllegalArgumentException("array size cannot be negative!");
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot be greater or equal than hi!");
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) 
            arr[i] = new Integer(StdRandom.uniform(lo, hi));
        return arr;
    }
    /*
     * 通过已有的 Integer 数组拷贝一个新的 Integer 数组，
     * 注意这里并非只拷贝了数组里的指针，而是重新分配了每个元素的内存地址
     * 
     * @param src 待拷贝的源数组
     * @return 拷贝后的新数组
     * @throw 非法参数异常
     */
    public static Integer[] IntegerCopy_arr(Integer[] src) {
        if (src == null)
            throw new IllegalArgumentException("source array cannot be null!");
        Integer[] copy = new Integer[src.length];
        for (int i = 0; i < src.length; i++) 
            copy[i] = src[i].intValue();
        return copy;
    }
    /*
     * 通过已有的 int 数组拷贝一个新的 int 数组
     * 
     * @param src 待拷贝的源数组
     * @return 拷贝后的新数组
     * @throw 非法参数异常
     */
    public static int[] intCopy_arr(int[] src) {
        if (src == null)
            throw new IllegalArgumentException("source array cannot be null!");
        int[] copy = new int[src.length];
        for (int i = 0; i < src.length; i++) 
            copy[i] = src[i];
        return copy;
    }
    /*
     * 产生一个 元素类型为 Integer 的无重复元素数组
     * 
     * @param N 数组尺寸
     * @param lo 随机数下界，可以取到
     * @param hi 随机数上界，不能取到
     */
    public static Integer[] IntegerNoDupli_size_bounds(int size, int lo, int hi) {
        if (size < 0)
            throw new IllegalArgumentException("array size cannot be negative!");
        if (size > (hi - lo))
            throw new IllegalArgumentException(String.format("N cannot be greater than %d", hi - lo));
        Set<Integer> set = new HashSet<Integer>();
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
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
    public static Integer[] IntegerPartialOrder_size_bounds(int size, int lo, int hi) {
        return IntegerPartialOrder_size_bounds_ratio(size, lo, hi, 0.2);
    }
    /*
     * 产生一个 元素类型为 Integer 的部分有序数组，会随机产生指定比列的逆序对
     * 
     * @param N 数组尺寸
     * @param lo 随机数下界，可以取到
     * @param hi 随机数上界，不能取到
     */
    public static Integer[] IntegerPartialOrder_size_bounds_ratio(int size, int lo, int hi, double scale) {
        if (size < 2)
            throw new IllegalArgumentException("array size cannot less than 2!");
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot be greater or equal than hi!");
        Integer[] arr = IntegerNoDupli_size_bounds(size, lo, hi);
        Arrays.sort(arr);
        int reverse = (int)(size * scale);
        while (reverse-- > 0) {
            int i = StdRandom.uniform(size);
            int j = StdRandom.uniform(size);
            while (j == i)
                j = StdRandom.uniform(size);
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
    public static int[] int_size_vrg(int size, int ...values) {
        if (size <= 0 || values == null)
            throw new IllegalArgumentException();
        int count = values.length;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++)
            arr[i] = values[StdRandom.uniform(count)];
        return arr;
    }
    /*
     * 返回一个每个元素指定数量的 int 数组，数组中每个值可以由可变参数指定
     * 
     * @param eachAmount 每种元素填充的数量
     * @param ... 数组中需要填充的元素值
     */
    public static int[] int_amount_vrg(int eachAmount, int ...values) {
        if (values == null || eachAmount <= 0)
            throw new IllegalArgumentException();
        int count = eachAmount * values.length;
        int[] arr = new int[count];
        int cur = 0;
        for (int i = 0; i < values.length; i++)
            for (int j = 0; j < eachAmount; j++)
                arr[cur++] = values[i];
        shuffle(arr);
        return arr;
    }
    /*
     * 打乱一个数组
     */
    public static void shuffle(int[] a) {
        if (a == null)
            throw new NullPointerException();
        for (int i = 0; i < a.length; i++) {
            int r = i + StdRandom.uniform(a.length - i);
            int t = a[i];
            a[i] = a[r];
            a[r] = t;
        }
    }
    public static void shuffle(Integer[] a) {
        if (a == null)
            throw new NullPointerException();
        for (int i = 0; i < a.length; i++) {
            int r = i + StdRandom.uniform(a.length - i);
            int t = a[i];
            a[i] = a[r];
            a[r] = t;
        }
    }
    /*
     * 产生一个 元素类型为 Integer 的逆序数组，元素从 hi 到 lo 递减
     * 
     * @param N 数组尺寸
     * @param lo 元素下界，可以取到
     * @param hi 元素上界，可以取到
     */
    public static Integer[] IntegerReverseOrder_bounds(int hi, int lo) {
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot greater or equal than hi!");
        int N = hi - lo + 1;
        Integer[] arr = Integer_bounds(lo, hi);
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
    public static int[] intReverseOrder_bounds(int hi, int lo) {
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot greater or equal than hi!");
        int N = hi - lo + 1;
        int[] arr = int_bounds(lo, hi);
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
    public static void print(Comparable[] a) {
        if (a == null)
            throw new NullPointerException();
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-5d", i);
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-5s", a[i].toString());
        StdOut.println();
    }
    public static void print(Double[] a) {
        if (a == null)
            throw new NullPointerException();
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-10d", i);
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-10.3f", a[i]);
        StdOut.println();
    }
    
    public static void print(int[] a) {
        if (a == null)
            throw new NullPointerException();
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-5d", i);
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-5d", a[i]);
        StdOut.println();
    }
}
