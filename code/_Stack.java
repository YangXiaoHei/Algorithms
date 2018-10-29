package code;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class _Stack<T> implements Iterable<T> {
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
    public void push(T v) {
        first = new Node(v, first);
        size++;
    }
    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }
    public T pop() {
        if (isEmpty())
            throw new NoSuchElementException();
        T toP = first.value;
        first = first.next;
        size--;
        return toP;
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
        _Stack<Integer> s = new _Stack<>();
        for (int i = 0; i < 10; i++)
            s.push(i);
        StdOut.println(s);
        for (int w : s)
            StdOut.println(w);
        StdOut.println("-------------");
        while (!s.isEmpty())
            StdOut.println(s.pop());
    }
}
