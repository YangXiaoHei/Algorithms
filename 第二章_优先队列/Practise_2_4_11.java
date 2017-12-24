package 第二章_优先队列;

import java.util.*;
import static 第二章_初级排序算法.Text_Array.*;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class Practise_2_4_11 {
    /*
     * 在分别实现前，我的预测是，因为无序队列中插入只需要常数时间，有序队列最坏情况下需要 O(N) 时间
     * 堆在最坏情况下需要 O(logN) 的时间，而删除最大元素操作无序队列最坏需要 O(N) 时间，有序队列需要常数时间
     * 堆需要常数时间，但删除最大元素操作较少，不足以发挥出常数时间的优势，所以无序数组是最有效的
     * 
     * 下面是证(da)明(lian)过程 ???
     * 
     * 排除缩容扩容的影响，这里不实现可调整的数组实现，仅仅在构造器中传入尺寸来分配数组空间
     */
    interface PQInterface <Key extends Comparable<Key>> {
        boolean isFull();
        boolean isEmpty();
        void insert(Key key);
        Key delMax();
    }
    /*
     * 无序数组
     */
    public static class UnorderedArr <Key extends Comparable<Key>> implements PQInterface<Key> {
        private Key[] keys;
        private int size;
        @SuppressWarnings("unchecked")
        public UnorderedArr(int N) {
            keys = (Key[])new Comparable[N];
        }
        public boolean isFull() { return size == keys.length; }
        public boolean isEmpty() { return size == 0; }
        public void insert(Key key) {
            if (isFull())
                throw new IllegalArgumentException("priority queue overflow!");
            keys[size++] = key;
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow!");
            Key max = keys[0];
            int idx = 0;
            for (int i = 0; i < size; i++) {
                if (keys[i].compareTo(max) > 0) {
                    max = keys[i];
                    idx = i;
                }
            }
            // 为了不在数组中留下空洞
            Key t = keys[idx]; keys[idx] = keys[size - 1]; keys[size - 1] = t;
            keys[size - 1] = null;
            --size;
            return max;
        }
    }
    /*
     * 有序数组
     */
    public static class OrderedArr <Key extends Comparable<Key>> implements PQInterface<Key>  {
        private Key[] keys;
        private int size;
        @SuppressWarnings("unchecked")
        public OrderedArr(int N) {
            keys = (Key[])new Comparable[N];
        }
        public boolean isFull() { return size == keys.length; }
        public boolean isEmpty() { return size == 0; }
        public void insert(Key key) {
            if (isFull())
                throw new IllegalArgumentException("priority queue overflow!");
            ++size; int i;
            for (i = size - 2; i >= 0 && key.compareTo(keys[i]) < 0; i--)
                keys[i + 1] = keys[i];
            keys[i + 1] = key; 
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow!");
            Key max = keys[--size];
            keys[size] = null;
            return max;
        }
    }
    /*
     * 堆
     */
    public static class Heap <Key extends Comparable<Key>> implements PQInterface<Key>  {
        private Key[] keys;
        private int size;
        @SuppressWarnings("unchecked")
        public Heap(int N) {
            keys = (Key[])new Comparable[N + 1];
        }
        public boolean isFull() { return size == keys.length - 1; }
        public boolean isEmpty() { return size == 0; }
        public void insert(Key key) {
            if (isFull())
                throw new IllegalArgumentException("priority queue overflow!");
            keys[++size] = key;
            int k = size; // 内联展开，避免函数调用上下文切换的影响
            while (k > 1 && keys[k >> 1].compareTo(keys[k]) < 0) {
                Key t = keys[k >> 1]; keys[k >> 1] = keys[k]; keys[k] = t;
                k >>= 1;
            }
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow!");
            Key max = keys[1];
            Key t = keys[size]; keys[size] = keys[1]; keys[1] = t;
            int k = 1; --size;
            while ((k << 1) <= size) { // 优化除法指令的耗时
                int j = k << 1;
                if (j < size && keys[j].compareTo(keys[j + 1]) < 0) j++;
                if (keys[k].compareTo(keys[j]) >= 0) break;
                Key tt = keys[k]; keys[k] = keys[j]; keys[j] = tt;
                k = j;
            }
            keys[size + 1] = null;
            return max;
        }
    }
    /*
     * 2.4.12
     */
    public static double test2(PQInterface<Integer> pq, Integer[] elems) {
        for (int i = 0; i < elems.length; i++)
            pq.insert(elems[i]);
        int lo = 10, hi = 210;
        Integer[] arr = Integers(lo, hi);
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < arr.length - (hi - lo + 1); i++)
            pq.insert(arr[i]);
        int delCount = (int)(elems.length * 0.8);
        while (delCount-- > 0)
            pq.delMax();
        return timer.elapsedTime();
    }
    /*
     * 2.4.11
     */
    public static double test1(PQInterface<Integer> pq, Integer[] elems) {
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < elems.length; i++)
            pq.insert(elems[i]);
        int delCount = 5;  // 在我电脑上，这里稍微改大一丢丢，堆实现都会比无序数组快...难道是因为我的一些小优化吗？？
        while (delCount-- > 0)
            pq.delMax();
        return timer.elapsedTime();
    }
    public static void main(String[] args) {
        /*
         * 2.4.11
         */
//        int N = 1000000;
//        Integer[] elems = Integers(0, N - 1);
//        UnorderedArr<Integer> ua = new UnorderedArr<Integer>(N);
//        OrderedArr<Integer> oa = new OrderedArr<Integer>(N);
//        Heap<Integer> h = new Heap<Integer>(N);
//        StdOut.printf("无序数组 : %.3f\n", test1(ua, elems));
//        StdOut.printf("堆 : %.3f\n", test1(h, elems));
//        StdOut.printf("有序数组 : %.3f\n", test1(oa, elems));
        
        
        /*
         * 
         * 2.4.12
         */
        int N2 = 40000;
        Integer[] elems2 = Integers(0, N2 - 1);
        UnorderedArr<Integer> ua2 = new UnorderedArr<Integer>(N2);
        OrderedArr<Integer> oa2 = new OrderedArr<Integer>(N2);
        Heap<Integer> h2 = new Heap<Integer>(N2);
        StdOut.printf("无序数组 : %.3f\n", test2(ua2, elems2));
        StdOut.printf("堆 : %.3f\n", test2(h2, elems2));
        StdOut.printf("有序数组 : %.3f\n", test2(oa2, elems2));
    }
    // output
    /*
     *  测试1
     *  无序数组 : 0.074
        堆 : 0.083
        有序数组 : 太久了...不想等
        
        测试2 
        无序数组 : 4.183
        堆 : 0.037
        有序数组 : 0.002

     */
}
