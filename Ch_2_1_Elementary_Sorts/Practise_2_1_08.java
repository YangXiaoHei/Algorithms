package Ch_2_1_Elementary_Sorts;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.*;

/*
 * 思路 :
 * 
 * 根据倍率实验，可以看出仍然是平方级别的增长率
 * 
 */
public class Practise_2_1_08 {
    public static void insertion(int[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) 
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                int t = a[j];
                a[j] = a[j - 1];
                a[j - 1] = t;
            }
    }
    public static void timeTrial(int N, int T) {
        double pre = 0, cur = 0;
        for (int i = N, j = 0; j < T; i += i, j++) {
            Stopwatch timer = new Stopwatch();
            insertion(intsVrg(i, 3, 2, 1));
            cur = timer.elapsedTime();
            StdOut.printf("规模为 %d 耗时 %.3f  倍率 : %.3f\n", i, cur, cur / pre);
            pre = cur;
        }
    }
    public static void main(String[] args) {
        timeTrial(100, 13);
    }
    // output
    /*
     *  规模为 100 耗时 0.004  倍率 : Infinity
        规模为 200 耗时 0.000  倍率 : 0.000
        规模为 400 耗时 0.002  倍率 : Infinity
        规模为 800 耗时 0.003  倍率 : 1.500
        规模为 1600 耗时 0.002  倍率 : 0.667
        规模为 3200 耗时 0.013  倍率 : 6.500
        规模为 6400 耗时 0.008  倍率 : 0.615
        规模为 12800 耗时 0.033  倍率 : 4.125
        规模为 25600 耗时 0.136  倍率 : 4.121
        规模为 51200 耗时 0.510  倍率 : 3.750
        规模为 102400 耗时 2.102  倍率 : 4.122
        规模为 204800 耗时 8.317  倍率 : 3.957
        规模为 409600 耗时 33.858  倍率 : 4.071
     * 
     */
    
}
