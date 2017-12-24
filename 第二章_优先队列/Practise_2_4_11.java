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
            while ((k << 1) <= size) {
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
    public static double test(PQInterface<Integer> pq, Integer[] elems) {
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < elems.length; i++)
            pq.insert(elems[i]);
        int delCount = 5;
        while (delCount-- > 0)
            pq.delMax();
        return timer.elapsedTime();
    }
    public static void main(String[] args) {
        int N = 1000000;
        Integer[] elems = Integers(0, N - 1);
        UnorderedArr<Integer> ua = new UnorderedArr<Integer>(N);
        OrderedArr<Integer> oa = new OrderedArr<Integer>(N);
        Heap<Integer> h = new Heap<Integer>(N);
        StdOut.printf("无序数组 : %.3f\n", test(ua, elems));
        StdOut.printf("堆 : %.3f\n", test(h, elems));
        StdOut.printf("有序数组 : %.3f\n", test(oa, elems));
    }
}
