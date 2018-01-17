package Tool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

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
public class ArrayGenerator {
    /*
     * 字母数组发生器
     */
    public static class Alphbets {
        private static String[] alphabet = new String[26];
        static {
            for (int i = 1; i <= 26; i++)
                alphabet[i - 1] = String.format("%c", 'A' + i - 1);
        }
        /*
         * 生成指定数目的随机字母
         */
        public static String[] random(int count) {
            String[] s = new String[count];
            for (int i = 0; i < count; i++)
                s[i] = alphabet[StdRandom.uniform(alphabet.length)];
            return s;
        }
        /*
         * 生成指定数目随机字母，无重复
         */
        public static String[] randomNoDupli(int count) {
            if (count > 26) throw new IllegalArgumentException();
            Set<String> set = new HashSet<String>();
            String[] ss = new String[count]; int i = 0;
            while (i < count) {
                String s = String.format("%c", 'A' + StdRandom.uniform(26));
                while (set.contains(s)) 
                    s = String.format("%c", 'A' + StdRandom.uniform(26));
                ss[i++] = s;
                set.add(s);
            }
            return ss;
        }
        /*
         * 将26个字母乱序后返回
         */
        public static String[] allRandom() {
            String[] s = new String[26];
            System.arraycopy(alphabet, 0, s, 0, 26);
            for (int i = 0; i < 25; i++) {
                int r = i + StdRandom.uniform(0, 26 - i);
                String t = s[r];
                s[r] = s[i];
                s[i] = t;
            }
            return s;
        }
        /*
         * 给出按照升序排列的26个字母
         */
        public static String[] allOrdered() {
            String[] s = new String[26];
            System.arraycopy(alphabet, 0, s, 0, 26);
            return s;
        }
        /*
         * 打印一个 String 数组
         */
        public static void print(String[] s) {
            for (int i = 0; i < s.length; i++)
                StdOut.printf("%4d", i);
            StdOut.println();
            for (int i = 0; i < s.length; i++)
                StdOut.printf("%4s", s[i]);
            StdOut.println("\n");
        }
    }
    /*
     * 两个 int[] 数组是否完全相等
     */
    public static boolean equal(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++)
            if (a[i] != b[i]) return false;
        return true;
    }
    /*
     * int[] 数组是否按照升序排列
     */
    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] < a[i - 1]) return false;
        return true;
    }
    /*
     * double[] 数组是否按照升序排列
     */
    public static boolean isSorted(double[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] < a[i - 1]) return false;
        return true;
    }
    /*
     * Comparable[] 数组是否按照升序排列
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(a[i - 1]) < 0) return false;
        return true;
    }
    /*
     * 二分查找 int[] 数组
     */
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
    /*
     * 打乱一个 int[] 数组
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
    /*
     * 打乱一个 Comparable[] 数组
     */
    public static void shuffle(Comparable[] a) {
        if (a == null)
            throw new NullPointerException();
        for (int i = 0; i < a.length; i++) {
            int r = i + StdRandom.uniform(a.length - i);
            Comparable t = a[i];
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
        int[] swapped = copy(sorted);
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
    /*
     * 解析形如 1, 2, 3, 4, 5, 6 7 8 9 10 11, 13 的字符串成 int[]
     */
    public static int[] parseInts(String s) {
        String[] sArr = s.split(",|\\s+");
        int[] arr = new int[sArr.length];
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(sArr[i]);
        return arr;
    }
    /*
     * 解析形如 1, 2, 3, 4, 5, 6 7 8 9 10 11, 13 的字符串成 Integer[]
     */
    public static Integer[] parseIntegers(String s) {
        return intToInteger(parseInts(s));
    }
    /*
     * 解析形如 1.0, 2.3, 3.2, 4.5, 5.7, 6.0 7.9 8.2 9.3 10.1 11.1, 13.2 的字符串成 double[]
     */
    public static double[] parsedoubles(String s) {
        String[] ss = s.split(",|\\s+");
        double[] c = new double[ss.length];
        for (int i = 0; i < c.length; i++)
            c[i] = Double.parseDouble(ss[i]);
        return c;
    }
    /*
     * 解析形如 1.0, 2.3, 3.2, 4.5, 5.7, 6.0 7.9 8.2 9.3 10.1 11.1, 13.2 的字符串成 Double[]
     */
    public static Double[] parseDoubles(String s) {
        return doubleToDouble(parsedoubles(s));
    }
    /*
     * 解析形如 a, b, d, c, e, f, g, h i j k l m n 的字符串成 char[]
     */
    public static char[] parseChar(String s) {
        String[] ss = s.split(",|\\s+");
        char[] c = new char[ss.length];
        for (int i = 0; i < c.length; i++)
            c[i] = ss[i].charAt(0);
        return c;
    }
    /*
     * 把 Integer[] 转成 int[]
     * 
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
     * 把 Object[] 转成 int[]
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
     * 把 int[] 转成 Integer[]
     */
    public static Integer[] intToInteger(int[] oarr) {
        if (oarr == null)
            throw new NullPointerException();
        int N = oarr.length;
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++)
            arr[i] = oarr[i];
        return arr;
    }
    /*
     * 把 Object[] 转成 long[]，必须要是被类型擦除的 Long[] 才行，否则抛出非法参数异常
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
     * 把 double[] 转成 Double[]
     */
    public static Double[] doubleToDouble(double[] darr) {
        if (darr == null)
            throw new NullPointerException();
        int N = darr.length;
        Double[] arr = new Double[N];
        for (int i = 0; i < N; i++)
            arr[i] = darr[i];
        return arr;
    }
    /*
     * 生成一个Integer[], 值为 [-size, size] 随机，尺寸为 size
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
     * 生成一个 Integer[] 取值 lo, lo+1, lo+2, lo+3 ... hi
     */
    public static Integer[] Integers(int lo, int hi) {
        Integer[] arr = ascendIntegers(lo, hi);
        shuffle(arr);
        return arr;
    }
    /*
     * 生成一个 Integer[] 取值 [lo, hi] 尺寸 size
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
    /*
     * 生成 Integer[] 取值 [-size, size] 按升序排列 
     */
    public static Integer[] ascendIntegers(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        Integer[] arr = Integers(size);
        Arrays.sort(arr);
        return arr;
    }
    /*
     * 生成 Integer[] 取值 lo, lo+1, lo+2 lo+3 ... hi 按升序排列
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
    /*
     * 生成 Integer[] 取值 [-size, size] 按降序排列
     */
    public static Integer[] descendIntegers(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        Integer[] arr = Integers(size);
        Arrays.sort(arr, Comparator.reverseOrder());
        return arr;
    }
    /*
     * 生成 Integer[] 取值 lo, lo+1, lo+2 ... hi 按降序排列
     */
    public static Integer[] descendIntegers(int hi, int lo) {
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot greater or equal than hi!");
        Integer[] arr = Integers(lo, hi);
        Arrays.sort(arr, Comparator.reverseOrder());
        return arr;
    }
    /*
     * 拷贝 Integer[] 为每个元素重新分配了内存
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
     * 产生一个 Integer[] 取值为 [0, size * 10] 无重复值
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
     * 产生一个 Integer[] 取值 [lo, hi] 无重复值 尺寸为 size
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
     * 产生一个 Integer[] 有 20% 的逆序对 取值范围 [lo, hi] 尺寸为 size
     */
    public static Integer[] IntegersPartialOrder(int size, int lo, int hi) {
        return IntegersPartialOrder(size, lo, hi, 0.2);
    }
    /*
     * 产生一个 Integer[] 有 scale 比例的逆序对 取值范围 [lo, hi] 尺寸为 size
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
     * 生成 int[] 取值范围 lo, lo+1, lo+2, lo+3 ... hi
     */
    public static int[] ints(int lo, int hi) {
        int[] arr = ascendInts(lo, hi);
        shuffle(arr);
        return arr;
    }
    /*
     * 生成 int[] 取值范围 [-size, size] 尺寸 size 随机排列
     */
    public static int[] ints(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        if (size == Integer.MAX_VALUE)
            throw new IllegalArgumentException("this size will result in overflow while generate random value");
        int[] d = new int[size];
        for (int i = 0; i < size; i++)
            d[i] = StdRandom.uniform(-size, size + 1);
        return d;
    }
    /*
     * 生成 int[] 取值范围 [lo, hi] 尺寸 size 随机排列
     */
    public static int[] ints(int size, int lo, int hi) {
        if (size < 0)
            throw new IllegalArgumentException("array size cannot be negative!");
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot be greater or equal than hi!");
        if (hi == Integer.MAX_VALUE)
            throw new IllegalArgumentException("this right bounds of " + hi + 
                    " will result in overflow while generating random value!");
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) 
            arr[i] = new Integer(StdRandom.uniform(lo, hi + 1));
        return arr;
    }
    /*
     * 生成一个 int[] 取值范围 [-size, size] 尺寸为  size 按升序排列
     */
    public static int[] ascendInts(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        int[] arr = ints(size);
        Arrays.sort(arr);
        return arr;
    }
    /*
     * 生成一个 int[] 取值范围 lo, lo+1, lo+2, lo+3 ... hi 按升序排列
     */
    public static int[] ascendInts(int lo, int hi) {
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot be greater or equal than hi!");
        int N = hi - lo + 1;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) 
            arr[i] = lo++;
        return arr;
    }
    /*
     * 生成一个 int[] 取值范围 [-size, size] 尺寸 size 按降序排列
     */
    public static int[] descendInts(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        Integer[] arr = Integers(size);
        Arrays.sort(arr, Comparator.reverseOrder());
        return IntegerToInt(arr);
    }
    /*
     * 生成一个 int[] 取值范围 hi, hi-1, hi-2. hi-3 ... lo 按降序排列
     */
    public static int[] descendInts(int hi, int lo) {
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot greater or equal than hi!");
        Integer[] arr = Integers(lo, hi);
        Arrays.sort(arr, Comparator.reverseOrder());
        return IntegerToInt(arr);
    }
    /*
     * 生成一个 int[] 值全部是 key
     */
    public static int[] allSameInts(int size, int key) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        int[] arr = new int[size];
        Arrays.fill(arr, key);
        return arr;
    }
    /*
     * 拷贝一个 int[]
     */
    public static int[] copy(int[] src) {
        if (src == null)
            throw new IllegalArgumentException("source array cannot be null!");
        int[] copy = new int[src.length];
        System.arraycopy(src, 0, copy, 0, src.length);
        return copy;
    }
    /*
     * 生成一个 int[] 取值 [0, size * 10] 尺寸为 size 
     */
    public static int[] intsNoDupli(int size) {
        if (size < 0)
            throw new IllegalArgumentException("array size cannot be negative!");
        if (size > (Integer.MAX_VALUE - 1) / 10)
            throw new IllegalArgumentException("size is too large to generate a no duplicated array");
        Set<Integer> set = new HashSet<Integer>();
        int[] arr = new int[size];
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
     * 生成一个 int[] 取值为 [lo, hi] 尺寸为 size
     */
    public static int[] intsNoDupli(int size, int lo, int hi) {
        if (size < 0)
            throw new IllegalArgumentException("array size cannot be negative!");
        if (size > (hi - lo + 1))
            throw new IllegalArgumentException(String.format("N cannot be greater than %d", hi - lo + 1));
        Set<Integer> set = new HashSet<Integer>();
        int[] arr = new int[size];
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
     * 生成一个 int[] 取值是调用者指定的不定个数的几个值, 每个值的数目随机，总尺寸为 size
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
     * 生成一个 int[] 取值为调用者指定的不定个数的几个值，每个值的数目为 eachAmount
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
     * 生成一个 Double[] 取值为 [lo, hi] 尺寸为 size
     */
    public static Double[] Doubles(int size, double lo, double hi) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        if (lo >= hi)
            throw new IllegalArgumentException("lo cannot greater or equal than hi");
        Double[] d = new Double[size];
        for (int i = 0; i < size; i++)
            d[i] = StdRandom.uniform(lo, hi);
        return d;
    }
    /*
     * 生成一个 Double[] 取值为 [0, 1] 尺寸为 size
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
     * 拷贝一个 Double[] 为每个元素重新分配内存
     */
    public static Double[] copy(Double[] src) {
        if (src == null)
            throw new IllegalArgumentException("source array cannot be null!");
        Double[] copy = new Double[src.length];
        for (int i = 0; i < src.length; i++) 
            copy[i] = src[i].doubleValue();
        return copy;
    }
    /*
     * 生成一个 double[] 取值为 [0, 1] 尺寸为 size
     */
    public static double[] doubles(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("array size cannot be negative or zero!");
        double[] d = new double[size];
        for (int i = 0; i < size; i++)
            d[i] = StdRandom.uniform();
        return d;
    }
    /*
     * 拷贝一个double[]
     */
    public static double[] copy(double[] src) {
        if (src == null)
            throw new IllegalArgumentException("source array cannot be null!");
        double[] copy = new double[src.length];
        System.arraycopy(src, 0, copy, 0, src.length);
        return copy;
    }
    /*
     * 带索引打印一个 Comparable[]
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
    /*
     * 带索引打印一个 Double[]
     */
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
     * 带索引打印一个 double[]
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
     * 带索引打印一个 int[]
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
    /*
     * 带索引打印一个 char[]
     */
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
        Integer[] s = descendIntegers(10);
        print(s);
    }
}
