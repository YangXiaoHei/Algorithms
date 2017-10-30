package 第一章_背包_队列和栈;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_3_39 {
	static class RingBuffer<T> {
		private int size;
		private int capacity;
		private int head;
		private int tail;
		private T[] items;
		private ReentrantLock lock;
		private Condition empty;
		private Condition full;
		@SuppressWarnings("unchecked")
		RingBuffer(int capacity) {
			this.capacity = capacity;
			items = (T[])new Object[capacity];
			lock = new ReentrantLock();
			empty = lock.newCondition();
			full = lock.newCondition();
		}
		T dequeue() throws InterruptedException {
			lock.lockInterruptibly();
			try {
				while(isEmpty()) {
					StdOut.println("Consumer is waiting ...");
					empty.await();
				}
				T deq = items[head];
				head = (head + 1) % items.length;
				size--;
				full.signalAll();
				return deq;
			} finally {
				lock.unlock();
			}
		}
		void enqueue(T item) throws InterruptedException  {
			if (item == null)
				throw new NullPointerException();
			lock.lockInterruptibly();
			try {
				while(isFull()) {
					StdOut.println("Producer is waiting ...");
					full.await();
				}
				size++;
				items[tail] = item;
				tail = (tail + 1) % items.length;
				empty.signalAll();
			} finally {
				lock.unlock();
			}
		}
		boolean isEmpty() { return size == 0; }
		boolean isFull() { return size == capacity; }
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("|");
			for(int i = 0; i < items.length; i++)
				sb.append(String.format(" %3s |", items[i] == null ? " " : items[i]));
			return sb.toString();
		}
	}
	
	static class Food {
		private static int counter = 0;
		private final int id = counter++;
		public String toString() { return "food " + id; }
	}
	
	static class Consumer implements Runnable {
		private RingBuffer<Food> buffer;
		public Consumer(RingBuffer<Food> buffer) { this.buffer = buffer; }
		public void run() {
			try {
				while(!Thread.interrupted()) {
					Food food = buffer.dequeue();
					StdOut.println("Consumer eats food " + food);
					TimeUnit.SECONDS.sleep(1);
				}
			} catch (InterruptedException e) {
				StdOut.println("Consumer was interrupted while eating food ");
			}
		}
	}
	static class Producer implements Runnable {
		private RingBuffer<Food> buffer;
		public Producer(RingBuffer<Food> buffer) { this.buffer = buffer; }
		public void run() {
			try {
				while(!Thread.interrupted()) {
					Food food = new Food();
					TimeUnit.SECONDS.sleep(2);
					buffer.enqueue(food);
					StdOut.println("Producer produced food " + food);
				}
			} catch (InterruptedException e) {
				StdOut.println("Producer was interrupted while making delicious food");
			}
		}
	}
	
	public static void main(String[] args) throws Exception  {
		RingBuffer<Food> buffer = new RingBuffer<Food>(20);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Consumer(buffer));
		exec.execute(new Producer(buffer));
		TimeUnit.SECONDS.sleep(15);
		exec.shutdownNow();
	}
}
