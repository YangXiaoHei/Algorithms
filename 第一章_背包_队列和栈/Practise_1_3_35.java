package 第一章_背包_队列和栈;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_3_35 {
	static class RandomQueue<T> {
		@SuppressWarnings("unchecked")
		private T[] items = (T[])new Object[1];
		private int size;
		private int head;
		private int tail;
		RandomQueue() {}
		boolean isEmpty() { return size == 0; }
		void enqueue(T item) {
			if (size == items.length)
				resize(2 * size);
			size++;
			items[tail] = item;
			tail = (tail + 1) % items.length;
			StdOut.println(this);
		}
		T dequeue() {
			if (isEmpty())
				throw new RuntimeException("dequeue from a empty queue");
			int r = randomIndex();
			size--;
			T tmp = items[head];
			items[head] = items[r];
			items[r] = tmp;
			T del = items[head];
			items[head] = null;
			head = (head + 1) % items.length;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			StdOut.println(this + " dequeue -> " + del);
			return del;
		}
		private int randomIndex() {
			if (head < tail)
				return head + StdRandom.uniform(tail - head);
			else {
				int[] indexs = new int[size];
				int k = 0, cur = head;
				do {
					indexs[k++] = cur;
					cur = (cur + 1) % items.length;
				} while(cur != tail);
				return indexs[StdRandom.uniform(size)];
			}
		}
		T sample() {
			return items[randomIndex()];
		}
		@SuppressWarnings("unchecked")
		private void resize(int newsize) {
			T[] newItems = (T[])new Object[newsize];
			int cur = head, k = 0;
			do {
				newItems[k++] = items[cur];
				cur = (cur + 1) % items.length;
			} while (cur != tail);
			head = 0;
			tail = size;
			items = newItems;
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (isEmpty()) return "[empty]";
			sb.append("|");
			for(int i = 0; i < items.length; i++)
				sb.append(String.format(" %3s |", items[i] == null ? " " : items[i]));
			return sb.toString();
		}
	}
	/*
	 * basic operations test
	 */
	public static void basicOperationTest() {
		RandomQueue<Integer> queue = new RandomQueue<Integer>();
		for(int i = 0; i < 10; i++)
			queue.enqueue(i);
		for(int i = 0; i < 9; i++)
			queue.dequeue();
		for(int i = 11; i < 15; i++)
			queue.enqueue(i);
		for(int i = 0; i < 2; i++)
			queue.dequeue();
		for(int i = 0; i < 4; i++)
			queue.enqueue(i);
		for(int i = 0; i < 7; i++)
			queue.dequeue();
	}
	static String[] hearts = {
			"♥A️", "♥2️", "♥3️", "♥4️", "♥5", "♥6", "♥7️", "♥8️", "♥9", "♥10", "♥J️", "♥Q️", "♥K️"
	};
	static String[] spades = {
			"♠A️",  "♠2️", "♠3️", "♠4️", "♠5", "♠6", "♠7️", "♠8️", "♠9", "♠10", "♠J️", "♠Q️", "♠K️"
	};
	static String[] clubs = {
			"♣A️",  "♣2️", "♣3️", "♣4️", "♣5", "♣6", "♣7️", "♣8️", "♣9", "♣10", "♣J️", "♣Q️", "♣K️"
	};
	static String[] diamonds = {
			"♦A", "♦2️", "♦3️", "♦4️", "♦5", "♦6", "♦7️", "♦8️", "♦9", "♦10", "♦J️", "♦Q️", "♦K️"
	};
	static String[][] cards = {
			hearts,
			spades,
			clubs,
			diamonds
	};
	static class StackOfPlayingCards extends RandomQueue<String> {
		StackOfPlayingCards() {
			for(int i = 0; i < cards.length; i++)
				for(int j = 0; j < cards[i].length; j++)
					enqueue(cards[i][j]);
		}
		String deal() {
			try {
				return dequeue();
			} catch (Exception e) {
				throw new RuntimeException("no cards~");
			}
		}
	}
	static class Person {
		private static int counter = 0;
		private final int id = counter++;
		private int size;
		String[] cards = new String[13];
		public void get(String card) { cards[size++] = card; }
		public String toString() {
			return "Person " + id + " has cards : " + Arrays.toString(cards);
		}
	}
	public static void dealOperationTest() {
		Person p1 = new Person(),
				   p2 = new Person(),
				   p3 = new Person(),
				   p4 = new Person();
			StackOfPlayingCards cards = new StackOfPlayingCards();
			for(int i = 0; i < 13; i++)
				p1.get(cards.deal());
			for(int i = 0; i < 13; i++)
				p2.get(cards.deal());
			for(int i = 0; i < 13; i++)
				p3.get(cards.deal());
			for(int i = 0; i < 13; i++)
				p4.get(cards.deal());
			StdOut.println(p1);
			StdOut.println(p2);
			StdOut.println(p3);
			StdOut.println(p4);
	}
	
	public static void main(String[] args) {
		basicOperationTest();
		dealOperationTest();
	}
	// output
	/*
	 * 	|   0 |
		|   0 |   1 |
		|   0 |   1 |   2 |     |
		|   0 |   1 |   2 |   3 |
		|   0 |   1 |   2 |   3 |   4 |     |     |     |
		|   0 |   1 |   2 |   3 |   4 |   5 |     |     |
		|   0 |   1 |   2 |   3 |   4 |   5 |   6 |     |
		|   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |
		|   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |     |     |     |     |     |     |     |
		|   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |     |     |     |     |     |     |
		|     |   1 |   0 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |     |     |     |     |     |     | dequeue -> 2
		|     |     |   0 |   3 |   4 |   5 |   6 |   7 |   1 |   9 |     |     |     |     |     |     | dequeue -> 8
		|     |     |     |   3 |   4 |   5 |   6 |   0 |   1 |   9 |     |     |     |     |     |     | dequeue -> 7
		|     |     |     |     |   4 |   3 |   6 |   0 |   1 |   9 |     |     |     |     |     |     | dequeue -> 5
		|     |     |     |     |     |   3 |   6 |   0 |   4 |   9 |     |     |     |     |     |     | dequeue -> 1
		|   6 |   0 |   4 |   9 |     |     |     |     | dequeue -> 3
		|     |   0 |   4 |   9 |     |     |     |     | dequeue -> 6
		|   4 |   9 |     |     | dequeue -> 0
		|   9 |     | dequeue -> 4
		|   9 |  11 |
		|   9 |  11 |  12 |     |
		|   9 |  11 |  12 |  13 |
		|   9 |  11 |  12 |  13 |  14 |     |     |     |
		|     |   9 |  12 |  13 |  14 |     |     |     | dequeue -> 11
		|     |     |  12 |  13 |   9 |     |     |     | dequeue -> 14
		|     |     |  12 |  13 |   9 |   0 |     |     |
		|     |     |  12 |  13 |   9 |   0 |   1 |     |
		|     |     |  12 |  13 |   9 |   0 |   1 |   2 |
		|   3 |     |  12 |  13 |   9 |   0 |   1 |   2 |
		|  12 |     |     |  13 |   9 |   0 |   1 |   2 | dequeue -> 3
		|  13 |     |     |     |   9 |   0 |   1 |   2 | dequeue -> 12
		|  13 |     |     |     |     |   0 |   1 |   2 | dequeue -> 9
		|  13 |     |     |     |     |     |   1 |   0 | dequeue -> 2
		|   0 |  13 |     |     | dequeue -> 1
		|   0 |     | dequeue -> 13
		[empty] dequeue -> 0
	 */
	// output 
	/*
	 * ....
	 * 	Person 0 has cards : [️♦A, ♥9, ♥Q️, ♥10, ♥8️, ♦3️, ♦6, ♠10, ♣A️, ♠7️, ♣7️, ♣Q️, ♣3️]
		Person 1 has cards : [♣8️, ♣K️, ♥4️, ♣4️, ♠2️, ♠8️, ♠Q️, ♥5, ♦5, ♥K️, ♦2️, ♥6, ♣2️]
		Person 2 has cards : [♦K️, ♠6, ♣J️, ♠J️, ♠A️, ♥J️, ♠5, ♠3️, ♥3️, ♦4️, ♦8️, ♣10, ♣5]
		Person 3 has cards : [♦7️, ♦9, ♣9, ♠9, ♦Q️, ♣6, ♥A️, ♥7️, ♦J️, ♦10, ♠4️, ♠K️, ♥2️]
	 */
}
