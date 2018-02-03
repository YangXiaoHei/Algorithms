package Ch_3_3_Hash_Tables;

import static Tool.ArrayGenerator.Alphbets.*;
import edu.princeton.cs.algs4.*;

public class __SequentialSearchST <K, V> {
    private class Node {
        K key; V val;
        Node next, prev;
        Node () {}
        Node (K k, V v, Node pv, Node nt) {
            key = k;
            val = v;
            prev = pv;
            next = nt;
        }
        Node insertBefore(K k, V v) {
            Node n = new Node(k, v, prev, this);
            if (prev != null) prev.next = n;
            prev = n;
            return n;
        }
        Node delete() {
            Node h = this;
            if (prev != null) prev.next = next;
            if (next != null) next.prev = prev;
            return h;
        }
        public String toString() {
            return String.format("{%5s  %5s}\n", key, val);
        }
    }
    private Node header = new Node(), tailer = new Node();
    {
        header.next = tailer;
        tailer.prev = header;
    }
    private int size;
    private Node cache = null;
    public int size() { return size; }
    public boolean isEmpty() { return header.next == tailer; }
    public void put(K k, V v) {
        if (k == null) throw new IllegalArgumentException();
        if (v == null) {
            delete(k);
            return;
        }
        // 命中缓存
        if (cache != null && cache.key.equals(k)) {
            cache.val = v;
            return;
        }
        Node x = search(k);
        if (x != null && k.equals(x.key)) {
            cache = x;
            x.val = v;
            return;
        }
        ++size;
        cache = tailer.insertBefore(k, v);
    }
    public V get(K k) {
        if (k == null) throw new IllegalArgumentException();
        if (cache != null && cache.key.equals(k)) return cache.val;
        Node n = search(k);
        if (n != null) cache = n;
        return n != null && k.equals(n.key) ? n.val : null;
    }
    public void delete(K k) {
        if (k == null) throw new IllegalArgumentException();
        Node x = search(k);
        if (x != null && k.equals(x.key)) {
            x.delete(); --size;
        }
        if (cache == x) cache = null;
    }
    public Iterable<K> keys() {
        Queue<K> q = new Queue<>();
        for (Node x = header.next; x != tailer; x = x.next)
            q.enqueue(x.key);
        return q;
    }
    public boolean contain(K k) { return get(k) != null; }
    private Node search(K k) {
        Node x = null;
        for (x = header.next;
             x != tailer && !k.equals(x.key); 
             x = x.next);
        return x == tailer ? null : x;
    }
    public String toString() {
        if (header.next == tailer) return "Empty Tree";
        StringBuilder sb = new StringBuilder();
        for (Node x = header.next; x != tailer; x = x.next)
            sb.append(x);
        return sb.toString();
    }
    public static void main(String[] args) {
        __SequentialSearchST<String, Integer> st = new __SequentialSearchST<>();
        String[] s = allRandom();
        print(s);
        for (String ss : s) st.put(ss, 1);
        StdOut.println(st);
        st.delete("G");
        StdOut.println(st);
    }
    // output
    /*
     *   0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
         S  T  M  H  L  N  B  V  Q  O  A  C  J  U  I  R  G  F  K  Y  P  E  W  Z  X  D

        {    A      1}
        {    B      1}
        {    C      1}
        {    D      1}
        {    E      1}
        {    F      1}
        {    G      1}
        {    H      1}
        {    I      1}
        {    J      1}
        {    K      1}
        {    L      1}
        {    M      1}
        {    N      1}
        {    O      1}
        {    P      1}
        {    Q      1}
        {    R      1}
        {    S      1}
        {    T      1}
        {    U      1}
        {    V      1}
        {    W      1}
        {    X      1}
        {    Y      1}
        {    Z      1}
        
        {    A      1}
        {    B      1}
        {    C      1}
        {    D      1}
        {    E      1}
        {    F      1}
        {    H      1}
        {    I      1}
        {    J      1}
        {    K      1}
        {    L      1}
        {    M      1}
        {    N      1}
        {    O      1}
        {    P      1}
        {    Q      1}
        {    R      1}
        {    S      1}
        {    T      1}
        {    U      1}
        {    V      1}
        {    W      1}
        {    X      1}
        {    Y      1}
        {    Z      1}
     */
}
