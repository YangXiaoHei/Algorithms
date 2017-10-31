package 第一章_背包_队列和栈;

import java.io.*;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_3_43 {
	static class Queue<T> {
		private T[] items = (T[])new Object[1];
		private int head;
		private int tail;
		private int size;
		void resize(int newsize) {
			T[] newItems = (T[])new Object[newsize];
			int cur = head, k = 0;
			do {
				newItems[k++] = items[cur++];
				if (cur == items.length) 
					cur = 0;
			} while(cur != tail);
			head = 0;
			tail = size;
			items = newItems;
		}
		int size() { return size; };
		void enqueue(T item) {
			if (size == items.length)
				resize(2 * size);
			size++;
			items[tail++] = item;
			if (tail == items.length) 
				tail = 0;
		}
		T dequeue() {
			if (isEmpty())
				throw new RuntimeException("dequeue from a empty queue");
			size--;
			T del = items[head];
			items[head++] = null;
			if (head == items.length)
				head = 0;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			return del;
		}
		T peek() {
			return items[head];
		}
		boolean isEmpty() { return size == 0; }
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			sb.append("|");
			for (T item : items)
				sb.append(String.format(" %2s |", item == null ? " " : item));
			return sb.toString();
		}
	}
	
	
	/*
	 * recursively print
	 */
	public static void printByRecursivion(File file) {
		StdOut.println("====== Recursivion ======");
		int depth = 0;
		print(file, depth);
	}
	public static void print(File file, int traceDepth) {
		if (!file.isDirectory()) {
			StdOut.println(fileString(file, traceDepth));
			return;
		}
		StdOut.println(fileString(file, traceDepth));
		for(File f : file.listFiles()) {
			traceDepth++;
			print(f, traceDepth);
			traceDepth--;
		}
	}
	public static String fileString(File file, int depth) {
		String str = "";
		for(int i = 0; i < depth; i++)
			str += "     ";
		return  str + "- " + file.getName();
	}
	
	/*
	 * implemented by queue
	 */
	public static void printByQueue(File file) {
		StdOut.println("\n\n\n====== Queue ======");
		Queue<File> queue = new Queue<File>();
		queue.enqueue(file);
		while(!queue.isEmpty()) {
			File f = queue.dequeue();
			if (!f.isDirectory()) {				
				StdOut.println(f.getName());
			} else {
				StdOut.println(f.getName());
				for(File ff : f.listFiles()) {
					queue.enqueue(ff);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		 File file = new File("/Users/bot/Desktop/ThinkingInJava");
		 printByRecursivion(file);
		 printByQueue(file);
	}
	
}
