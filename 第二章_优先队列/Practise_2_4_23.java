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
    public static void main(String[] args) {
        int N = 200000;
        int[] a = ints(0, N - 1);
        for (int i = 2, j = 0; j < 20; i += i, j++) {
            int[] copy = intsCopy(a);
            StdOut.printf("规模 : %d %d叉树排序耗时 : %.3f\n",N, i, multiwayHeapSort(copy,  i));
            assert isSorted(copy);
        }
    }
}
