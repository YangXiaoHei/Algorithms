package 第二章_初级排序算法;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_1_35 {
    public static double shell(double[] a) {
        a = doublesCopy(a);
        Stopwatch timer = new Stopwatch();
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                double t = a[i];
                int j;
                for (j = i - h; j >= 0 && t < a[j]; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
            h /= 3;
        }
        return timer.elapsedTime();
    }
    public static void main(String[] args) {
        StdOut.printf("对照试验 : %f\n", shell(doubles(4000000)));
        StdOut.printf("高斯分布 : %f\n", shell(gaussian(4000000, 100)));
        StdOut.printf("泊松分布 : %f\n", shell(possion(4000000, 100)));
        StdOut.printf("几何分布 : %f\n", shell(geometric(4000000, 0.4)));
        StdOut.printf("离散分布 : %f\n", shell(discrete(4000000, 0.3, 0.3, 0.4)));
    }
    // output
    /*
     *  对照试验 : 1.256000
        高斯分布 : 1.234000
        泊松分布 : 0.381000
        几何分布 : 0.233000
        离散分布 : 0.191000

     */
}
