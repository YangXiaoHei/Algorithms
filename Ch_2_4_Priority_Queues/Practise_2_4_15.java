package Ch_2_4_Priority_Queues;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_4_15 {
    /*
     * 最大堆
     */
    public static <Key extends Comparable<Key>> boolean isMaxPQ(Key[] pq) {
        int N = pq.length;
        for (int k = N - 1; k > N >> 1; k--) {
            for (int i = k; i > 1; i >>= 1) {
                if (pq[i >> 1].compareTo(pq[i]) < 0) return false;
            }
        }
        return true;
    }
    /*
     * 最小堆
     */
    public static <Key extends Comparable<Key>> boolean isMinPQ(Key[] pq) {
        int N = pq.length;
        for (int k = N - 1; k > N >> 1; k--) {
            for (int i = k; i > 1; i >>= 1) {
                if (pq[i >> 1].compareTo(pq[i]) > 0) return false;
            }
        }
        return true;
    }
    public static Integer[] createMaxPQ(int N) {
        Integer[] b = Integers(0, N - 1);
        Integer[] a = new Integer[N + 1];
        System.arraycopy(b, 0, a, 1, N);
        for (int i = N >> 1; i > 0; i--) {
            int k = i;
            while ((k << 1) <= N) {
                int j = k << 1;
                if (j < N && a[j].compareTo(a[j + 1]) < 0) j++;
                if (a[k].compareTo(a[j]) >= 0) break;
                Integer t = a[k]; a[k] = a[j]; a[j] = t;
                k = j;
            }
        }
        return a;
    }
    public static Integer[] createMinPQ(int N) {
        Integer[] b = Integers(0, N - 1);
        Integer[] a = new Integer[N + 1];
        System.arraycopy(b, 0, a, 1, N);
        for (int i = N >> 1; i > 0; i--) {
            int k = i;
            while ((k << 1) <= N) {
                int j = k << 1;
                if (j < N && a[j].compareTo(a[j + 1]) > 0) j++;
                if (a[k].compareTo(a[j]) <= 0) break;
                Integer t = a[k]; a[k] = a[j]; a[j] = t;
                k = j;
            }
        }
        return a;
    }
    public static void main(String[] args) {
        Integer[] maxPQ = createMaxPQ(15);
        StdOut.println(Arrays.toString(maxPQ));
        StdOut.println("是否是最大堆 : " + isMaxPQ(maxPQ) + "\n");
        
        Integer[] minPQ = createMinPQ(15);
        StdOut.println(Arrays.toString(minPQ));
        StdOut.println("是否是最小堆 : " + isMinPQ(minPQ) + "\n");
    }
    // output
    /*
     *  [null, 14, 12, 13, 11, 2, 9, 10, 3, 8, 1, 0, 6, 7, 4, 5]
        是否是最大堆 : true
        
        [null, 0, 1, 2, 6, 11, 5, 3, 13, 10, 14, 12, 7, 9, 4, 8]
        是否是最小堆 : true


     */
}
