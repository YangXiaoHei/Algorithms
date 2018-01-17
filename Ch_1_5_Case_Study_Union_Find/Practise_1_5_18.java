package Ch_1_5_Case_Study_Union_Find;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_1_5_18 {
    static RandomBag<Connection> randomGridGenerator(int N) {
        RandomBag<Connection> rb = new RandomBag<Connection>();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N - 1; j++) 
                if (StdRandom.bernoulli(0.5))
                    rb.add(new Connection(i * N + j, i * N + j + 1));
                else
                    rb.add(new Connection(i * N + j + 1, i * N + j));
        
        for (int i = 0; i < N - 1; i++)
            for (int j = 0; j < N; j++)
                if (StdRandom.bernoulli(0.5))
                    rb.add(new Connection(i * N + j, i * N + N + j));
                else
                    rb.add(new Connection(i * N + N + j, i * N + j));
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
       for (Connection c : randomGridGenerator(5))
           StdOut.println(c);
    }
    // output
    /*
     *  { 2, 1 }
        { 15, 10 }
        { 20, 21 }
        { 14, 13 }
        { 1, 6 }
        { 19, 14 }
        { 16, 15 }
        { 0, 5 }
        { 17, 18 }
        { 16, 11 }
        { 8, 9 }
        { 5, 6 }
        { 11, 12 }
        { 6, 7 }
        { 22, 21 }
        { 12, 17 }
        { 3, 8 }
        { 24, 19 }
        { 11, 6 }
        { 20, 15 }
        { 12, 13 }
        { 22, 23 }
        { 12, 7 }
        { 23, 18 }
        { 0, 1 }
        { 2, 7 }
        { 17, 16 }
        { 19, 18 }
        { 2, 3 }
        { 13, 18 }
        { 5, 10 }
        { 4, 3 }
        { 23, 24 }
        { 21, 16 }
        { 9, 4 }
        { 9, 14 }
        { 8, 13 }
        { 8, 7 }
        { 22, 17 }
        { 10, 11 }
     */
     
}
