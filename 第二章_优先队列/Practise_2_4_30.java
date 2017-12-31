package 第二章_优先队列;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class Practise_2_4_30 {
    static class MaxPQ <Key extends Comparable<Key>> {
        @SuppressWarnings("unchecked")
        private Key[] keys = (Key[])new Comparable[2];
        private int size;
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            Key[] newKeys = (Key[])new Comparable[newSize + 1];
            for (int i = 1; i <= size; i++)
                newKeys[i] = keys[i];
            keys = newKeys;
        }
        public int size() { return size; }
        public boolean isEmpty() { return size == 0; }
        public void insert(Key key) {
            if (size == keys.length - 1)
                resize(size << 1);
            int k = ++size;
            while (k > 1 && key.compareTo(keys[k >> 1]) > 0) {
                keys[k] = keys[k >> 1];
                k >>= 1;
            }
            keys[k] = key;
        }
        public Key max() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            return keys[1];
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Key max = keys[1];
            keys[1] = keys[size--];
            Key top = keys[1];
            int k = 1;
            while ((k << 1) <= size) {
                int j = k << 1;
                if (keys[j].compareTo(keys[j + 1]) < 0) j++;
                if (top.compareTo(keys[j]) >= 0) break;
                keys[k] = keys[j];
                k = j;
            }
            keys[k] = top;
            keys[size + 1] = null;
            if (size > 0 && size == (keys.length - 1) >> 2)
                resize((keys.length - 1) >> 1);
            return max;
        }
    }
    static class MinPQ <Key extends Comparable<Key>> {
        @SuppressWarnings("unchecked")
        private Key[] keys = (Key[])new Comparable[2];
        private int size;
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            Key[] keys = (Key[])new Comparable[newSize + 1];
            for (int i = 1; i <= size; i++)
                keys[i] = this.keys[i];
            this.keys = keys;
        }
        public int size() { return size; }
        public boolean isEmpty() { return size == 0; }
        public Key min() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            return keys[1];
        }
        public void insert(Key key) {
            if (size == keys.length - 1)
                resize(size << 1);
            int k = ++size;
            while (k > 1 && key.compareTo(keys[k >> 1]) < 0) {
                keys[k] = keys[k >> 1];
                k >>= 1;
            }
            keys[k] = key;
        }
        public Key delMin() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Key min = keys[1];
            keys[1] = keys[size--];
            Key top = keys[1];
            int k = 1;
            while ((k << 1) <= size) {
                int j = k << 1;
                if (keys[j].compareTo(keys[j + 1]) > 0) j++;
                if (top.compareTo(keys[j]) <= 0) break;
                keys[k] = keys[j];
                k = j;
            }
            keys[k] = top;
            keys[size + 1] = null;
            if (size > 0 && size == (keys.length - 1) >> 2)
                resize((keys.length - 1) >> 1);
            return min;
        }
    }
    public static class GetMedianDynamically  {
        private MaxPQ<Double> maxPQ = new MaxPQ<Double>();
        private MinPQ<Double> minPQ = new MinPQ<Double>();
        private Double mid;
        public boolean isEmpty() { return mid == null; }
        public void insert(Double key) {
            if (mid == null) {
                mid = key;
                return;
            } 
            if (key.compareTo(mid) > 0)
                minPQ.insert(key);
            else
                maxPQ.insert(key);
            if (Math.abs(minPQ.size() - maxPQ.size()) == 2) {
                if (minPQ.size() < maxPQ.size()) {
                    minPQ.insert(mid);
                    mid = maxPQ.delMax();
                } else {
                    maxPQ.insert(mid);
                    mid = minPQ.delMin();
                }
            }
        }
        /*
         * 查看当前中位数，不删除
         */
        public Double peekMedian() { 
            if      (isEmpty()) return null;
            else if (maxPQ.size() > minPQ.size()) return (mid + maxPQ.max()) / 2.0;
            else if (maxPQ.size() < minPQ.size()) return (mid + minPQ.min()) / 2.0;
            else    return mid;
        }
        /*
         * 查看当前中位数，并删除
         */
        public Double getMedian() {
            if      (isEmpty()) throw new NoSuchElementException("both maxPQ and minPQ underflow");
            else if (maxPQ.isEmpty() && minPQ.isEmpty()) { Double m = mid; mid = null; return m; }
            else if (maxPQ.size() > minPQ.size()) return (mid + maxPQ.delMax()) / 2.0;
            else if (maxPQ.size() < minPQ.size()) return (mid + minPQ.delMin()) / 2.0;
            else    {
                Double median = mid;
                mid = minPQ.delMin();
                return median;
            }
        }
    }
    public static void main(String[] args) {
        int[] d = ints(30, 0, 20);
        print(d);
        GetMedianDynamically m = new GetMedianDynamically();
        for (int i : d) {
            m.insert(i * 1.0);
            StdOut.printf("插入元素 %.1f 当前中位数是 : %.1f\n", i * 1.0, m.peekMedian());
        }
        while (!m.isEmpty())
            StdOut.printf("取出当前中位数并删除 : %.1f\n", m.getMedian());
    }
    // output
    /*
     * 
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  
        8   13  11  13  0   9   9   16  12  0   12  2   14  0   4   20  16  14  1   5   15  6   8   0   15  2   8   12  17  0   
        插入元素 8.0 当前中位数是 : 8.0
        插入元素 13.0 当前中位数是 : 10.5
        插入元素 11.0 当前中位数是 : 11.0
        插入元素 13.0 当前中位数是 : 12.0
        插入元素 0.0 当前中位数是 : 11.0
        插入元素 9.0 当前中位数是 : 10.0
        插入元素 9.0 当前中位数是 : 9.0
        插入元素 16.0 当前中位数是 : 10.0
        插入元素 12.0 当前中位数是 : 11.0
        插入元素 0.0 当前中位数是 : 10.0
        插入元素 12.0 当前中位数是 : 11.0
        插入元素 2.0 当前中位数是 : 10.0
        插入元素 14.0 当前中位数是 : 11.0
        插入元素 0.0 当前中位数是 : 10.0
        插入元素 4.0 当前中位数是 : 9.0
        插入元素 20.0 当前中位数是 : 10.0
        插入元素 16.0 当前中位数是 : 11.0
        插入元素 14.0 当前中位数是 : 11.5
        插入元素 1.0 当前中位数是 : 11.0
        插入元素 5.0 当前中位数是 : 10.0
        插入元素 15.0 当前中位数是 : 11.0
        插入元素 6.0 当前中位数是 : 10.0
        插入元素 8.0 当前中位数是 : 9.0
        插入元素 0.0 当前中位数是 : 9.0
        插入元素 15.0 当前中位数是 : 9.0
        插入元素 2.0 当前中位数是 : 9.0
        插入元素 8.0 当前中位数是 : 9.0
        插入元素 12.0 当前中位数是 : 9.0
        插入元素 17.0 当前中位数是 : 9.0
        插入元素 0.0 当前中位数是 : 9.0
        取出当前中位数并删除 : 9.0
        取出当前中位数并删除 : 9.0
        取出当前中位数并删除 : 9.5
        取出当前中位数并删除 : 11.0
        取出当前中位数并删除 : 10.0
        取出当前中位数并删除 : 12.0
        取出当前中位数并删除 : 10.0
        取出当前中位数并删除 : 12.0
        取出当前中位数并删除 : 9.0
        取出当前中位数并删除 : 12.0
        取出当前中位数并删除 : 9.0
        取出当前中位数并删除 : 13.0
        取出当前中位数并删除 : 8.5
        取出当前中位数并删除 : 13.0
        取出当前中位数并删除 : 8.0
        取出当前中位数并删除 : 14.0
        取出当前中位数并删除 : 8.0
        取出当前中位数并删除 : 14.0
        取出当前中位数并删除 : 8.0
        取出当前中位数并删除 : 15.0
        取出当前中位数并删除 : 7.5
        取出当前中位数并删除 : 15.0
        取出当前中位数并删除 : 8.0
        取出当前中位数并删除 : 16.0
        取出当前中位数并删除 : 8.0
        取出当前中位数并删除 : 16.0
        取出当前中位数并删除 : 8.5
        取出当前中位数并删除 : 17.0
        取出当前中位数并删除 : 10.0
        取出当前中位数并删除 : 20.0
     */
}
