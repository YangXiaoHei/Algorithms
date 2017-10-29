package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.*;

public class Practise_1_3_34 {
	static class RandomBag<T> implements Iterable<T> {
		@SuppressWarnings("unchecked")
		private T[] items = (T[])new Object[1];
		private int size;
		public RandomBag() {}
		public int size() { return size; }
		public boolean isEmpty() { return size == 0; }
		public void add(T item) {
			if (size == items.length)
				resize(2 * size);
			items[size++] = item;
		}
		@SuppressWarnings("unchecked")
		private void resize(int newsize) {
			T[] newItems = (T[])new Object[newsize];
			for(int i = 0; i < size; i++)
				newItems[i] = items[i];
			items = newItems;
		}
		@SuppressWarnings("unchecked")
		private T[] randomCopy() {
			T[] random = (T[])new Object[size];
			for(int i = 0; i < size; i++)
				random[i] = items[i];
			/*
			 * r = [i, N)
			 */
			for(int i = 0; i < random.length; i++) {
				int r = i + StdRandom.uniform(random.length - i);
				T tmp = random[r];
				random[r] = random[i];
				random[i] = tmp;
			}
			return random;
		}
		public Iterator<T> iterator() {
			return new Iterator<T>() {
				private T[] randomCopy = randomCopy();
				private int index = 0;
				public boolean hasNext() {
					return index < randomCopy.length;
				}
				public T next() {
					return randomCopy[index++];
				}
				public void remove() {
					throw new UnsupportedOperationException();
				}
			};
		}
	}
	public static void test() {
		RandomBag<Integer> bag = new RandomBag<Integer>();
		for(int i = 0; i < 20; i++)
			bag.add(i);
		
		for(int i = 0; i < 10; i++) {
			for(Integer elem : bag) 
				StdOut.print(elem + " ");
			StdOut.println();
		}
	}
	public static void main(String[] args) {
		test();
		
		
	}
}
