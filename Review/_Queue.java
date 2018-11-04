package Review;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class _Queue<T> implements Iterable<T> {
    @SuppressWarnings("unchecked")
    private T[] value = (T[])new Object[16];
    private int head; 
    private int tail; 
    private int size;
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        T[] newValue = (T[])new Object[newSize];
        int k = head, n = 0;
        do {
            newValue[n++] = value[k++];
            if (k == value.length)
                k = 0;
        } while (k != tail);
        value = newValue;
        head = 0;
        tail = size;
    }
    public int size() { return size; }
    public void clear() {
        while (!isEmpty())
            dequeue();
    }
    public void enqueue(T v) {
        if (size == value.length)
            resize(size << 1);
        value[tail++] = v;
        if (tail == value.length)
            tail = 0;
        size++;
    }
    public boolean isEmpty() { return size == 0; }
    public T dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        T deq = value[head++];
        if (head == value.length)
            head = 0;
        --size;
        if (size > 1 && size == value.length >> 2)
            resize(value.length >> 1);
        return deq;
    }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = head;
            public boolean hasNext() {
                return i != tail;
            }
            public T next() {
                T n = value[i++];
                if (i == value.length)
                    i = 0;
                return n;
            }
        };
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int k = head, sz = size;
        while (sz-- > 0) {
            sb.append(String.format("%5s", value[k++]));
            if (k == value.length)
                k = 0;
        } 
        return sb.toString();
    }
    public static void main(String[] args) {
        _Queue<Integer> q = new _Queue<>();
        for (int i = 0; i < 100; i++)
            q.enqueue(i);
        StdOut.println(q);
        for (int w : q)
            StdOut.println(w);
        StdOut.println("-------------------");
        while (!q.isEmpty())
            StdOut.println(q.dequeue());
        
    }
    
}
