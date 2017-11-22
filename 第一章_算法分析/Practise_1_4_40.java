package 第一章_算法分析;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_40 {
    /*
     * 思路 :
     * 
     * 结果为 N^3/16M
     * 
     * 
     */
    static class ThreeSum {
        static int cursor1 = 0;
        static int cursor2 = 0;
        static int fastCount(int[] a) {
            cursor1++;
            Arrays.sort(a);
            int cnt = 0;
            for (int i = 0; i < a.length; i++) {
                int lo = i + 1, hi = a.length - 1;
                while (lo < hi) {
                    if      (a[lo] + a[hi] > -a[i]) hi--;
                    else if (a[lo] + a[hi] < -a[i]) lo++;
                    else    {  cnt++; lo++; hi--; } 
                }
            }
            return cnt;
        }
        static int slowCount(int[] a) {
            cursor2++;
            int N = a.length;
            int cnt = 0;
            for (int i = 0; i < N; i++)
                for (int j = i + 1; j < N; j++)
                    for (int k = j + 1; k < N; k++)
                        if (a[i] + a[j] + a[k] == 0) 
                            cnt++;
            return cnt;
        }
    }
    static int[] sourceArr(int N) {
        Set<Integer> set = new HashSet<Integer>();
        int[] arr = new int[N];
        int size = 0, r = 0;
        while (size < N) {
            while (set.contains(r = StdRandom.uniform(-100000, 100000)));
            arr[size++] = r;
            set.add(r);
        }
        return arr;
    }
	public static void main(String[] args) {
	    int N = 1000;
		int[] arr = sourceArr(N);
		StdOut.println("理论值 : " + Math.pow(N, 3) / (16 * 100000) + "  实验值 : " + ThreeSum.fastCount(arr));
	}
	// output
	/*
	 * [-7, 92, 20, 68, -16, 41, -97, 16, -23, -48, 0, -42, -25, 10, 7, -65, -87, -5, 21, -82, -72, -89, 52, 34, -59, -90, 98, 63, -32, -77, -53, 59, -4, -55, -74, 95, -21, -57, -71, -47, -62, 9, 15, -69, 74, -28, -22, -45, -11, -6]
        66

	 */
}
