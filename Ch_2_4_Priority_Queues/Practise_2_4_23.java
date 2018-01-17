package Ch_2_4_Priority_Queues;

import static Ch_2_1_Elementary_Sorts.__Array.*;

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
//         createHeapBySink(a, d);
        // createHeapBySwim(a, d);
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
//          sink(a, 1, --N, d);
            --N;
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
                N, __HeapSort.heap(copy));
        assert isSorted(copy);
        for (int i = 2; i < 20; i++) {
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
    /*
     * 6叉堆排序和二叉堆排序比较
     * 
     *  五叉堆排序耗时 ：2.497
        六叉堆排序耗时 ：3.203
        七叉堆排序耗时 ：3.295
        二叉堆排序耗时 ：2.705

        五叉堆排序耗时 ：2.414
        六叉堆排序耗时 ：2.267
        七叉堆排序耗时 ：3.206
        二叉堆排序耗时 ：2.681

     */
    public static void test3() {
        int[] a = ints(0, 10000000);
        int[] copy = intsCopy(a);
        int[] copy1 = intsCopy(a);
        int[] copy2 = intsCopy(a);
        StdOut.printf("五叉堆排序耗时 ：%.3f\n", multiwayHeapSort(a, 5));
        StdOut.printf("六叉堆排序耗时 ：%.3f\n", multiwayHeapSort(copy1, 6));
        StdOut.printf("七叉堆排序耗时 ：%.3f\n", multiwayHeapSort(copy2, 7));
        StdOut.printf("二叉堆排序耗时 ：%.3f\n", __HeapSort.heap(copy));
        assert isSorted(a);
        assert isSorted(copy);
        assert isSorted(copy1);
        assert isSorted(copy2);
    }
    public static void main(String[] args) {
        test3();
    }
    // output
    /*
     *  规模 : 10000000 教材的二叉堆排序耗时 : 3.335
        规模 : 10000000 复杂索引计算的【2叉堆】排序耗时 : 4.918
        规模 : 10000000 复杂索引计算的【3叉堆】排序耗时 : 3.452
        规模 : 10000000 复杂索引计算的【4叉堆】排序耗时 : 3.709
        规模 : 10000000 复杂索引计算的【5叉堆】排序耗时 : 3.124
        规模 : 10000000 复杂索引计算的【6叉堆】排序耗时 : 3.178
        规模 : 10000000 复杂索引计算的【7叉堆】排序耗时 : 3.198
        规模 : 10000000 复杂索引计算的【8叉堆】排序耗时 : 3.253
        规模 : 10000000 复杂索引计算的【9叉堆】排序耗时 : 3.386
        规模 : 10000000 复杂索引计算的【10叉堆】排序耗时 : 3.446
        规模 : 10000000 复杂索引计算的【11叉堆】排序耗时 : 3.472
        规模 : 10000000 复杂索引计算的【12叉堆】排序耗时 : 3.555
        规模 : 10000000 复杂索引计算的【13叉堆】排序耗时 : 3.748
        规模 : 10000000 复杂索引计算的【14叉堆】排序耗时 : 3.819
        规模 : 10000000 复杂索引计算的【15叉堆】排序耗时 : 3.835
        规模 : 10000000 复杂索引计算的【16叉堆】排序耗时 : 3.802
        规模 : 10000000 复杂索引计算的【17叉堆】排序耗时 : 3.831
        规模 : 10000000 复杂索引计算的【18叉堆】排序耗时 : 3.873
        规模 : 10000000 复杂索引计算的【19叉堆】排序耗时 : 3.808
     */
}
