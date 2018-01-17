package Ch_2_5_Applications;

import static Ch_2_4_Priority_Queues.__Alphabet.*;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_5_24 {
    public static class StableMaxPQ<Key extends Comparable<Key>> {
        static class Wrapper <IKey extends Comparable<IKey>> {
            private IKey key;
            private int order;
            public Wrapper (IKey key, int order) {
                this.key = key;
                this.order = order;
            }
            public static <T extends Comparable<T>>  Comparator<Wrapper<T>> secondaryKey() {
                return new Comparator<Wrapper<T>>() {
                    public int compare(Wrapper<T> w1, Wrapper<T> w2) {
                        return w1.order < w2.order ? -1 :
                               w1.order > w2.order ? 1 : 0;
                    }
                };
            }
        }
        @SuppressWarnings("unchecked")
        private Wrapper<Key>[] keys = (Wrapper<Key>[])new Wrapper[2];
        private int size;
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            Wrapper<Key>[] keys = (Wrapper<Key>[])new Wrapper[newSize + 1];
            for (int i = 1; i <= size; i++) 
                keys[i] = this.keys[i];
            this.keys = keys;
        }
        public boolean isEmpty() { return size == 0; }
        public void insert(Key key) {
            if (size == keys.length - 1)
                resize(size << 1);
            keys[++size] = new Wrapper<Key>(key, size);
            swim(size);
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Key max = keys[1].key;
            keys[1] = keys[size--];
            sink(1);
            keys[size + 1] = null;
            if (size > 0 && size == (keys.length - 1) >> 2)
                resize((keys.length - 1) >> 1);
            return max;
        }
        @SuppressWarnings("unchecked")
        private int compare(Wrapper i, Wrapper j) {
            if (i.key.compareTo(j.key) != 0) return i.key.compareTo(j.key);
            return -Wrapper.secondaryKey().compare(i, j);
        }
        private void sink(int k) {
            int j;  Wrapper<Key> t = keys[k];
            while ((j = (k << 1)) <= size) {
                if (compare(keys[j], keys[j + 1]) < 0) j++;
                if (compare(t, keys[j]) >= 0) break;
                keys[k] = keys[j];
                k = j;
            }
            keys[k] = t;
        }
        private void swim(int k) {
            Wrapper<Key> t = keys[k];
            while (k > 1 && compare(t, keys[k >> 1]) > 0) {
                keys[k] = keys[k >> 1];
                k >>= 1;
            }
            keys[k] = t;
        }
    }
    static class Test implements Comparable<Test> {
        private static int counter = 0;
        private final int id = counter++;
        private String a;
        public Test(String a) { this.a = a; }
        public String toString() { return String.format("%s-%d", a, id); }
        public int compareTo(Test t) {
            return a.charAt(0) < t.a.charAt(0) ? -1 :
                   a.charAt(0) > t.a.charAt(0) ? 1 : 0;
        }
        public static Test[] tests(String[] s) {
            Test[] t = new Test[s.length];
            for (int i = 0; i < s.length; i++)
                t[i] = new Test(s[i]);
            return t;
        }
    }
    public static void main(String[] args) {
        Test[] t = Test.tests(random(30));
        StdOut.println("\n=========  排序前 ==========");
        for (Test tt : t) StdOut.println(tt);
        StableMaxPQ<Test> pq = new StableMaxPQ<Test>();
        for (Test tt : t) pq.insert(tt);
        StdOut.println("\n\n=========  排序后 ==========");
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }
    // output
    /*
     * 
        =========  排序前 ==========
        P-0
        Y-1
        Y-2
        G-3
        F-4
        H-5
        D-6
        Q-7
        J-8
        R-9
        T-10
        Y-11
        Z-12
        R-13
        T-14
        W-15
        U-16
        B-17
        T-18
        D-19
        D-20
        J-21
        K-22
        C-23
        D-24
        I-25
        H-26
        X-27
        E-28
        A-29
        
        
        =========  排序后 ==========
        Z-12
        Y-1
        Y-2
        Y-11
        X-27
        W-15
        U-16
        T-10
        T-14
        T-18
        R-9
        R-13
        Q-7
        P-0
        K-22
        J-8
        J-21
        I-25
        H-5
        H-26
        G-3
        F-4
        E-28
        D-6
        D-19
        D-20
        D-24
        C-23
        B-17
        A-29
     */
}   
