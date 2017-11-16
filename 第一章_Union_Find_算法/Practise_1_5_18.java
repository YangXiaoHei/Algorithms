package 第一章_Union_Find_算法;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_1_5_18 {
    static RandomBag<Connection> randomGridGenerator(int N) {
        RandomBag<Connection> rb = new RandomBag<Connection>();
        for (int p = 0; p < N; p++) {
            for (int q = 0; q < N; q++) {
                if (p != q) {
                    rb.add(new Connection(p, q));
                }
            }
        }
        return rb;
    }
    static class Connection {
        int p, q;
        Connection(int p, int q) {
            this.p = p;
            this.q = q;
        }
        public String toString() {  return String.format("{ %d, %d }", p, q); }
    }
    static class RandomBag<T> implements Iterable<T> {
        @SuppressWarnings("unchecked")
        private T[] items = (T[])new Object[1];
        private int size;
        RandomBag() {}
        boolean isEmpty() { return size == 0; }
        int size() { return size; }
        void resize(int newSize) {
            @SuppressWarnings("unchecked")
            T[] newItems = (T[])new Object[newSize];
            for (int i = 0; i < size; i++)
                newItems[i] = items[i];
            items = newItems;
        }
        void add(T item) {
            if (size == items.length)
                resize(size * 2);
            items[size++] = item;
        }
        public Iterator<T> iterator() {
           return new Iterator<T>() {
               private int index;
               @SuppressWarnings("unchecked")
               private T[] random = (T[])new Object[size];
               {
                   for (int i = 0; i < size; i++)
                       random[i] = items[i];
                   for (int i = 0; i < size; i++) {
                       int r = i + StdRandom.uniform(size - i);
                       T tmp = random[r];
                       random[r] = random[i];
                       random[i] = tmp;
                   }
               }
               public boolean hasNext() { return index < size; }
               public T next() { return random[index++]; }
           };
        }
    }
    public static void main(String[] args) {
       for (Connection c : randomGridGenerator(10))
           StdOut.println(c);
    }
    // output
    /*
     *  { 8, 0 }
        { 9, 1 }
        { 2, 5 }
        { 0, 6 }
        { 1, 6 }
        { 6, 9 }
        { 8, 7 }
        { 4, 8 }
        { 2, 4 }
        { 2, 3 }
        { 5, 4 }
        { 6, 0 }
        { 3, 5 }
        { 7, 3 }
        { 0, 5 }
        { 7, 2 }
        { 7, 8 }
        { 6, 8 }
        { 9, 6 }
        { 1, 7 }
        { 9, 8 }
        { 4, 3 }
        { 8, 9 }
        { 4, 2 }
        { 5, 3 }
        { 3, 2 }
        { 5, 6 }
        { 6, 5 }
        { 1, 9 }
        { 7, 9 }
        { 9, 3 }
        { 2, 0 }
        { 0, 1 }
        { 1, 4 }
        { 7, 4 }
        { 9, 0 }
        { 9, 2 }
        { 6, 4 }
        { 0, 3 }
        { 6, 7 }
        { 1, 3 }
        { 7, 6 }
        { 3, 7 }
        { 2, 1 }
        { 5, 9 }
        { 6, 3 }
        { 3, 0 }
        { 5, 8 }
        { 3, 1 }
        { 8, 1 }
        { 9, 7 }
        { 0, 4 }
        { 2, 6 }
        { 3, 6 }
        { 2, 7 }
        { 4, 1 }
        { 5, 1 }
        { 7, 1 }
        { 0, 2 }
        { 6, 2 }
        { 7, 5 }
        { 3, 8 }
        { 8, 3 }
        { 1, 0 }
        { 4, 6 }
        { 2, 8 }
        { 8, 2 }
        { 0, 8 }
        { 4, 9 }
        { 7, 0 }
        { 1, 8 }
        { 5, 2 }
        { 4, 5 }
        { 9, 4 }
        { 5, 7 }
        { 0, 9 }
        { 4, 7 }
        { 1, 5 }
        { 3, 4 }
        { 3, 9 }
        { 9, 5 }
        { 8, 4 }
        { 4, 0 }
        { 1, 2 }
        { 8, 5 }
        { 6, 1 }
        { 5, 0 }
        { 0, 7 }
        { 2, 9 }
        { 8, 6 }
     */
}
