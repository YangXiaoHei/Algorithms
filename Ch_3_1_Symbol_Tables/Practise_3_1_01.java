package Ch_3_1_Symbol_Tables;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_3_1_01 {
    static class ST <K, V> {
        private class Node {
            K key; V value;
            Node next;
            Node() {}
            Node(K k, V v) {
                key = k;
                value = v;
            }
            Node(K k, V v, Node n) {
                key = k;
                value = v;
                next = n;
            }
        }
        public int size() { return size; }
        public boolean isEmpty() { return size == 0; }
        private Node header = new Node();
        private int size;
        public void put(K key, V value) {
            if (key == null) return;
            /*
             * 先遍历一遍，看 key 是否已经被包含，如果找到了，并且 value 不是空，就更新
             * 如果 value 是空，那么就删除该符号
             */
            for (Node x = header.next, y = header; x != null; x = x.next, y = y.next) {
                if (x.key.equals(key)) {
                    if (value != null) {
                        x.value = value;
                     } else {
                         x.value = null;
                         y.next = y.next.next;
                         --size;
                     }
                    return;
                }
            }
            // 如果执行到这里 value 还为 null 直接返回
            if (value == null) return;
            ++size;
            header.next = new Node(key, value, header.next);
        }
        public V get(K key) {
            if (key == null) return null;
            for (Node x = header.next; x != null; x = x.next) 
                if (key.equals(x.key)) 
                    return x.value;
            return null;
        }
        public void delete(K key) {
            if (key == null) return;
            for (Node y = header,x = header.next; x != null; x = x.next, y = y.next)
                if (x.key.equals(key)) {
                    x.key = null;
                    y.next = y.next.next;
                    --size;
                }
        }
        public boolean contain(K key) {
            if (key == null) return false;
            for (Node x = header.next; x != null; x = x.next)
                if (x.key.equals(key)) return true;
            return false;
        }
        public Iterable<K> keys() {
            LinkedList<K> list = new LinkedList<K>();
            for (Node x = header.next; x != null; x = x.next)
                list.add(x.key);
            return list;
        }
    }
    public static void main(String[] args) {
        ST<String, Double> grades = new ST<String, Double>();
        grades.put("A+", 4.33);
        grades.put("A", 4.00);
        grades.put("A-", 3.67);
        grades.put("B+", 3.33);
        grades.put("B", 3.00);
        grades.put("B-", 2.67);
        grades.put("C+", 2.33);
        grades.put("C", 2.00);
        grades.put("C-", 1.67);
        grades.put("D", 1.00);
        grades.put("F", 0.00);
        
        String[] s = { "A+", "C-", "C", "B", "A-", "A", "B-", "B+" };
        double sum = 0;
        for (String ss : s)
            sum += grades.get(ss);
        sum /= s.length;
        StdOut.printf("GPA = %.3f\n", sum);
    }
    // output
    /*
     * GPA = 3.084

     */
}
