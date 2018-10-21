package Ch_4_2_Directed_Graphs;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class __Stack<T> implements Iterable<T> {
    private class Node {
        Node next;
        T item;
        Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
    private Node first;
    private int size;
    public void push(T item) {
        first = new Node(item, first);
        size++;
    }
    public void clear() {
        while (!isEmpty())
            pop();
    }
    public boolean isEmpty() { return size == 0; }
    public T pop() {
        if (isEmpty())
            throw new RuntimeException("empty");
        T toPop = first.item;
        first = first.next;
        size--;
        return toPop;
    }
    public int size() { return size; }
    public T peek() {
        if (isEmpty())
            throw new RuntimeException("empty");
        return first.item;
    }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node cur = first;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : this)
            sb.append(item + " ");
        return sb.toString();
    }
    public static void main(String[] args) {
        __Stack<Integer> stack = new __Stack<>();
        int i = 0;
        while (i < 10)
            stack.push(i++);
        while (!stack.isEmpty())
            StdOut.println(stack.pop());
    }
}
