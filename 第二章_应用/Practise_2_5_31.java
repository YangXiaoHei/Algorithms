package 第二章_应用;

import static 第二章_初级排序算法.Text_Array.*;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_5_31 {
    public static int RB(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            hi = a[mid] >  key ? mid : hi;
            lo = a[mid] <= key ? mid + 1 : lo;
        }
        if (a[lo] == key) return lo;
        if (--lo < 0) return -1;
        return a[lo] == key ? lo : -1;
    }
    /*
     * 计算重复值的个数 
     */
    public static int countCommon(int[] a) {
        Arrays.sort(a);
        int cnt = 0, i = -1, right;
        while (++i < a.length) {
            if ((right = RB(a, a[i])) != i) {
                cnt++;
                i = right;
            } 
        }
        return cnt;
    }
    /*
     * 计算不重复值的个数
     */
    public static int countDistinct(int[] a) {
        Arrays.sort(a);
        int cnt = 0;
        for (int i = 1; i < a.length; i++)
            if (a[i - 1] != a[i])
                cnt++;
        return cnt;
    }
    public static void main(String[] args) {
        int M = 1000000, N = 234254, T = 10, cnt = 0;
        for (int i = 0; i < T; i++) 
            cnt += countDistinct(ints(N, 0, M - 1));
        cnt /= T;
        StdOut.printf("实验值 : %d  理论值 : %d\n", cnt, (int)(M * (1 - Math.exp(-N * 1.0 / M))));
    }
    // output
    /*
     * 实验值 : 208918  理论值 : 208839
     */
}
