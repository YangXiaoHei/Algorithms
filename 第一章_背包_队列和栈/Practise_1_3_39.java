package 第一章_背包_队列和栈;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import edu.princeton.cs.algs4.*;
/*
 * 思路 :
 * 
 * 阅读并参考了 ArrayBlockingQueue 的实现
 * 
 */
public class Practise_1_3_39 {
	static class RingBuffer<T> {
		private int size, capacity, head, tail;
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
			    // 如果当前队列为空，那么让当前线程在 empty 条件上陷入等待
				while(isEmpty()) {
					StdOut.println("♣️Consumer is waiting ...");
					empty.await();
				}
				
				// 如果当前线程被唤醒，那么说明由元素可供出列
				T deq = items[head];
				head = (head + 1) % items.length;
				size--;
				
				// 出列了一个元素，那么如果由线程在 full 条件上陷入等待，我们将其唤醒
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
			    
			    // 如果入队时发现队列已经满了，那么让当前线程在 full 条件上陷入等待
				while(isFull()) {
					StdOut.println("♥️Producer is waiting ...");
					full.await();
				}
				
				// 如果当前线程被唤醒，说明队列中出现可供入队的空位，我们执行入队操作
				size++;
				items[tail] = item;
				tail = (tail + 1) % items.length;
				
				// 入队一次后，如果由在 empty 条件上陷入等待的线程，我们将其唤醒
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
