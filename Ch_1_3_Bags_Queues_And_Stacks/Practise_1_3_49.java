package Ch_1_3_Bags_Queues_And_Stacks;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_49 {
	/*
	 * Two stack implementation
	 */
	static class TwoStackQueue<T> {
	    @SuppressWarnings("unchecked")
	    static class Stack<T> {
	        private T[] items = (T[])new Object[1];
	        private int size;
	        public boolean isEmpty() { return size == 0; }
	        public int size() { return size; }
	        public void push(T item) {
	            if (size == items.length)
	                resize(items.length * 2);
	            items[size++] = item;
	        }
	        public T pop() {
	            T del = items[--size];
	            items[size] = null;
	            if (size > 0 && size == items.length / 4)
	                resize(items.length / 2);
	            return del;
	        }
	        public void resize(int newsize) {
	            T[] newItems = (T[])new Object[newsize];
	            for (int i = 0; i < size; i++)
	                newItems[i] = items[i];
	            items = newItems;
	        }
	        public String toString() {
	            if (isEmpty()) return "[empty]";
	            StringBuilder sb = new StringBuilder();
	            sb.append("|");
	            for(int i = 0; i < items.length; i++)
	                sb.append(String.format(" %2s |", items[i] == null ? " " : items[i]));
	            return sb.toString();
	        }
	        public static void stackTest() {
	            Stack<Integer> stack = new Stack<Integer>();
	            for (int i = 0; i < 10; i++)
	                stack.push(i);
	            for (int i = 0; i < 6; i++)
	                stack.pop();
	            StdOut.println(stack);
	        }
	    }
		private Stack<T> stack1 = new Stack<T>();
		private Stack<T> stack2 = new Stack<T>();
		public int size() { return stack1.size() + stack2.size(); }
		public boolean isEmpty() { return stack1.isEmpty() && stack2.isEmpty(); }
		public void enqueue(T item) {
			if (stack1.isEmpty() && stack2.isEmpty()) {
				stack1.push(item);
			} else if (stack1.isEmpty()) {
				while (!stack2.isEmpty())
					stack1.push(stack2.pop());
				stack1.push(item);
			} else {
				stack1.push(item);
			}
		}
		public T dequeue() {
			if (stack1.isEmpty() && stack2.isEmpty()) {
				throw new RuntimeException("dequeue from a empty queue");
			} else if (stack1.isEmpty()) {
				return stack2.pop();
			} else {
				while (stack1.size() > 1)
					stack2.push(stack1.pop());
				return stack1.pop();
			}
		}
		public static void queueTest() {
		    StdOut.println("\n========= 两个栈实现一个队列 ============");
			TwoStackQueue<Integer> queue = new TwoStackQueue<Integer>();
			for (int i = 0; i < 10; i++)
				queue.enqueue(i);
			for (int i = 0; i < 5; i++)
				StdOut.print(queue.dequeue() + " ");
			for (int i = 10; i < 20; i++)
				queue.enqueue(i);
			while (!queue.isEmpty())
				StdOut.print(queue.dequeue() + " ");
		}
	}
	/*
	 * six stack implementation
	 * 
	 */
	static class SixStackQueue<T> {
	    static class Stack<T> {
	        private class Node {
	            Node next;
	            T item;
	            Node (T item, Node next) { this.next = next; this.item = item; }
	        }
	        private int size = 0;
	        private String identifier;
	        private Node top = null;
	        boolean isEmpty() { return size == 0; }
	        int size() { return size; }
	        String id() { return identifier; }
	        Stack(String id) { identifier = id; }
	        void push(T item) {
	            size++;
	            top = new Node(item, top);
	        }
	        T pop() {
	            size--;
	            T item = top.item;
	            top = top.next;
	            return item;
	        }
	        Stack<T> shallowCopy(String id) {
	            Stack<T> copy = new Stack<T>(id);
	            copy.top = top;
	            copy.size = size;
	            return copy;
	        }
	        String description() {
	            return description(top);
	        }
	        private String description(Node first) {
	            if (first == null) return "";
	            return description(first.next) + " " + first.item;
	        }
	        public String toString() {
	            return "\n" + identifier + " : [" + 
	                    (!isEmpty() ? description() : "")
	                    + " ]\n";
	        }
	    }
	    private boolean isReversing; // 是否正处于翻转操作中
	    private int reverseCount; // 队头翻转后需要再次翻转的数目
	    private Stack<T> Head = new Stack<T>("H"); // 队头  
	    private Stack<T> Tail = new Stack<T>("T"); // 队尾
	    private Stack<T> TailReversed = new Stack<T>("H'"); // 提供对队尾的翻转操作
	    private Stack<T> TailWhenReverse = new Stack<T>("T'"); // 提供在对队尾进行翻转时的入队操作
	    private Stack<T> HeadReversed = new Stack<T>("Hr"); // 提供对队头的翻转操作
	    private Stack<T> HeadWhenReverse = new Stack<T>("h"); // 提供在队头进行翻转时的出队操作
	    void inspect(String id) {
	        if      (id.equals("H"))
	            StdOut.println(Head);
	        else if (id.equals("T"))
	            StdOut.println(Tail);
	        else if (id.equals("H'"))
                StdOut.println(TailReversed);
	        else if (id.equals("T'"))
                StdOut.println(TailWhenReverse);
	        else if (id.equals("Hr"))
                StdOut.println(HeadReversed);
	        else if (id.equals("h"))
                StdOut.println(HeadWhenReverse);
	    }
	    void inspect() {
	        inspect("H"); inspect("T");
	        inspect("H'"); inspect("T'");
	        inspect("Hr"); inspect("h");
	    }
	    boolean isEmpty() {
	        if (isReversing)
	            return HeadWhenReverse.isEmpty();
	        return Head.isEmpty();
	    }
	    void enqueue(T item) {
	        if (isReversing) {
	            TailWhenReverse.push(item);
	            amortizedOp();
	            amortizedOp();
	        } else {
	            if (Head.size() > Tail.size()) 
	                Tail.push(item);
	            else {
	                isReversing = true;
	                Tail.push(item);
	                HeadWhenReverse = Head.shallowCopy("h");
	                amortizedOp();
	                amortizedOp();
	            }
	        }
	    }
	    T dequeue() {
	        if (isReversing) {
	           T item = HeadWhenReverse.pop();
	           reverseCount--;
	           amortizedOp();
	           amortizedOp();
	           return item;
	        }
	        
	        if (Head.size() > Tail.size()) 
	            return Head.pop();
	        else {
	            isReversing = true;
	            T item = Head.pop();
	            HeadWhenReverse = Head.shallowCopy("h");
	            amortizedOp();
	            amortizedOp();
	            return item;
	        }
	    }
	    void amortizedOp() {
	        if (!isReversing) return;
	        
	        if (!Head.isEmpty() && !Tail.isEmpty()) {
	            TailReversed.push(Tail.pop());
	            HeadReversed.push(Head.pop());
	            reverseCount++;
	        } else if (Head.isEmpty() && !Tail.isEmpty()) {
	            TailReversed.push(Tail.pop());
	        } else if (Head.isEmpty() && Tail.isEmpty()) {
	            if (reverseCount > 1) {
	                reverseCount--;
	                TailReversed.push(HeadReversed.pop());
	            } else if (reverseCount == 1) {
	                isReversing = false;
	                reverseCount--;
	                TailReversed.push(HeadReversed.pop());
	                Head = TailReversed.shallowCopy("H");
	                Tail = TailWhenReverse.shallowCopy("T");
	                HeadReversed = new Stack<T>("Hr");
	                TailReversed = new Stack<T>("H'");
	                HeadWhenReverse = new Stack<T>("h");
	                TailWhenReverse = new Stack<T>("T'");
	                
	            } else {
	                isReversing = false;
	                Head = TailReversed.shallowCopy("H");
                    Tail = TailWhenReverse.shallowCopy("T");
                    HeadReversed = new Stack<T>("Hr");
                    TailReversed = new Stack<T>("H'");
                    HeadWhenReverse = new Stack<T>("h");
                    TailWhenReverse = new Stack<T>("T'");
	            }
	        }
	    }
	    static void queueTest() {
	        StdOut.println("\n\n\n========= 六个栈实现一个 O(1) 队列 ============");
	        SixStackQueue<Integer> queue = new SixStackQueue<Integer>();
	        for (int i = 0; i < 15; i++)
	            queue.enqueue(i);
	        queue.inspect();
	        while (!queue.isEmpty()) {
	            StdOut.println(queue.dequeue());
	        }
        }
	}
	public static void main(String[] args) {
		TwoStackQueue.queueTest();
		SixStackQueue.queueTest();
	}
	// output
	/*
	 * 	
        ========= 两个栈实现一个队列 ============
        0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 
        
        
        ========= 六个栈实现一个 O(1) 队列 ============
        
        H : [ 6 5 4 3 2 ]
        
        
        T : [ 7 8 9 10 11 12 ]
        
        
        H' : [ 14 13 ]
        
        
        T' : [ ]
        
        
        Hr : [ 0 1 ]
        
        
        h : [ 6 5 4 3 2 1 0 ]
        
        0
        1
        2
        3
        4
        5
        6
        7
        8
        9
        10
        11
        12
        13
        14
	 */
}
