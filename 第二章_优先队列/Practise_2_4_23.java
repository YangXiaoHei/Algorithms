package 第二章_优先队列;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_4_23 {
    /*
     *  若某个子节点索引为i，则它的父节点的索引为（i-2）/d+1，向下取整。
        若某个父节点索引为i，则它的第j个子节点的索引为d*(i-1)+j+1。
     */
    /*
     * 上浮法建立d叉堆
     */
    public static void createHeapBySwim(int[] a, int d) {
        int[] b = new int[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            b[i + 1] = a[i];
            int k = i + 1, pre;
            while (k > 1 && b[pre = (k - 2) / d + 1] < b[k]) {
                int t = b[k]; b[k] = b[pre]; b[pre] = t;
                k = pre;
            }
        }
        System.arraycopy(b, 1, a, 0, a.length);
    }
    /*
     * 下沉法建立d叉堆
     * 
     * 优化了索引计算，比先前提交的快了几十倍...
     */
    public static void createHeapBySink(int[] a, int d) {
        int N = a.length;
        for (int i = (N - 2) / d + 1; i > 0; i--) {
            int k = i, p, count = d, maxIndex, max;
            while ((p = d * (k - 1) + 2) <= N) {
                maxIndex = p - 1; max = a[p - 1]; count = d;
                while (--count > 0) {
                    if (++p > N) break;
                    if (a[p - 1] > max) {
                        max = a[p - 1];
                        maxIndex = p - 1;
                    }
                }
                if (a[k - 1] >= a[maxIndex]) break;
                int t = a[k - 1]; a[k - 1] = a[maxIndex]; a[maxIndex] = t;
                k = maxIndex + 1;
            }
        }
    }
    /*
     * 下沉法建立二叉堆
     */
    public static void createBinaryHeapBySink(int[] a) {
        int N = a.length;
        for (int i = N >> 1; i > 0; i--) {
            int k = i;
            while ((k << 1) <= N) {
                int j = k << 1;
                if (j < N && a[j - 1] < a[j]) j++;
                if (a[k - 1] >= a[j - 1]) break;
                int t = a[k - 1]; a[k - 1] = a[j - 1]; a[j - 1] = t;
                k = j;
            }
        }
    }
    /*
     * d叉堆的下沉操作
     */
    public static void sink(int[] a, int i, int size, int d) {
        int p, count, max, maxIndex, k = i;
        while ((p = d * (k - 1) + 2) <= size) {
            max = a[p - 1]; maxIndex = p - 1; count = d;
            while (--count > 0) {
                if (++p > size) break;
                if (a[p - 1] > max) {
                    max = a[p - 1];
                    maxIndex = p - 1;
                }
            }
            if (a[k - 1] >= a[maxIndex]) break;
            int t = a[k - 1]; a[k - 1] = a[maxIndex]; a[maxIndex] = t;
            k = maxIndex + 1;
        }
    }
    /*
     * d叉堆排序
     */
    public static double multiwayHeapSort(int[] a, int d) {
        Stopwatch timer = new Stopwatch();
        // createHeapBySink(a, d);
        int N = a.length;
        for (int i = (N - 2) / d + 1; i > 0; i--) {
            int k = i, p, count = d, maxIndex, max;
            while ((p = d * (k - 1) + 2) <= N) {
                maxIndex = p - 1; max = a[p - 1]; count = d;
                while (--count > 0) {
                    if (++p > N) break;
                    if (a[p - 1] > max) {
                        max = a[p - 1];
                        maxIndex = p - 1;
                    }
                }
                if (a[k - 1] >= a[maxIndex]) break;
                int t = a[k - 1]; a[k - 1] = a[maxIndex]; a[maxIndex] = t;
                k = maxIndex + 1;
            }
        }
        while (N > 1) {
            // exch(a, 1, N)
            int t = a[0]; a[0] = a[N - 1]; a[N - 1] = t;
            --N;
            // sink(a, 1, --N, d)
            int p, count, max, maxIndex, k = 1;
            while ((p = d * (k - 1) + 2) <= N) {
                max = a[p - 1]; maxIndex = p - 1; count = d;
                while (--count > 0) {
                    if (++p > N) break;
                    if (a[p - 1] > max) {
                        max = a[p - 1];
                        maxIndex = p - 1;
                    }
                }
                if (a[k - 1] >= a[maxIndex]) break;
                t = a[k - 1]; a[k - 1] = a[maxIndex]; a[maxIndex] = t;
                k = maxIndex + 1;
            }
        }
        return timer.elapsedTime();
    }
    /*
     * 在 d叉堆 中找出排序性能最好的分支数
     */
    public static void test1() {
        int N = 10000000;
        int[] a = ints(0, N - 1);
        int[] copy = intsCopy(a);
        StdOut.printf("规模 : %d 教材的二叉堆排序耗时 : %.3f\n",
                N, Text_HeapSort.heap(copy));
        assert isSorted(copy);
        for (int i = 2, j = 0; j < 17; i ++, j++) {
            copy = intsCopy(a);
            StdOut.printf("规模 : %d 复杂索引计算的【%d叉堆】排序耗时 : %.3f\n",
                    N, i, multiwayHeapSort(copy,  i));
            assert isSorted(copy);
        }
    }
    /*
     * 对于教材的二叉堆建立和 d叉堆令 d = 2 的二叉堆建立的性能
     */
    public static void test2() {
        int[] a = ints(0, 10000000);
        int[] copy = intsCopy(a);
        Stopwatch timer = new Stopwatch();
        createHeapBySink(a, 2);
        double t1 = timer.elapsedTime();
        timer = new Stopwatch();
        createBinaryHeapBySink(copy);
        double t2 = timer.elapsedTime();
        StdOut.printf("方法 1 : %.3f\n", t1);
        StdOut.printf("方法 2 : %.3f\n", t2);
        assert equal(a, copy);
    }
    public static void main(String[] args) {
        test1();
    }
    // output
    /*
     *  规模 : 200000 2叉树排序耗时 : 35.666
        规模 : 200000 4叉树排序耗时 : 11.861
        规模 : 200000 8叉树排序耗时 : 4.877
        规模 : 200000 16叉树排序耗时 : 2.034
        规模 : 200000 32叉树排序耗时 : 0.990
        规模 : 200000 64叉树排序耗时 : 0.503
        规模 : 200000 128叉树排序耗时 : 0.316
        规模 : 200000 256叉树排序耗时 : 0.248
        规模 : 200000 512叉树排序耗时 : 0.279
        规模 : 200000 1024叉树排序耗时 : 0.443
        规模 : 200000 2048叉树排序耗时 : 0.823
        规模 : 200000 4096叉树排序耗时 : 1.581
        规模 : 200000 8192叉树排序耗时 : 2.999
     */
}
