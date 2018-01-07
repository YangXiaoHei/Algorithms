package 第三章_符号表;

import edu.princeton.cs.algs4.StdOut;

public class Practise_3_1_25 {
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
        public boolean isEmpty() { return size == 0; }
        public boolean contains(K k) {
            if (k == null) return false;
            /*
             *  命中缓存
             */
            if (cache != null && cache.k.equals(k))  return true;
            /*
             * 没有命中缓存
             */
            for (Node x = header.next; x != null; x = x.next)
                if (x.k.equals(k)) {
                    cache = x; // 更新缓存
                    return true;
                }
            return false;
        }
        public V get(K k) {
            if (k == null) return null;
            /*
             * 如果 k 命中缓存，直接使用缓存即可
             */
            if (cache != null && cache.k.equals(k)) return cache.v;
            /*
             * 如果没有命中，那么就查找符号表中的 k，如果找到，更新缓存
             */
            for (Node x = header.next; x != null; x = x.next)
                if (x.k.equals(k)) {
                    cache = x;
                    return x.v;
                }
            return null;
        }
        public void put(K k, V v) {
            if (k == null) return;
            if (v == null) {
                delete(k);
                return;
            }
            /*
             * 如果 k 命中缓存，直接使用缓存结点
             */
            if (cache != null && cache.k.equals(k)) {
                cache.v = v;
                return;
            }
            /*
             * 如果这个 k 没命中缓存，但 k 仍然有可能和符号表中的 k 重复
             */
            for (Node x = header.next; x != null; x = x.next) {
                if (x.k.equals(k)) {
                    x.v = v;
                    cache = x; // 更新缓存
                    return;
                }
            }
            ++size;
            header.next = new Node(k, v, header.next);
            cache = header.next; // 更新缓存
           
        }
        /*
         * 突然发现...有点尴尬，单向链表，即便你的 delete 方法命中缓存，你也没法不遍历而删除该结点
         */
        public void delete(K k) {
            if (k == null) return;
            Node pre = null, cur = null;
           for (pre = header, cur = header.next;
                cur != null && !cur.k.equals(k);
                pre = pre.next, cur = cur.next);
           if (cur == null) return;
           --size;
           if (cur == cache) cache = null; // 删除缓存
           cur.k = null; cur.v = null;
           pre.next = pre.next.next;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Node x = header.next; x != null; x = x.next)
                sb.append(x + "\n");
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        st.put("yanghan", 1);
        st.put("lijie", 2);
        st.put("zhangyafang", 3);
        st.put("heihei", 4);
        st.put("xixi", 5);
        st.put("pupu", 6);
        st.put("haha", 7);
        StdOut.println(st.get("yanghan"));
        StdOut.println(st.get("yanghan"));
        StdOut.println(st.get("zhangyafang"));
        StdOut.println(st.get("xixi"));
        st.put("xixi", 9);
        StdOut.println(st.get("xixi"));
        st.delete("xixi");
        StdOut.println(st.get("lijie"));
        st.put("lijie", 11);
        StdOut.println(st.get("lijie"));
    }
    // output
    /*
     *  1
        1
        3
        5
        9
        2
        11

     */
}
