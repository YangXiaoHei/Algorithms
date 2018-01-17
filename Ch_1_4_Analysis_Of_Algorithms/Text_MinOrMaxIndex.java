package Ch_1_4_Analysis_Of_Algorithms;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import edu.princeton.cs.algs4.*;

public class Text_MinOrMaxIndex {
    public static int maxRank_logN(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (a[mid] > key)
                hi = mid;
            else
                lo = mid + 1;
        }
        if (a[lo] == key) return lo;
        return --lo < 0 || a[lo] != key ? -1 : lo;
    }
    public static int maxRank_N(int[] a, int key) {
        int lo = 0, N = a.length, hi = N - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (a[mid] > key)   hi = mid - 1;
            else if (a[mid] < key) lo = mid + 1;
            else {
                while (lo < N && a[lo++] == key);
                return --lo;
            }
        }
        return -1;
    }
    public static void fastSearch_doublingRatioTest() {
        double pre = 0, cur = 0;
        for (int i = 1000, j = 0; j < 15; j++, i += i) {
            int[] a = allSameInts(i, 99);
            long start = System.nanoTime(); // 纳秒
            int index = maxRank_logN(a, 99);
            long end = System.nanoTime();
            StdOut.printf("规模 : %d "
                    + "耗时 : %.3f "
                    + "倍率 : %.0f "
                    + "找到的索引 : %d "
                    + "该索引处的值 : %d\n",
                    i, 
                    (cur = (end - start)), 
                    cur / pre,
                    index,
                    a[index]);
            pre = cur;
        }
    }
    public static void slowSearch_doublingRatioTest() {
        double pre = 0, cur = 0;
        for (int i = 1000, j = 0; j < 19; j++, i += i) {
            int[] a = allSameInts(i, 99);
            long start = System.nanoTime(); // 纳秒
            int index = maxRank_N(a, 99);
            long end = System.nanoTime();
            StdOut.printf("规模 : %d "
                    + "耗时 : %.3f "
                    + "倍率 : %.0f "
                    + "找到的索引 : %d "
                    + "该索引处的值 : %d\n",
                    i, 
                    (cur = (end - start)), 
                    cur / pre,
                    index,
                    a[index]);
            pre = cur;
        }
    }
    public static void main(String[] args) {
        slowSearch_doublingRatioTest();
    }
    // output
    /*
     *  快速二分查找返回最大索引
        规模 : 32000 耗时 : 2453.000 倍率 : 1 找到的索引 : 31999 该索引处的值 : 99
        规模 : 64000 耗时 : 2652.000 倍率 : 1 找到的索引 : 63999 该索引处的值 : 99
        规模 : 128000 耗时 : 3687.000 倍率 : 1 找到的索引 : 127999 该索引处的值 : 99
        规模 : 256000 耗时 : 5141.000 倍率 : 1 找到的索引 : 255999 该索引处的值 : 99
        规模 : 512000 耗时 : 6321.000 倍率 : 1 找到的索引 : 511999 该索引处的值 : 99
        规模 : 1024000 耗时 : 6396.000 倍率 : 1 找到的索引 : 1023999 该索引处的值 : 99
        规模 : 2048000 耗时 : 6033.000 倍率 : 1 找到的索引 : 2047999 该索引处的值 : 99
        规模 : 4096000 耗时 : 7615.000 倍率 : 1 找到的索引 : 4095999 该索引处的值 : 99
        规模 : 8192000 耗时 : 7716.000 倍率 : 1 找到的索引 : 8191999 该索引处的值 : 99
        规模 : 16384000 耗时 : 7439.000 倍率 : 1 找到的索引 : 16383999 该索引处的值 : 99
        
        最坏情况下退化的返回最大索引
        规模 : 32000 耗时 : 680504.000 倍率 : 2 找到的索引 : 31999 该索引处的值 : 99
        规模 : 64000 耗时 : 422498.000 倍率 : 1 找到的索引 : 63999 该索引处的值 : 99
        规模 : 128000 耗时 : 852015.000 倍率 : 2 找到的索引 : 127999 该索引处的值 : 99
        规模 : 256000 耗时 : 210010.000 倍率 : 2 找到的索引 : 255999 该索引处的值 : 99
        规模 : 512000 耗时 : 344039.000 倍率 : 2 找到的索引 : 511999 该索引处的值 : 99
        规模 : 1024000 耗时 : 687822.000 倍率 : 2 找到的索引 : 1023999 该索引处的值 : 99
        规模 : 2048000 耗时 : 987893.000 倍率 : 1 找到的索引 : 2047999 该索引处的值 : 99
        规模 : 4096000 耗时 : 3410076.000 倍率 : 3 找到的索引 : 4095999 该索引处的值 : 99
        规模 : 8192000 耗时 : 7203188.000 倍率 : 2 找到的索引 : 8191999 该索引处的值 : 99
        规模 : 16384000 耗时 : 17836289.000 倍率 : 2 找到的索引 : 16383999 该索引处的值 : 99
        规模 : 32768000 耗时 : 25721049.000 倍率 : 2 找到的索引 : 32767999 该索引处的值 : 99
        规模 : 65536000 耗时 : 41160195.000 倍率 : 2 找到的索引 : 65535999 该索引处的值 : 99
        规模 : 131072000 耗时 : 77256872.000 倍率 : 2 找到的索引 : 131071999 该索引处的值 : 99
        规模 : 262144000 耗时 : 187748089.000 倍率 : 2 找到的索引 : 262143999 该索引处的值 : 99
        
     */
}
