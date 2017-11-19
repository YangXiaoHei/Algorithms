package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.*;
import java.util.*;
/*
 * 思路 :
 * 
 * 在迭代器中拷贝一份数组副本，并执行随机打乱，然后迭代器实现在此打乱数组上的迭代操作 
 * 
 * 
 */
public class Practise_1_3_34 {
	@SuppressWarnings("unchecked")
	static class RandomBag<T> implements Iterable<T> {
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
		private void resize(int newsize) {
			T[] newItems = (T[])new Object[newsize];
			for(int i = 0; i < size; i++)
				newItems[i] = items[i];
			items = newItems;
		}
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
	// output
	/*
	 *	0 10 17 5 14 4 11 18 2 16 3 13 15 7 6 12 9 8 19 1 
		7 14 18 5 12 8 1 13 17 3 11 0 19 9 4 6 10 15 16 2 
		12 14 10 2 11 5 18 16 19 9 13 4 6 15 1 7 8 17 0 3 
		13 1 10 11 15 4 7 6 12 8 3 2 14 16 9 18 17 5 19 0 
		13 18 19 6 7 8 14 2 12 15 4 10 11 5 3 17 9 0 1 16 
		1 15 18 9 16 3 5 12 17 11 14 7 2 8 13 6 19 4 10 0 
		18 16 14 13 1 0 8 11 4 9 12 2 10 3 15 6 5 7 17 19 
		9 2 17 11 3 15 16 7 4 12 10 5 19 1 14 13 6 8 18 0 
		1 16 0 14 6 3 15 4 5 19 12 9 13 8 18 7 10 2 11 17 
		7 12 11 19 4 8 13 6 5 1 14 17 9 16 15 18 3 2 0 10 
	 */
}
