package Ch_4_4_Shortest_Paths;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class __Bag<T> implements Iterable<T> {
    private class Node {
        Node next;
        T item;
        Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
    private Node tail;
    private Node head;
    int size;
    public int size() { return size; }
    public void add(T item) {
        Node n = new Node(item, null);
        if (head == null)
            head = n;
        if (tail == null)
            tail = n;
        else {
            tail.next = n;
            tail = n;
        }
        size++;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T i : this)
            sb.append(i + " ");
        return sb.toString();
        
    }
    public boolean contains(T item) {
        for (T i : this)
            if (item.equals(i))
                return true;
        return false;
    }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node cur = head;
            public boolean hasNext() {
                return cur != null;
            }
            public T next() {
                T item = cur.item;
                cur = cur.next;
                return item;
            }
        };
    }
    public static void main(String[] args) {
        __Bag<Integer> bag = new __Bag<>();
        int i = 0;
        while (i < 10)
            bag.add(i++);
        for (int w : bag)
            StdOut.println(w);
    }
}
