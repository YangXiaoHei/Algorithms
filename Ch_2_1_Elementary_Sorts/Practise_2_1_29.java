package Ch_2_1_Elementary_Sorts;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_1_29 {
    public static int[] incs_A = { 4188161, 2354689, 1045055, 587521, 260609, 146305, 64769, 36289, 16001, 8929, 3905, 2161, 929, 505, 209, 109, 41, 19, 5, 1};
    public static int[] incs_B = { 1743392200, 581130733, 193710244, 64570081, 21523360, 7174453, 2391484, 797161, 265720, 88573, 29524, 9841, 3280, 1093, 364, 121, 40, 13, 4, 1};
    public static double shell_A(int[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length;
        for (int k = 0; k < incs_A.length; k++) 
            for (int h = incs_A[k], i = h; i < N; i++) {
                int t = a[i], j;
                for (j = i - h; j >= 0 && t < a[j]; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
        return timer.elapsedTime();
    }
    public static double shell_B(int[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length;
        for (int k = 0; k < incs_B.length; k++) 
            for (int h = incs_B[k], i = h; i < N; i++) {
                int t = a[i], j;
                for (j = i - h; j >= 0 && t < a[j]; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
        return timer.elapsedTime();
    }
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int[] arr = ints(16000000, 1, 100000000);
            int[] copy = intsCopy(arr);
            StdOut.printf("序列 A : %.3f\n", shell_A(arr));
            StdOut.printf("序列 B : %.3f\n", shell_B(copy));
            StdOut.println();
        }
    }
    // output
    /*
     *  序列 A : 3.036
        序列 B : 4.846
        
        序列 A : 2.994
        序列 B : 4.709
        
        序列 A : 2.976
        序列 B : 4.685
        
        序列 A : 3.012
        序列 B : 4.636
        
        序列 A : 2.995
        序列 B : 4.610
     */
}
