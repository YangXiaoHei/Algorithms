package Ch_2_1_Elementary_Sorts;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_1_27 {
    public static void shell(int[] a) {
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                int t = a[i], j;
                for (j = i - h; j >= 0 && a[j] > t; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
            h /= 3;
        }
    }
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
        for (int i = N, j = 0; j < T; j++, i++) {
            avrg = 0;
            for (int k = 0; k < loops; k++) {
                int[] arr = ints((int)Math.pow(2, i), 0, i * 10);
                timer = new Stopwatch();
                
//                selection(arr);
                
//                insertion(arr);
                
                shell(arr);
                
                avrg += timer.elapsedTime();
            }
            cur = avrg / loops;
            StdOut.printf("规模 : %d 耗时 : %.3f 倍率->b : %.1f\n", (int)Math.pow(2, i), cur, Math.log(cur / pre) / Math.log(2.0));
            pre = cur;
        }
    }
    public static void main(String[] args) {
        timerTrial(20, 7, 3);
    }
    // output
    /*
     *  选择排序 
     * 
     *  规模 : 128 耗时 : 0.000 倍率->b : Infinity
        规模 : 256 耗时 : 0.001 倍率->b : 1.0
        规模 : 512 耗时 : 0.001 倍率->b : 0.6
        规模 : 1024 耗时 : 0.002 倍率->b : 0.7
        规模 : 2048 耗时 : 0.002 倍率->b : 0.5
        规模 : 4096 耗时 : 0.009 倍率->b : 2.0
        规模 : 8192 耗时 : 0.035 倍率->b : 1.9
        规模 : 16384 耗时 : 0.150 倍率->b : 2.1
        规模 : 32768 耗时 : 0.564 倍率->b : 1.9
        规模 : 65536 耗时 : 2.219 倍率->b : 2.0
        规模 : 131072 耗时 : 9.032 倍率->b : 2.0
        
        插入排序
        
        规模 : 128 耗时 : 0.000 倍率->b : Infinity
        规模 : 256 耗时 : 0.000 倍率->b : 0.0
        规模 : 512 耗时 : 0.000 倍率->b : 0.0
        规模 : 1024 耗时 : 0.001 倍率->b : 1.0
        规模 : 2048 耗时 : 0.001 倍率->b : 1.0
        规模 : 4096 耗时 : 0.002 倍率->b : 0.6
        规模 : 8192 耗时 : 0.007 倍率->b : 1.8
        规模 : 16384 耗时 : 0.029 倍率->b : 2.1
        规模 : 32768 耗时 : 0.122 倍率->b : 2.1
        规模 : 65536 耗时 : 0.451 倍率->b : 1.9
        规模 : 131072 耗时 : 1.822 倍率->b : 2.0
        规模 : 262144 耗时 : 7.687 倍率->b : 2.1
        规模 : 524288 耗时 : 31.273 倍率->b : 2.0
        
        希尔排序
        
        规模 : 128 耗时 : 0.000 倍率->b : NaN
        规模 : 256 耗时 : 0.000 倍率->b : NaN
        规模 : 512 耗时 : 0.000 倍率->b : Infinity
        规模 : 1024 耗时 : 0.001 倍率->b : 1.0
        规模 : 2048 耗时 : 0.000 倍率->b : -Infinity
        规模 : 4096 耗时 : 0.001 倍率->b : Infinity
        规模 : 8192 耗时 : 0.001 倍率->b : -0.6
        规模 : 16384 耗时 : 0.001 倍率->b : 0.6
        规模 : 32768 耗时 : 0.002 倍率->b : 1.0
        规模 : 65536 耗时 : 0.004 倍率->b : 1.1
        规模 : 131072 耗时 : 0.010 倍率->b : 1.2
        规模 : 262144 耗时 : 0.039 倍率->b : 2.0
        规模 : 524288 耗时 : 0.070 倍率->b : 0.8
        规模 : 1048576 耗时 : 0.120 倍率->b : 0.8
        规模 : 2097152 耗时 : 0.195 倍率->b : 0.7
        规模 : 4194304 耗时 : 0.398 倍率->b : 1.0
        规模 : 8388608 耗时 : 0.836 倍率->b : 1.1
        规模 : 16777216 耗时 : 1.766 倍率->b : 1.1
        规模 : 33554432 耗时 : 3.722 倍率->b : 1.1
        
        
     * 
     * 
     * 
     */
}
