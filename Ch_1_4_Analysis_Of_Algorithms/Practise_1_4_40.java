package Ch_1_4_Analysis_Of_Algorithms;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_40 {
    /*
     * 思路 :
     * 
     * 
     * N 个数中挑 3 个的不同组合为 C(N, 3) ~ N^3/6
     * 从 [-M, M] 中选 3 个数共有 (2M + 1)^N 种方式
     * 从 [-M, M] 中选 3 个和为 0 的数共有 3M^2 + 3M + 1 种方式
     * 从 [-M, M] 中选 3 个和不为 0 的数共有 (2M + 1)^(N - 1) 种方式
     * 总共有 N^3/6 * (3M^2 + 3M + 1) * (2M + 1)^(N - 1)
     * 所以平均值为 (N^3/6 * (3M^2 + 3M + 1) * (2M + 1)^(N - 1)) / (2M + 1)^N
     * 
     * 结果为 N^3/16M
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
	    int N = 1000, loops = 100;
	    double rel = 0;
	    int i = loops;
	    while (i-- > 0)
	        rel += ThreeSum.fastCount(sourceArr(N));
	    rel /= loops * 1.0;
		StdOut.println("理论值 : " + Math.pow(N, 3) / (16 * 100000) + "  实验值 : " + rel);
	}
	// output
	/*
	 * 理论值 : 625.0  实验值 : 626.82

	 * 
	 */
}
