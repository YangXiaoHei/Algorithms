package Ch_4_2_Directed_Graphs;

import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class __Queue<T> implements Iterable<T> {
    private T[] items = (T[])new Object[1];
    private int head, tail, size;
    /*
     * tail 总是指向莫元素的后一个位置
     * head 总是指向头部元素
     */
    private void resize(int newSize) {
        T[] newItems = (T[])new Object[newSize];
        int cur = head, k = 0;
        do {
            newItems[k++] = items[cur];
            cur++;
            if (cur == items.length) cur = 0;
        } while (tail != cur);
        head = 0;
        tail = k;
        items = newItems;
    }
    public void enqueue(T item) {
        if (size == items.length)
            resize(size << 1);
        items[tail] = item;
        tail++;
        if (tail == items.length)
            tail = 0;
        size++;
//        StdOut.println(this);
    }
    public T dequeue() {
        if (isEmpty())
            throw new RuntimeException("empty");
        T item = items[head];
        head++;
        size--;
        if (head == items.length)
            head = 0;
        if (size > 0 && size == (items.length >> 2))
            resize(items.length >> 1);
//        StdOut.println(this);
        return item;
    }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cur = head;
            public boolean hasNext() {
                return cur != tail;
            }
            public T next() {
                T item = items[cur];
                if (++cur == items.length)
                    cur = 0;
                return item;
            }
        };
    }
    public boolean isEmpty() { return size == 0; }
    public String toString() {
        if (isEmpty()) return "[empty]";
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for(int i = 0; i < items.length; i++)
            sb.append(String.format(" %3s |", items[i] == null ? " " : items[i]));
        sb.append(String.format(""
                + "           "
                + "<<<<<<<<<< "
                + "size : %d "
                + "head : %d "
                + "tail : %d "
                + "cap : %d"
                + " >>>>>>>>>>", size, head, tail, items.length));
        return sb.toString();
    }
    public static void main(String[] args) {
        __Queue<Integer> q = new __Queue<>();
        int i = -1;
        while (i++ < 10)
            q.enqueue(i);
        for (int w : q)
            StdOut.println(w);
    }
    
}
