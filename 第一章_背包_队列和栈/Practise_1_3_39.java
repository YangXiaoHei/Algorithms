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
					StdOut.println("♣️Consumer is waiting ...");
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
					StdOut.println("♥️Producer is waiting ...");
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
					StdOut.println("️Consumer eats food " + food);
					TimeUnit.MILLISECONDS.sleep(StdRandom.uniform(300));
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
					TimeUnit.MILLISECONDS.sleep(StdRandom.uniform(300));
					buffer.enqueue(food);
					StdOut.println("Producer produced food " + food);
				}
			} catch (InterruptedException e) {
				StdOut.println("Producer was interrupted while making delicious food");
			}
		}
	}
	
	public static void main(String[] args) throws Exception  {
		RingBuffer<Food> buffer = new RingBuffer<Food>(2);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Consumer(buffer));
		exec.execute(new Producer(buffer));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
	// output :
	/*
	 * 	♣️Consumer is waiting ...
		Producer produced food food 0
		️Consumer eats food food 0
		Producer produced food food 1
		️Consumer eats food food 1
		Producer produced food food 2
		️Consumer eats food food 2
		♣️Consumer is waiting ...
		Producer produced food food 3
		️Consumer eats food food 3
		♣️Consumer is waiting ...
		Producer produced food food 4
		️Consumer eats food food 4
		♣️Consumer is waiting ...
		Producer produced food food 5
		️Consumer eats food food 5
		Producer produced food food 6
		Producer produced food food 7
		️Consumer eats food food 6
		Producer produced food food 8
		️Consumer eats food food 7
		Producer produced food food 9
		️Consumer eats food food 8
		Producer produced food food 10
		♥️Producer is waiting ...
		️Consumer eats food food 9
		Producer produced food food 11
		♥️Producer is waiting ...
		️Consumer eats food food 10
		Producer produced food food 12
		♥️Producer is waiting ...
		️Consumer eats food food 11
		Producer produced food food 13
		️Consumer eats food food 12
		️Consumer eats food food 13
		♣️Consumer is waiting ...
		Producer produced food food 14
		️Consumer eats food food 14
		Producer produced food food 15
		Producer produced food food 16
		️Consumer eats food food 15
		Producer produced food food 17
		♥️Producer is waiting ...
		️Consumer eats food food 16
		Producer produced food food 18
		️Consumer eats food food 17
		Producer produced food food 19
		️Consumer eats food food 18
		️Consumer eats food food 19
		Producer produced food food 20
		Producer produced food food 21
		️Consumer eats food food 20
		Producer produced food food 22
		♥️Producer is waiting ...
		️Consumer eats food food 21
		Producer produced food food 23
		️Consumer eats food food 22
		Producer produced food food 24
		️Consumer eats food food 23
		Producer produced food food 25
		♥️Producer is waiting ...
		️Consumer eats food food 24
		Producer produced food food 26
		♥️Producer is waiting ...
		️Consumer eats food food 25
		Producer produced food food 27
		♥️Producer is waiting ...
		️Consumer eats food food 26
		Producer produced food food 28
		️Consumer eats food food 27
		Consumer was interrupted while eating food 
		Producer was interrupted while making delicious food
	 */
}
