package 第二章_优先队列;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_4_03 {
    interface PQOperation<Key extends Comparable<Key>> {
        boolean isOrderedImp();
        void insert(Key key);
        boolean isEmpty();
        Key delMax();
    }
    /*
     * 无序数组
     */
    static class UnorderedArr<Key extends Comparable<Key>> implements PQOperation<Key> {
        private int size;
        @SuppressWarnings("unchecked")
        private Key[] keys = (Key[])new Comparable[1];
        public boolean isOrderedImp() { return false; }
        public void insert(Key key) {
            if (size == keys.length)
                resize(size << 1);
            keys[size++] = key;
        }
        public boolean isEmpty() { return size == 0; }
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            Key[] newKeys = (Key[])new Comparable[newSize];
            for (int i = 0; i < size; i++)
                newKeys[i] = keys[i];
            keys = newKeys;
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Key max = keys[0];
            int index = 0;
            for (int i = 0; i < size; i++) {
                if (keys[i].compareTo(max) > 0) {
                    max = keys[i];
                    index = i;
                }
            }
            Key t = keys[index]; keys[index] = keys[size - 1]; keys[size - 1] = t;
            keys[size - 1] = null;
            --size;
            if (size > 0 && size == keys.length >> 2) 
                resize(keys.length >> 1);
            return max;
        }
    }
    /*
     * 无序链表
     */
    static class UnorderedList<Key extends Comparable<Key>> implements PQOperation<Key>  {
        private class Node {
            Node next;
            Key key;
            Node () {}
            Node (Key key) { this.key = key; }
        }
        private int size;
        private Node header = new Node();
        private Node cur = header;
        public boolean isOrderedImp() { return false; }
        public boolean isEmpty() { return size == 0; }
        public void insert(Key key) {
            ++size;
            cur.next = new Node(key);
            cur = cur.next;
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Node cur = header.next, recorder = header;
            Key max = cur.key;
            while (cur.next != null) {
                if (cur.next.key.compareTo(max) > 0) {
                    recorder = cur;
                    max = cur.next.key;
                }
                cur = cur.next;
            }
            --size;
            recorder.next.key = null;
            recorder.next = recorder.next.next;
            return max;
        }
    }
    /*
     * 有序数组
     */
    static class OrderedArr <Key extends Comparable<Key>> implements PQOperation<Key>  {
        @SuppressWarnings("unchecked")
        private Key[] keys = (Key[])new Comparable[1];
        private int size;
        public boolean isEmpty() { return size == 0; }
        public boolean isOrderedImp() { return true; }
        @SuppressWarnings("unchecked")
        public void resize(int newSize) {
            Key[] newKeys = (Key[])new Comparable[newSize];
            for (int i = 0; i < size; i++)
                newKeys[i] = keys[i];
            keys = newKeys;
        }
        public void insert(Key key) {
            if (size == keys.length)
                resize(size << 1);
            int i; ++size;
            for (i = size - 2; i >= 0 && key.compareTo(keys[i]) < 0; i--) 
                keys[i + 1] = keys[i];
            keys[i + 1] = key;
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Key max = keys[--size];
            keys[size] = null;
            if (size > 0 && size == keys.length >> 2)
                resize(keys.length >> 1);
            return max;
        }
    }
    /*
     * 有序链表
     */
    static class OrderedList <Key extends Comparable<Key>> implements PQOperation<Key>  {
        private class Node {
            Node next;
            Key key;
            Node() {}
            Node(Key key) { this.key = key; }
            Node insertAfter(Key key) {
                Node n = new Node(key);
                n.next = next;
                next = n;
                return n;
            }
        }
        private Node header = new Node();
        private int size;
        public boolean isOrderedImp() { return true; }
        public boolean isEmpty() { return size == 0; }
        public void insert(Key key) {
            Node cur = header;
            while (cur.next != null) {
                if (cur.next.key.compareTo(key) > 0) break;
                cur = cur.next;
            }
            ++size;
            cur.insertAfter(key);
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            --size;
            Node cur = header, pre = header;
            while ((cur = cur.next).next != null);
            while (pre.next != cur) pre = pre.next;
            Key max = cur.key;
            cur.key = null;
            pre.next = null;
            return max;
        }
    }
    public static double timeOftheWorstCase(PQOperation<Integer> pq) {
        if (pq.isOrderedImp()) {
            Stopwatch timer = new Stopwatch();
            for (int i = 100000; i >= 0; i--)
                pq.insert(i);
            while (!pq.isEmpty())
                pq.delMax();
            return timer.elapsedTime();
        } else {
            Stopwatch timer = new Stopwatch();
            for (int i = 0; i < 100000; i++)
                pq.insert(i);
            while (!pq.isEmpty())
                pq.delMax();
            return timer.elapsedTime();
        }
    }
    public static void correctTest(PQOperation<Integer> pq) {
        for (int i = 0; i < 10; i++)
            pq.insert(StdRandom.uniform(100));
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }
    public static void main(String[] args) {
        UnorderedArr<Integer> ua = new UnorderedArr<Integer>();
        UnorderedList<Integer> ul = new UnorderedList<Integer>();
        OrderedArr<Integer> oa = new OrderedArr<Integer>();
        OrderedList<Integer> ol = new OrderedList<Integer>();
        StdOut.printf("无序数组 : %.3f\n", timeOftheWorstCase(ua));
        StdOut.printf("无序链表 : %.3f\n", timeOftheWorstCase(ul));
        StdOut.printf("有序数组 : %.3f\n", timeOftheWorstCase(oa));
        StdOut.printf("有序链表 : %.3f\n", timeOftheWorstCase(ol));
    }
    // output
    /*
     *  无序数组 : 6.716
        无序链表 : 14.894
        有序数组 : 6.965
        有序链表 : 31.316

     */
}
