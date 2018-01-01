package 第二章_应用;

import static 第二章_优先队列.Text_Alphabet.*;
import java.util.Arrays;
import edu.princeton.cs.algs4.*;

public class Practise_2_5_04 {
    public static void merge(String[] a) {
        String[] aux = a.clone();
        merge(aux, a, 0, a.length - 1);
    }
    private static void merge(String[] src, String[] dst, int lo, int hi) {
        if (hi - lo + 1 < 8) {
            for (int i = lo; i <= hi; i++) {
                String s = dst[i]; int j;
                for (j = i - 1; j >= lo && s.compareTo(dst[j]) < 0; j--)
                    dst[j + 1] = dst[j];
                dst[j + 1] = s;
            }
            return;
        }
        int mid = (lo + hi) >> 1;
        merge(dst, src, lo, mid);
        merge(dst, src, mid + 1, hi);
        if (src[mid].compareTo(src[mid + 1]) <= 0) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        mergeSort(src, dst, lo, mid, hi);
    }
    private static void mergeSort(String[] src, String[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) 
            if      (i > mid)                       dst[k] = src[j++];
            else if (j > hi)                        dst[k] = src[i++];
            else if (src[j].compareTo(src[i]) < 0)  dst[k] = src[j++];
            else                                    dst[k] = src[i++];
    }
    public static String[] dedup(String[] a) {
        String[] copy = a.clone();
        merge(copy);
       int j = 0, i = 1;
       while (i < copy.length) {
           if (copy[i].compareTo(copy[j]) != 0) 
               copy[++j] = copy[i++];
           else
               i++;
       }
       String[] nodup = new String[j + 1];
       for (int k = 0; k < j + 1; k++)
           nodup[k] = copy[k];
       return nodup;
    }
    public static void main(String[] args) {
        String[] s = random(100);
        StdOut.println(Arrays.toString(s));
        StdOut.println(Arrays.toString(dedup(s)));
    }
    // output
    /*
     *  [M, G, P, E, W, J, M, Q, G, G, M, E, H, U, R, V, B, W, B, A, X, U, Y, N, F, Y, H, V, O, X, V, K, W, Z, Q, N, B, C, B, E, M, W, L, T, T, U, E, E, M, E, V, Q, D, M, O, H, M, M, U, X, M, G, E, R, U, T, W, L, W, D, I, E, B, N, G, T, K, Y, J, O, W, L, B, K, N, E, Z, N, V, I, A, V, W, O, J, M, X, G, V, O]
        [A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, T, U, V, W, X, Y, Z]

     */
}
