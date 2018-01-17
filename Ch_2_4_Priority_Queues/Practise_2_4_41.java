package Ch_2_4_Priority_Queues;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_4_41 {
    /*
     * 多叉堆排序
     */
    public static int multiwayHeap(int[] a, int d) {
        compares = 0;
        int N = a.length;
        for (int i = (N - 2) / d + 1; i > 0; i--) {
            int k = i, p, maxIndex, max, count = d;
            while ((p = (d * (k - 1) + 2)) <= N) {
                maxIndex = p - 1; max = a[p - 1]; count = d;
                while (count-- > 0) {
                    if (++p > N) break;
                    if (grea(a[p - 1], max)) {
                        max = a[p - 1];
                        maxIndex = p - 1;
                    }
                }
                if (!less(a[k - 1], max)) break;
                int t = a[k - 1];
                a[k - 1] = a[maxIndex];
                a[maxIndex] = t;
                k = maxIndex + 1;
            }
        }
        while (N > 1) {
            int t = a[0];
            a[0] = a[N - 1];
            a[N - 1] = t;
            --N;
            int k = 1, p, count = d, maxIndex, max;
            while ((p = d * (k - 1) + 2) <= N) {
                maxIndex = p - 1; max = a[p - 1]; count = d;
                while (count-- > 0) {
                    if (++p > N) break;
                    if (grea(a[p - 1], max)) {
                        max = a[p - 1];
                        maxIndex = p - 1;
                    }
                }
                if (!less(a[k - 1], max)) break;
                t = a[k - 1]; a[k - 1] = a[maxIndex]; a[maxIndex] = t;
                k = maxIndex + 1;
            }
        }
        return compares;
    }
    public static int standard(int[] a) {
        compares = 0;
        int N = a.length;
        for (int i = N >> 1; i > 0; i--) {
            int k = i, tmp = a[i - 1], j;
            while ((j = k << 1) <= N) {
                if (j < N && a[j - 1] < a[j]) j++;
                if (!less(tmp, a[j - 1])) break;
                a[k - 1] = a[j - 1];
                k = j;
            }
            a[k - 1] = tmp;
        }
        while (N > 1) {
            int t = a[0];
            a[0] = a[N - 1];
            a[N - 1] = t;
            --N;
            int k = 1, tmp = a[0], j;
            while ((j = k << 1) <= N) {
                if (j < N && a[j - 1] < a[j]) j++;
                if (!less(tmp, a[j - 1])) break;
                a[k - 1] = a[j - 1];
                k = j;
            }
            a[k - 1] = tmp;
        }
        return compares;
    }
    private static int compares;
    public static boolean less(int i, int j) { 
        compares++;
        return i < j;
    }
    public static boolean grea(int i, int j) {
        compares++;
        return i > j;
    }
    public static void main(String[] args) {
        int N = 1000000;
        int[] a = ints(0, N - 1);
        int[] copy = intsCopy(a);
        int[] copy1 = intsCopy(a);
        
        StdOut.printf("二叉堆比较次数 : %d\n", standard(a));
        StdOut.printf("三叉堆比较次数 : %d\n", multiwayHeap(copy1, 3));
        StdOut.printf("四叉堆比较次数 : %d\n", multiwayHeap(copy, 4));
        
        assert isSorted(a);
        assert isSorted(copy);
        assert isSorted(copy1);
    }
    // output
    /*
     *  N = 1000
     * 
     *  二叉堆比较次数 : 8434
        三叉堆比较次数 : 21769
        四叉堆比较次数 : 21948
        
        N = 1000000
        
        二叉堆比较次数 : 18397706
        三叉堆比较次数 : 46910379
        四叉堆比较次数 : 46854898


     */
}
