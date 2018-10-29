package code;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class _Bag<T> implements Iterable<T> {
    private class Node {
        T value;
        Node next;
        Node(T value, Node next) { this.value = value; this.next = next; }
        public String toString() {
            return String.format("%s", value);
        }
    }
    private Node first;
    private int size;
    public void add(T v) {
        first = new Node(v, first);
        size++;
    }
    public boolean isEmpty() { return size == 0; }
    public boolean contains(T v) {
        if (isEmpty())
            return false;
        for (Node cur = first; cur != null; cur = cur.next)
            if (cur.value.equals(v))
                return true;
        return false;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node cur = first; cur != null; cur = cur.next)
            sb.append(cur + " ");
        sb.append("\n");
        return sb.toString();
    }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
              private Node icur = first;
              public boolean hasNext() {
                  return icur != null;
              }
              public T next() {
                  T v = icur.value;
                  icur = icur.next;
                  return v;
              }
        };
    }
    public static void main(String[] args) {
        _Bag<Integer> b = new _Bag<>();
        for (int i = 0; i < 100; i++)
            b.add(i);
        System.out.println(b);
        for (int w : b)
            StdOut.println(w);
    }
}
