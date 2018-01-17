package Ch_2_1_Elementary_Sorts;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_1_28 {
    public static void insertion(int[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= 0 && a[j] > t; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }
    public static void selection(int[] a) {
        int N = a.length;
        int min = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++)
                if (a[j] < a[min]) min = j;
            int t = a[i];
            a[i] = a[min];
            a[min] = t;
        }
    }
    public static void timerTrial(int T, int N, int loops) {
        double pre = 0, cur = 0;
        Stopwatch timer = null;
        double avrg = 0;
        for (int i = N, j = 0; j < T; j++, i += i) {
            avrg = 0;
            for (int k = 0; k < loops; k++) {
                int[] arr = intsVrg(i, 1, 2);
                timer = new Stopwatch();
                
                selection(arr);
                
//                insertion(arr);
                
                avrg += timer.elapsedTime();
            }
            cur = avrg / loops;
            StdOut.printf("规模 : %d 耗时 : %.3f 倍率->b : %.1f\n", i, cur, Math.log(cur / pre) / Math.log(2.0));
            pre = cur;
        }
    }
    public static void main(String[] args) {
        timerTrial(15, 100, 3);
    }
    // output
    /*
     * 插入排序
     * 
     *  规模 : 100 耗时 : 0.000 倍率->b : NaN
        规模 : 200 耗时 : 0.000 倍率->b : Infinity
        规模 : 400 耗时 : 0.001 倍率->b : 1.0
        规模 : 800 耗时 : 0.000 倍率->b : -1.0
        规模 : 1600 耗时 : 0.002 倍率->b : 2.3
        规模 : 3200 耗时 : 0.002 倍率->b : 0.3
        规模 : 6400 耗时 : 0.002 倍率->b : 0.0
        规模 : 12800 耗时 : 0.009 倍率->b : 2.2
        规模 : 25600 耗时 : 0.051 倍率->b : 2.5
        规模 : 51200 耗时 : 0.155 倍率->b : 1.6
        规模 : 102400 耗时 : 0.562 倍率->b : 1.9
        规模 : 204800 耗时 : 2.367 倍率->b : 2.1
        规模 : 409600 耗时 : 9.597 倍率->b : 2.0
        
        选择排序
        
        规模 : 100 耗时 : 0.000 倍率->b : NaN
        规模 : 200 耗时 : 0.000 倍率->b : Infinity
        规模 : 400 耗时 : 0.000 倍率->b : -Infinity
        规模 : 800 耗时 : 0.001 倍率->b : Infinity
        规模 : 1600 耗时 : 0.001 倍率->b : 0.6
        规模 : 3200 耗时 : 0.002 倍率->b : 1.2
        规模 : 6400 耗时 : 0.010 倍率->b : 2.1
        规模 : 12800 耗时 : 0.050 倍率->b : 2.3
        规模 : 25600 耗时 : 0.184 倍率->b : 1.9
        规模 : 51200 耗时 : 0.700 倍率->b : 1.9
        规模 : 102400 耗时 : 2.781 倍率->b : 2.0
        规模 : 204800 耗时 : 11.551 倍率->b : 2.1
        规模 : 409600 耗时 : 45.430 倍率->b : 2.0
        
     * 
     * 
     * 
     */
}
