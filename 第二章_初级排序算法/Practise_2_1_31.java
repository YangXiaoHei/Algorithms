package 第二章_初级排序算法;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_1_31 {
    public static double insertion(int[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length;
        for (int i = 1; i < N; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= 0 && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
        return timer.elapsedTime();
    }
    public static double selection(int[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++)
                if (a[j] < a[min]) min = j;
            int t = a[min];
            a[min] = a[i];
            a[i] = t;
        }
        return timer.elapsedTime();
    }
    public static double shell(int[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                int t = a[i], j;
                for (j = i - h; j >= 0 && t < a[j]; j -= h) 
                    a[j + h] = a[j];
                a[j + h] = t;
            }
            h /= 3;
        }
        return timer.elapsedTime();
    }
    public static void timeTrial(int T, int N, String algoName) {
        double pre = 0, cur = 0, preRatio = 0, avrg = 0;
        for (int i = N, j = 0; j < T; j++, i += i) {
            int[] a = intRandom_size_bounds(i, 0, i * 10);
            avrg = 0;
            for (int k = 0; k < 3; k++) {
                if (algoName.equals("insertion"))
                    avrg += insertion(a);
                if (algoName.equals("selection"))
                    avrg += selection(a);
                if (algoName.equals("shell"))
                    avrg += shell(a);
            }
            cur = avrg / 3;
            StdOut.printf("规模 : %d\t估计用时 : %.3f\t实际排序用时 : %.3f\t倍率 : %.1f\n",
                    i, pre * preRatio, cur, cur / pre);
            preRatio = cur / pre;
            pre = cur;
        }
    }
    public static void main(String[] args) {
//        timeTrial(13, 1000, "selection");
        timeTrial(10, 1000, "insertion");
//        timeTrial(15, 1000, "shell");
    }
    // output
    /*
     *  选择排序
     * 
     *  规模 : 1000    估计用时 : 0.000    实际排序用时 : 0.003  倍率 : Infinity
        规模 : 2000   估计用时 : Infinity 实际排序用时 : 0.001  倍率 : 0.5
        规模 : 4000   估计用时 : 0.001    实际排序用时 : 0.005  倍率 : 3.8
        规模 : 8000   估计用时 : 0.019    实际排序用时 : 0.019  倍率 : 3.7
        规模 : 16000  估计用时 : 0.070    实际排序用时 : 0.080  倍率 : 4.3
        规模 : 32000  估计用时 : 0.340    实际排序用时 : 0.295  倍率 : 3.7
        规模 : 64000  估计用时 : 1.090    实际排序用时 : 1.146  倍率 : 3.9
        规模 : 128000 估计用时 : 4.454    实际排序用时 : 4.663  倍率 : 4.1
        规模 : 256000 估计用时 : 18.976   实际排序用时 : 18.671 倍率 : 4.0
        
        插入排序
        
        规模 : 1000   估计用时 : 0.000    实际排序用时 : 0.002  倍率 : Infinity
        规模 : 2000   估计用时 : Infinity 实际排序用时 : 0.005  倍率 : 2.8
        规模 : 4000   估计用时 : 0.013    实际排序用时 : 0.001  倍率 : 0.2
        规模 : 8000   估计用时 : 0.000    实际排序用时 : 0.004  倍率 : 4.0
        规模 : 16000  估计用时 : 0.016    实际排序用时 : 0.015  倍率 : 3.8
        规模 : 32000  估计用时 : 0.059    实际排序用时 : 0.066  倍率 : 4.3
        规模 : 64000  估计用时 : 0.287    实际排序用时 : 0.263  倍率 : 4.0
        规模 : 128000 估计用时 : 1.040    实际排序用时 : 0.998  倍率 : 3.8
        规模 : 256000 估计用时 : 3.792    实际排序用时 : 3.321  倍率 : 3.3
        规模 : 512000 估计用时 : 11.053   实际排序用时 : 9.778  倍率 : 2.9
        
        
        希尔排序
        
        规模 : 1000   估计用时 : 0.000    实际排序用时 : 0.000  倍率 : Infinity
        规模 : 2000   估计用时 : Infinity 实际排序用时 : 0.001  倍率 : 2.0
        规模 : 4000   估计用时 : 0.001    实际排序用时 : 0.000  倍率 : 0.5
        规模 : 8000   估计用时 : 0.000    实际排序用时 : 0.001  倍率 : 4.0
        规模 : 16000  估计用时 : 0.005    实际排序用时 : 0.001  倍率 : 1.0
        规模 : 32000  估计用时 : 0.001    实际排序用时 : 0.002  倍率 : 1.3
        规模 : 64000  估计用时 : 0.002    实际排序用时 : 0.004  倍率 : 2.6
        规模 : 128000 估计用时 : 0.011    实际排序用时 : 0.010  倍率 : 2.2
        规模 : 256000 估计用时 : 0.022    实际排序用时 : 0.020  倍率 : 2.1
        规模 : 512000 估计用时 : 0.041    实际排序用时 : 0.046  倍率 : 2.3
        规模 : 1024000    估计用时 : 0.104    实际排序用时 : 0.106  倍率 : 2.3
        规模 : 2048000    估计用时 : 0.248    实际排序用时 : 0.235  倍率 : 2.2
        规模 : 4096000    估计用时 : 0.521    实际排序用时 : 0.519  倍率 : 2.2
        规模 : 8192000    估计用时 : 1.143    实际排序用时 : 1.093  倍率 : 2.1
        规模 : 16384000   估计用时 : 2.302    实际排序用时 : 2.395  倍率 : 2.2
        
     */
}
