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
     */
    public static void createHeapBySink(int[] a, int d) {
        int N = a.length;
        for (int i = (N - 2) / d + 1; i > 0; i--) {
            int maxIndex = i - 1, max = a[i - 1];
            while ((d * (i - 1) + 2) <= N) {
                for (int j = 1; j <= d; j++) {
                    int c = d * (i - 1) + j + 1;
                    if (c > N) break;
                    if (a[c - 1] > max) {
                        max = a[c - 1];
                        maxIndex = c - 1;
                    }
                }
                if (maxIndex + 1 == i) break;
                int t = a[maxIndex]; 
                a[maxIndex] = a[i - 1]; 
                a[i - 1] = t;
                i = maxIndex + 1;
                max = a[maxIndex];
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
    public static void sink(int[] a, int i, int size, int d) {
        int maxIndex = i - 1, max = a[i - 1];
        while (d * (i - 1) + 2 <= size) {
            for (int j = 1; j <= d; j++) {
                int c = d * (i - 1) + j + 1;
                if (c > size) break;
                if (a[c - 1] > max) {
                    max = a[c - 1];
                    maxIndex = c - 1;
                }
            }
            if (maxIndex + 1 == i) break;
            int t = a[maxIndex]; 
            a[maxIndex] = a[i - 1]; 
            a[i - 1] = t;
            i = maxIndex + 1;
            max = a[maxIndex];
        }
    }
    public static double multiwayHeapSort(int[] a, int d) {
        Stopwatch timer = new Stopwatch();
        createHeapBySink(a, d);
        int N = a.length;
        while (N > 1) {
            int t = a[0];
            a[0] = a[N - 1];
            a[N - 1] = t;
            sink(a, 1, --N, d);
        }
        return timer.elapsedTime();
    }
    public static void test1() {
        int N = 200000;
        int[] a = ints(0, N - 1);
        int[] copy1 = intsCopy(a);
        StdOut.printf("规模 : %d %d叉树排序耗时 : %.3f\n",N, 2, Text_HeapSort.heap(copy1));
        assert isSorted(copy1);
        for (int i = 2, j = 0; j < 20; i += i, j++) {
            int[] copy = intsCopy(a);
            StdOut.printf("规模 : %d %d叉树排序耗时 : %.3f\n",N, i, multiwayHeapSort(copy,  i));
            assert isSorted(copy);
        }
    }
    public static void test2() {
        int[] a = ints(0, 100000);
        int[] copy = intsCopy(a);
        Stopwatch timer1 = new Stopwatch();
        createBinaryHeapBySink(a);
        double t1 = timer1.elapsedTime();
        timer1 = new Stopwatch();
        createHeapBySink(copy, 2);
        double t2 = timer1.elapsedTime();
        StdOut.printf("用 方法1 建立二叉堆用时 : %.3f\n", t1);
        StdOut.printf("用 方法2 建立二叉堆用时 : %.3f\n", t2);
    }
    public static void main(String[] args) {
        test2();
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
