package 第二章_初级排序算法;

import java.util.*;
import edu.princeton.cs.algs4.*;

/*
 * **********************************************************************
 * 这是一个生成供排序测试数组的工具类，在此工具类中，方法的实现和命名统一遵守下列隐含条件
 * 
 * 1, 对于指定了元素取值左右边界的函数，边界默认是闭区间，而非半开半闭区间，换言之，上界和下界都能取到
 * 2, Java 支持函数重载，因此不再按照自解释的长句子给函数命名，尽量以简洁为主
 * 3, 如果没有额外说明，函数默认生成的是随机数组，元素取值域为 [-size, size]
 * 4, 对于需要生成无重复随机值的函数，元素取值域为 [0, size * 10]
 * **********************************************************************
 */
public class Text_Array {
    public static boolean equal(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++)
            if (a[i] != b[i]) return false;
        return true;
    }
    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] < a[i - 1]) return false;
        return true;
    }
    public static boolean isSorted(double[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] < a[i - 1]) return false;
        return true;
    }
    public static boolean isSorted(Double[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(a[i - 1]) < 0) return false;
        return true;
    }
    public static int binarySearch(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if      (a[mid] < key) lo = mid + 1;
            else if (a[mid] > key) hi = mid - 1;
            else    return mid;
        }
        return -1;
    }
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
     * 返回一个数组，其中的每个元素距离最终位置的距离不超过 N
     * 
     * @param N 数组尺寸 
     * @param distance 偏移距离
     */
    public static int[] whthinDistance(int N, int distance) {
        int[] sorted = intsNoDupli(N);
        Arrays.sort(sorted);
        int[] swapped = intsCopy(sorted);
        for (int j = 0; j < N; j++) 
            for (int k = j + 1; k < N; k++) {
                if (Math.abs(binarySearch(sorted, swapped[j]) - k) <= distance &&
                    Math.abs(binarySearch(sorted, swapped[k]) - j) <= distance) {
                    int t = swapped[j];
                    swapped[j] = swapped[k];
                    swapped[k] = t;
                }
            }
        return swapped;
    }
    /*
     * 返回按照高斯分布的 N 个值，均值为 mu
     * 
     * @param N 数组尺寸
     * @param mu 均值 u
     */
    public static double[] gaussian(int N, double mu) {
        double[] arr = new double[N];
        for (int i = 0; i < N; i++)
            // 平均值是 u, 那么产生的值最有可能是 u，产生其他值的概率随着大于或小于 u 变得越来越小
            arr[i] = StdRandom.gaussian(mu, 1);
        return arr;
    }
    /*
     * 返回按照泊松分布的 N 个值
     * 
     * @param N 数组尺寸
     * @param lambda 单位时间内发生的次数
     */
    public static double[] possion(int N, int lambda) {
        double[] arr = new double[N];
        for (int i = 0; i < N; i++)
            // 单位时间内发生某件事的频度是 lambda，那么下一个单位时间内会发生该事件的次数是？
            arr[i] = StdRandom.poisson(lambda);
        return arr;
    }
    /*
     * 返回按照几何分布的 N 个值
     * 
     * @param N 数组尺寸
     * @param success 独立试验成功的概率
     */
    public static double[] geometric(int N, double success) {
        double[] arr = new double[N];
        for (int i = 0; i < N; i++)
            // 如果某件事成功的概率是 0.1, 那么做了多少次这件事才第一次成功 ?
            arr[i] = StdRandom.geometric(success);
        return arr;
    }
    /*
     * 返回按照指定概率离散分布的 N 个值
     * 
     * @param N 数组尺寸
     * @param 概率集，加起来必须等于1
     */
    public static double[] discrete(int N, double...probabilities) {
        if (probabilities == null) throw new IllegalArgumentException("argument array is null");
        double EPSILON = 1E-14;
        double sum = 0.0;
        for (int i = 0; i < probabilities.length; i++) {
            if (!(probabilities[i] >= 0.0))
                throw new IllegalArgumentException("array entry " + i + " must be nonnegative: " + probabilities[i]);
            sum += probabilities[i];
        }
        if (sum > 1.0 + EPSILON || sum < 1.0 - EPSILON)
            throw new IllegalArgumentException("sum of array entries does not approximately equal 1.0: " + sum);
        double[] arr = new double[N];
        for (int i = 0; i < N; i++)
            // 给定 0 ~ N 的每个数出现的概率，那么下一次产生 0 ~ N 中的几 ？
            arr[i] = StdRandom.discrete(probabilities);
        return arr;
    }
    public static int[] parseInts(String s) {
        String[] sArr = s.split("\\s+");
        int[] arr = new int[sArr.length];
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(sArr[i]);
        return arr;
    }
    public static char[] parseChar(String s) {
        String[] ss = s.split("\\s+");
        char[] c = new char[ss.length];
        for (int i = 0; i < c.length; i++)
            c[i] = ss[i].charAt(0);
        return c;
    }
    /*
     * 从字符串中解析出 int 数组
     * 
     * @param s 形如 "1,2,3,4,5,6,6" 的字符串，由 Arrays.toString(new int[]{1,2,3,4,5,6,6}) 产生
     * 
     */
    public static int[] parseIntsString(String s) {
        String[] sArr = s.split(",\\s*");
        int[] arr = new int[sArr.length];
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(sArr[i]);
        return arr;
    }
    /*
     * 从字符串中解析出 Double 数组
     * 
     * @param s 形如 "1.0,2.0,3.0,4.0,5.0,6.0" 的字符串，由 Arrays.toString(new double[]{1.0,2.0,3.0,4.0,5.0,6.0}) 产生
     * 
     */
    public static Double[] parseDoublesString(String s) {
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
    /*
     * 把 Object[] 转成 long[]，必须要是被类型擦除的 Long[] 才行，否则抛出非法参数异常
     * 
     * @param oarr 待转换的数组
     * @throw 空指针异常，非法参数异常
     */
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
     * 把 Double[] 转成 double[]
     * 
     * @param oarr 待转换的数组
     * @throw 空指针异常，非法参数异常
     */
    public static double[] DoubleTodouble(Double[] darr) {
        if (darr == null)
            throw new NullPointerException();
        for (Double o : darr)
            if (!(o instanceof Double))
                throw new IllegalArgumentException("wrong element type! cast fail!");
        int N = darr.length;
        double[] arr = new double[N];
        for (int i = 0; i < N; i++)
            arr[i] = ((Double)darr[i]).doubleValue();
        return arr;
    }
    
    /*
     * 生成一个从 0 到 N 的自然增长序列的 Integer 数组
     * 
     * @param N 元素上界，能取到
     * @throw 非法参数异常
     */
    public static Integer[] Integers(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        if (size == Integer.MAX_VALUE)
            throw new IllegalArgumentException("this size will result in overflow while generate random value");
        Integer[] d = new Integer[size];
        for (int i = 0; i < size; i++)
            d[i] = StdRandom.uniform(-size, size + 1);
        return d;
    }
    /*
     * 生成一个元素取值从 lo 到 hi 的乱序 Integer 数组
     * 
     * @param lo 元素下界，能取到
     * @param hi 元素上界，能取到
     * @throw 非法参数异常
     */
    public static Integer[] Integers(int lo, int hi) {
        Integer[] arr = ascendIntegers(lo, hi);
        shuffle(arr);
        return arr;
    }
    /*
     * 产生一个 元素类型为 Integer 的随机数组
     * 
     * @param N 数组尺寸
     * @param lo 随机数下界，可以取到
     * @param hi 随机数上界，不能取到
     * @throw 非法参数异常
     */
    public static Integer[] Integers(int size, int lo, int hi) {
        if (size < 0)
            throw new IllegalArgumentException("array size cannot be negative!");
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot be greater or equal than hi!");
        if (hi == Integer.MAX_VALUE)
            throw new IllegalArgumentException("this right bounds of " + hi + 
                    " will result in overflow while generating random value!");
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) 
            arr[i] = new Integer(StdRandom.uniform(lo, hi + 1));
        return arr;
    }
    
    public static Integer[] ascendIntegers(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        Integer[] arr = Integers(size);
        Arrays.sort(arr);
        return arr;
    }
    /*
     * 生成一个从 lo 到 hi 的自然增长序列的 Integer 数组
     * 
     * @param lo 元素下界，能取到
     * @param hi 元素上界，能取到
     * @throw 非法参数异常
     */
    public static Integer[] ascendIntegers(int lo, int hi) {
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot be greater or equal than hi!");
        int N = hi - lo + 1;
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) 
            arr[i] = lo++;
        return arr;
    }
    public static Integer[] descendIntegers(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        Integer[] arr = Integers(size);
        Arrays.sort(arr);
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            Integer t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
            i++; j--;
        }
        return arr;
    }
    /*
     * 产生一个 元素类型为 Integer 的降序排列数组，元素从 hi 到 lo 递减
     * 
     * @param N 数组尺寸
     * @param lo 元素下界，可以取到
     * @param hi 元素上界，可以取到
     */
    public static Integer[] descendIntegers(int hi, int lo) {
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot greater or equal than hi!");
        Integer[] arr = Integers(lo, hi);
        Arrays.sort(arr);
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            Integer t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
            i++; j--;
        }
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
    public static Integer[] IntegersCopy(Integer[] src) {
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
     * @param size 数组尺寸
     */
    public static Integer[] IntegersNoDupli(int size) {
        if (size < 0)
            throw new IllegalArgumentException("array size cannot be negative!");
        if (size > (Integer.MAX_VALUE - 1) / 10)
            throw new IllegalArgumentException("size is too large to generate a no duplicated array");
        Set<Integer> set = new HashSet<Integer>();
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            int r = StdRandom.uniform(size * 10 + 1);
            while (set.contains(r))
                r = StdRandom.uniform(size * 10 + 1);
            arr[i] = r;
            set.add(r);
        }
        return arr;
    }
    /*
     * 产生一个 元素类型为 Integer 的无重复元素数组
     * 
     * @param N 数组尺寸
     * @param lo 随机数下界，可以取到
     * @param hi 随机数上界，不能取到
     */
    public static Integer[] IntegersNoDupli(int size, int lo, int hi) {
        if (size < 0)
            throw new IllegalArgumentException("array size cannot be negative!");
        if (size > (hi - lo + 1))
            throw new IllegalArgumentException(String.format("N cannot be greater than %d", hi - lo + 1));
        Set<Integer> set = new HashSet<Integer>();
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            int r = StdRandom.uniform(lo, hi + 1);
            while (set.contains(r))
                r = StdRandom.uniform(lo, hi + 1);
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
    public static Integer[] IntegersPartialOrder(int size, int lo, int hi) {
        return IntegersPartialOrder(size, lo, hi, 0.2);
    }
    /*
     * 产生一个 元素类型为 Integer 的部分有序数组，会随机产生指定比列的逆序对
     * 
     * @param N 数组尺寸
     * @param lo 随机数下界，可以取到
     * @param hi 随机数上界，不能取到
     */
    public static Integer[] IntegersPartialOrder(int size, int lo, int hi, double scale) {
        if (size < 2)
            throw new IllegalArgumentException("array size cannot less than 2!");
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot be greater or equal than hi!");
        Integer[] arr = IntegersNoDupli(size, lo, hi);
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
     * 生成一个从 lo 到 hi 的自然增长序列的 int 数组
     * 
     * @param lo 元素下界，能取到
     * @param hi 元素上界，能取到
     * @throw 非法参数异常
     */
    public static int[] ints(int lo, int hi) {
        return IntegerToInt(Integers(lo, hi));
    }
    /*
     * 产生一个 元素类型为 int 的随机数组, 默认元素上下界为 [-size, size]
     * 
     * @param N 数组尺寸
     * @throw 非法参数异常
     */
    public static int[] ints(int size) {
        return IntegerToInt(Integers(size));
    }
    /*
     * 产生一个 元素类型为 int 的随机数组
     * 
     * @param N 数组尺寸
     * @param lo 随机数下界，可以取到
     * @param hi 随机数上界，不能取到
     * @throw 非法参数异常
     */
    public static int[] ints(int size, int lo, int hi) {
        return IntegerToInt(Integers(size, lo, hi));
    }
    /*
     * 返回一个已经排序完毕元素类型为 int 的数组
     * 
     * @param N 元素上界，能取到
     * @throw 非法参数异常
     */
    public static int[] ascendInts(int size) {
        return IntegerToInt(ascendIntegers(size));
    }
    public static int[] ascendInts(int lo, int hi) {
        return IntegerToInt(ascendIntegers(lo, hi));
    }
    public static int[] descendInts(int size) {
        return IntegerToInt(descendIntegers(size));
    }
    public static int[] descendInts(int hi, int lo) {
        return IntegerToInt(descendIntegers(hi, lo));
    }
    /*
     * 返回一个个元素都相等的 int 数组
     * 
     * @param N 元素数量
     * @param key 值
     * @throw 非法参数异常
     */
    public static int[] allSameInts(int size, int key) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        int[] arr = new int[size];
        Arrays.fill(arr, key);
        return arr;
    }
    /*
     * 通过已有的 int 数组拷贝一个新的 int 数组
     * 
     * @param src 待拷贝的源数组
     * @return 拷贝后的新数组
     * @throw 非法参数异常
     */
    public static int[] intsCopy(int[] src) {
        if (src == null)
            throw new IllegalArgumentException("source array cannot be null!");
        int[] copy = new int[src.length];
        for (int i = 0; i < src.length; i++) 
            copy[i] = src[i];
        return copy;
    }
    /*
     * 产生一个 元素类型为 int 的无重复元素数组
     * 
     * @param size 数组尺寸
     */
    public static int[] intsNoDupli(int size) {
        return IntegerToInt(IntegersNoDupli(size));
    }
    /*
     * 产生一个 元素类型为 int 的无重复元素数组
     * 
     * @param N 数组尺寸
     * @param lo 随机数下界，可以取到
     * @param hi 随机数上界，不能取到
     */
    public static int[] intsNoDupli(int size, int lo, int hi) {
        return IntegerToInt(IntegersNoDupli(size, lo, hi));
    }
    /*
     * 返回一个每个元素任意数量的 int 数组，数组中每个值可以由可变参数指定
     * 
     * @param N 数组尺寸
     * @param ... 数组中需要填充的元素值
     */
    public static int[] intsVrg(int size, int ...values) {
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
    public static int[] intsVrgWithEachAmount(int eachAmount, int ...values) {
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
     * 生成一个适合改进条件 a[mid] < a[mid + 1] 发挥作用的数组，适合程度仅次于完全排序就位的数组
     * 
     * @param N 数组尺寸，并不一定会用这个值，而是使用 满足 2 的幂次方的小于 N 的最大值
     * 
     * 生成的数组如下 [2 1 4 3 6 5 8 7 10 9 12 11 ....]
     */
    public static int[] forMergeSortTest(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        int k = (int)(Math.log(N) / Math.log(2));
        N = (int)Math.pow(2, k);
        int base = 2, beg = 2, i = 2;
        int[] arr = new int[N];
        int index = 0;
        while (true) {
            for (int m = 0; m < 2; m++) 
                arr[index++] = beg--;
            if (index == N) break;
            beg = (i++) * base;
        }
        return arr;
    }
    /*
     * 生成一个元素值从 0 到 1 浮点数随机分布的数组
     * 
     * @param N 数组尺寸
     * @throw 非法参数异常
     */
    public static Double[] Doubles(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        Double[] d = new Double[size];
        for (int i = 0; i < size; i++)
            d[i] = StdRandom.uniform();
        return d;
    }
    /*
     * 通过已有的 Double 数组拷贝一个新的 Double 数组
     * 
     * @param src 待拷贝的源数组
     * @return 拷贝后的新数组
     * @throw 非法参数异常
     */
    public static Double[] DoublesCopy(Double[] src) {
        if (src == null)
            throw new IllegalArgumentException("source array cannot be null!");
        Double[] copy = new Double[src.length];
        for (int i = 0; i < src.length; i++) 
            copy[i] = src[i].doubleValue();
        return copy;
    }
    /*
     * 生成一个元素值从 0 到 1 浮点数随机分布的数组
     * 
     * @param N 数组尺寸
     * @throw 非法参数异常
     */
    public static double[] doubles(int size) {
        return DoubleTodouble(Doubles(size));
    }
    /*
     * 通过已有的 double 数组拷贝一个新的 double 数组
     * 
     * @param src 待拷贝的源数组
     * @return 拷贝后的新数组
     * @throw 非法参数异常
     */
    public static double[] doublesCopy(double[] src) {
        if (src == null)
            throw new IllegalArgumentException("source array cannot be null!");
        double[] copy = new double[src.length];
        for (int i = 0; i < src.length; i++) 
            copy[i] = src[i];
        return copy;
    }
    /*
     * 带索引的打印一个 Comparable 数组
     * 
     * @param 待打印数组
     */
    public static void print(Comparable[] a) {
        if (a == null || a.length == 0) return;
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-7d", i);
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-7s", a[i].toString());
        StdOut.println();
    }
    public static void print(Double[] a) {
        if (a == null || a.length == 0) return;
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-7d", i);
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-7.2f", a[i].doubleValue());
        StdOut.println();
    }
    /*
     * 带索引的打印一个 double 数组
     * 
     * @param 待打印数组
     */
    public static void print(double[] a) {
        if (a == null || a.length == 0) return;
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-5d", i);
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-5.1f", a[i]);
        StdOut.println();
    }
    /*
     * 带索引的打印一个 int 数组
     * 
     * @param 待打印数组
     */
    public static void print(int[] a) {
        if (a == null || a.length == 0) return;
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-4d", i);
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-4d", a[i]);
        StdOut.println();
    }
    public static void print(char[] a) {
        if (a == null || a.length == 0) return;
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-4d", i);
        StdOut.println();
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%-4c", a[i]);
        StdOut.println();
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
     * Integers[] 的测试
     * 
     */
    public static void IntegersTest() {
        Integer[] arr1 = Integers(10);
        print(arr1);
        
        arr1 = Integers(10, 20);
        print(arr1);
        
        arr1 = Integers(10, -100, 100);
        print(arr1);
        
        arr1 = ascendIntegers(10);
        print(arr1);
        
        arr1 = ascendIntegers(-10, 10);
        print(arr1);
        
        arr1 = descendIntegers(10);
        print(arr1);
        
        arr1 = descendIntegers(10, 0);
        print(arr1);
        
        arr1 = IntegersNoDupli(10);
        print(arr1);
        
        arr1 = IntegersNoDupli(3, 1, 10);
        print(arr1);
        
        arr1 = IntegersPartialOrder(10, 1, 10);
        print(arr1);
        
        arr1 = IntegersPartialOrder(10, 1, 10, 0.8);
        print(arr1);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
     * int[] 的测试
     */
    public static void intsTest() {
        int[] arr = ints(10);
        print(arr);
        
        arr = ints(10, 20);
        print(arr);
        
        arr = ints(15, 0, 5);
        print(arr);
        
        arr = ascendInts(10);
        print(arr);
        
        arr = ascendInts(10, 20);
        print(arr);
        
        arr = descendInts(10);
        print(arr);
        
        arr = allSameInts(10, 5);
        print(arr);
        
        arr = intsNoDupli(10);
        print(arr);
        
        arr = intsNoDupli(5, 0, 10);
        print(arr);
        
        arr = intsVrg(10, 4, 8, 9);
        print(arr);
        
        arr = intsVrgWithEachAmount(4, 1, 2, 3);
        print(arr);
        
        arr = forMergeSortTest(10);
        print(arr);
    }
    public static void main(String[] args) {
    }
}
