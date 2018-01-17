package Ch_3_1_Symbol_Tables;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_3_1_04 {
    static class Time implements Comparable<Time> {
        private final String timeString;
        private final int hour;
        private final int min;
        private final int sec;
        private final long timeInterval;
        public Time(String time) {
            if (!time.matches("\\d{1,2}:\\d{1,2}:\\d{1,2}"))
                throw new IllegalArgumentException("不合法的时间格式");
            timeString = time;
            String[] cmp = time.split(":");
            hour = Integer.parseInt(cmp[0]);
            min = Integer.parseInt(cmp[1]);
            sec = Integer.parseInt(cmp[2]);
            if (hour < 0 || hour > 23 ||
                min < 0 || min > 59  ||
                sec < 0 || sec > 59)
                throw new IllegalArgumentException("不合法的时间范围");
            timeInterval = hour * 60 * 60 + min * 60 + sec;
        }
        public int compareTo(Time t) {
            return timeInterval < t.timeInterval ? -1 :
                   timeInterval > t.timeInterval ? 1 : 0;
        }
        public String toString() { return timeString; }
    }
    static class Event {
        private final String event;
        public Event(String s) { event = s; }
        public String toString() { return event; }
    }
    static class ST<K extends Comparable<K>, V> {
        private class Node {
            K key; V value;
            Node next;
            Node() {}
            Node(K k, V v, Node n) { key = k; value = v; next = n; }
            public String toString() { return String.format("{%s  %s}", key, value); }
        }
        private Node header = new Node();
        private int size;
        private K min, max;
        public boolean isEmpty() { return size == 0; }
        public int size() { return size; }
        public void put(K k, V v) {
            if (k == null) return;
            if (v == null) {
                delete(k);
                return;
            }
            if      (min == null) min = k;
            else if (k.compareTo(min) < 0) min = k;
            if      (max == null) max = k;
            else if (k.compareTo(max) > 0) max = k;
            Node cur = null, pre = null;
            for (cur = header.next, pre = header; 
                 cur != null && cur.key.compareTo(k) < 0; 
                 cur = cur.next, pre = pre.next);
            if (cur != null && cur.key.compareTo(k) == 0)
                cur.value = v;
            else {
                ++size;
                pre.next = new Node(k, v, pre.next);
            }
        }
        public V get(K k) {
            if (k == null) return null;
            Node x;
            for (x = header.next; 
                 x != null && k.compareTo(x.key) != 0; 
                 x = x.next);
            return x == null ? null : x.value;
        }
        public K select(int k) {
            if (isEmpty() || k < 0 || k >= size) return null;
            Node x = header.next;
            while (k-- > 0 && x != null)
                x = x.next;
            return x == null ? null : x.key;
        }
        public int rank(K k) {
            if (k == null) return -1;
            int i = 0; Node cur;
            for (cur = header.next; 
                 cur != null && cur.key.compareTo(k) < 0; 
                 cur = cur.next, ++i);
            return cur == null ? -1 : i;
        }
        public void delete(K k) {
            if (k == null) return;
            if (isEmpty())
                throw new NoSuchElementException("symbol table is empty");
            Node cur, pre;
            for (cur = header.next, pre = header;
                 cur != null && cur.key.compareTo(k) != 0;
                 cur = cur.next, pre = pre.next);
            if (cur == null) return;
            if (cur.key == min) min = cur.next == null ? null : cur.next.key;
            if (cur.key == max) max = pre == header ? null : pre.key;
            --size;
            cur.key = null;
            pre.next = pre.next.next;
        }
        public K floor(K k) {
            if (k == null) return null;
            Node cur, pre;
            for (cur = header.next, pre = header;
                 cur != null && cur.key.compareTo(k) < 0;
                 cur = cur.next, pre = pre.next);
            if (pre == header)
                throw new NoSuchElementException("小于 k 的键不存在");
            return pre.key;
        }
        public K ceiling(K k) {
            if (k == null) return null;
            Node cur;
            for (cur = header.next;
                 cur != null && cur.key.compareTo(k) < 0;
                 cur = cur.next);
            if (cur == null) 
                throw new NoSuchElementException("大于 k 的键不存在");
            return cur.key;
        }
        public K max() { return max; }
        public K min() { return min; }
        public int size(K lo, K hi) {
            if (lo.compareTo(hi) > 0) return 0;
            return rank(hi) - rank(lo);
        }
        public Iterable<K> keys(K lo, K hi) {
            if (lo.compareTo(hi) > 0) return null;
            LinkedList<K> list = new LinkedList<K>();
            Node x = header.next;
            while (x != null && x.key.compareTo(lo) < 0)
                x = x.next;
            if (x == null) return null;
            Node cur, pre;
            for (cur = header.next, pre = header;
                 cur != null && cur.key.compareTo(hi) < 0;
                 cur = cur.next, pre = pre.next);
            while (x != pre.next) {
                list.add(x.key);
                x = x.next;
            }
            return list;
        }
        public Iterable<K> keys() { return keys(min, max); }
        public String toString() {
            if (isEmpty()) return "emptyST";
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("最大值 : %s  最小值 : %s  尺寸 : %d\n", max, min, size));
            for (Node x = header.next; x != null; x = x.next) 
                sb.append(x + "\n");
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        ST<Time, Event> st = new ST<Time, Event>();
        st.put(new Time("09:00:00"), new Event("Chicago"));
        st.put(new Time("09:00:03"), new Event("Phoenix"));
        st.put(new Time("09:00:13"), new Event("Houston"));
        st.put(new Time("09:00:59"), new Event("Chicago"));
        st.put(new Time("09:01:10"), new Event("Houston"));
        st.put(new Time("09:03:13"), new Event("Chicago"));
        st.put(new Time("09:10:11"), new Event("Seattle"));
        st.put(new Time("09:10:25"), new Event("Seattle"));
        st.put(new Time("09:14:25"), new Event("Phoenix"));
        st.put(new Time("09:19:32"), new Event("Chicago"));
        st.put(new Time("09:19:46"), new Event("Chicago"));
        st.put(new Time("09:21:05"), new Event("Chicago"));
        st.put(new Time("09:22:43"), new Event("Seattle"));
        st.put(new Time("09:22:54"), new Event("Seattle"));
        st.put(new Time("09:25:52"), new Event("Chicago"));
        st.put(new Time("09:35:21"), new Event("Chicago"));
        st.put(new Time("09:36:14"), new Event("Seattle"));
        st.put(new Time("09:37:44"), new Event("Phoenix"));
        
        StdOut.println(st);
        
        StdOut.printf("min() = %s\n", st.min());
        StdOut.printf("get(09:00:13) = %s\n", st.get(new Time("09:00:13")));
        StdOut.printf("floor(09:05:00) = %s\n", st.floor(new Time("09:05:00")));
        StdOut.printf("select(7) = %s\n", st.select(7));
        StdOut.println("遍历 keys(09:15:00, 09:25:00)\n");
        for (Time t : st.keys(new Time("09:15:00"), new Time("09:25:00")))
            StdOut.println(t.toString() + " : " + st.get(t));
        StdOut.printf("ceiling(09:30:00) = %s\n", st.ceiling(new Time("09:30:00")));
        StdOut.printf("max() = %s\n", st.max());    
        StdOut.printf("size(09:15:00, 09:25:00) = %s\n", st.size(new Time("09:15:00"), new Time("09:25:00"))); 
        StdOut.printf("rank(09:10:25) = %s\n", st.rank(new Time("09:10:25"))); 
    }
    // output
    /*
     *  最大值 : 09:37:44  最小值 : 09:00:00  尺寸 : 18
        {09:00:00  Chicago}
        {09:00:03  Phoenix}
        {09:00:13  Houston}
        {09:00:59  Chicago}
        {09:01:10  Houston}
        {09:03:13  Chicago}
        {09:10:11  Seattle}
        {09:10:25  Seattle}
        {09:14:25  Phoenix}
        {09:19:32  Chicago}
        {09:19:46  Chicago}
        {09:21:05  Chicago}
        {09:22:43  Seattle}
        {09:22:54  Seattle}
        {09:25:52  Chicago}
        {09:35:21  Chicago}
        {09:36:14  Seattle}
        {09:37:44  Phoenix}
        
        min() = 09:00:00
        get(09:00:13) = Houston
        floor(09:05:00) = 09:03:13
        select(7) = 09:10:25
        遍历 keys(09:15:00, 09:25:00)
        
        09:19:32 : Chicago
        09:19:46 : Chicago
        09:21:05 : Chicago
        09:22:43 : Seattle
        09:22:54 : Seattle
        ceiling(09:30:00) = 09:35:21
        max() = 09:37:44
        size(09:15:00, 09:25:00) = 5
        rank(09:10:25) = 7
     */
    
}
