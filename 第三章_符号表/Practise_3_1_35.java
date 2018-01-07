package 第三章_符号表;

import edu.princeton.cs.algs4.*;

public class Practise_3_1_35 {
    static class SequentialSearchST <K, V> {
        private class Node {
            K k; V v;
            Node next;
            Node() {}
            Node(K kk, V vv, Node nextt) { k = kk; v = vv; next = nextt; }
            public String toString() { return String.format("{ %s %s }", k, v); }
        }
        private Node header = new Node();
        private Node cache = null;
        private int size;
        public void put(K k, V v) {
            if (k == null) return;
            if (v == null) {
                delete(k);
                return;
            }
            if (cache != null && cache.k.equals(k)) {
                cache.v = v;
                return;
            }
            for (Node x = header.next; x != null; x = x.next) {
                if (x.k.equals(k)) {
                    x.v = v;
                    cache = x;
                    return;
                }
            }
            ++size;
            header.next = new Node(k, v, header.next);
            cache = header.next;
        }
        public V get(K k) {
            if (k == null) return null;
            if (cache != null && cache.k.equals(k)) return cache.v;
            for (Node x = header.next; x != null; x = x.next)
                if (x.k.equals(k)) {
                    cache = x;
                    return cache.v;
                }
            return null;
        }
        public void delete(K k) {
            if (k == null) return;
            Node pre, cur;
            for (pre = header, cur = header.next;
                 cur != null && !cur.k.equals(k);
                 cur = cur.next, pre = pre.next);
            if (cur == null) return;
            --size;
            if (cur == cache) cache = null;
            cur.k = null; cur.v = null;
            pre.next = pre.next.next;
        }
    }
    public static String randomStr() {
        StringBuilder sb = new StringBuilder();
        int len = StdRandom.uniform(2, 51);
        for (int i = 0; i < len; i++)
            sb.append((char)('a' + StdRandom.uniform(26)));
        return sb.toString();
    }
    public static void main(String[] args) {
        int N = 10000000;
        String[] words = new String[N];
        for (int i = 0; i < N; i++)
            words[i] = randomStr();
        
        double pre = 0, cur = 0; int k = 0;
        for (int i = 100, j = 0; j < 11; j++, i += i) {
            SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
            int count = i; k = 0;
            Stopwatch timer = new Stopwatch();
            while (count-- > 0) {
                st.put(words[k++], 10);
            }
            double t = timer.elapsedTime();
            StdOut.printf("规模 : %d 耗时 : %.3f 倍率 : %.1f\n", i, cur = t, cur / pre);
            pre = cur;
        }
    }
    // output
    /*
     *  规模 : 100 耗时 : 0.000 倍率 : NaN
        规模 : 200 耗时 : 0.000 倍率 : NaN
        规模 : 400 耗时 : 0.001 倍率 : Infinity
        规模 : 800 耗时 : 0.004 倍率 : 4.0
        规模 : 1600 耗时 : 0.005 倍率 : 1.3
        规模 : 3200 耗时 : 0.018 倍率 : 3.6
        规模 : 6400 耗时 : 0.082 倍率 : 4.6
        规模 : 12800 耗时 : 0.358 倍率 : 4.4
        规模 : 25600 耗时 : 1.554 倍率 : 4.3
        规模 : 51200 耗时 : 8.402 倍率 : 5.4
        规模 : 102400 耗时 : 39.722 倍率 : 4.7

     */
}
