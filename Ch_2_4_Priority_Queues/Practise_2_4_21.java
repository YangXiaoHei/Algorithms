package Ch_2_4_Priority_Queues;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_4_21 {
    /*
     * 使用优先队列实现栈
     * 
     * 思路 ：给每个入队元素赋予一个优先级，后入队的优先级高，先入队的优先级低，用最大堆实现
     */
    static class MaxPQImplementationOfStack <Key>  {
         class InnerWrapper <IKey> {
            IKey key; 
            int priority; // 用来控制插入先后顺序的优先级
        }
        private InnerWrapper<Key>[] ws;
        private int size;
        @SuppressWarnings("unchecked")
        public MaxPQImplementationOfStack(int N) {
            ws = (InnerWrapper<Key>[])new InnerWrapper[N + 1];
        }
        public boolean isEmpty() { return size == 0; }
        public void insert(Key key) {
            if (size == ws.length - 1)
                throw new IllegalArgumentException("priority queue overflow!");
            InnerWrapper<Key> w = new InnerWrapper<Key>();
            w.key = key;
            w.priority = size++; // 后进入的优先级高
            ws[size] = w;
            int k = size;
            while (k > 1 && ws[k >> 1].priority < ws[k].priority) {
                InnerWrapper<Key> t = ws[k >> 1]; ws[k >> 1] = ws[k]; ws[k] = t;
                k >>= 1;
            }
        }
        public Key delMax() {
            if (size == 0) 
                throw new NoSuchElementException("priority queue underflow");
            InnerWrapper<Key> maxWrapper = ws[1];
            ws[1] = ws[size--];
            int k = 1;
            while ((k << 1) <= size) {
                int j = k << 1;
                if (ws[j].priority < ws[j + 1].priority) j++;
                if (ws[k].priority >= ws[j].priority) break;
                InnerWrapper<Key> t = ws[k]; ws[k] = ws[j]; ws[j] = t;
                k = j;
            }
            ws[size + 1] = null;
            return maxWrapper.key;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ws.length; i++)
                if (ws[i] != null)
                    sb.append(String.format("{ %s p : %d }, ",
                            ws[i].key.toString(), ws[i].priority));
                else
                    sb.append("{ }, ");
            return sb.toString();
        }
    }
    /*
     * 使用优先队列实现队列
     * 
     * 思路 ：给每个入队元素赋予一个优先级，后入队的优先级高，先入队的优先级低，然后用最小堆实现
     */
    static class MinPQImplementationOfQueue <Key>  {
        class InnerWrapper <IKey> {
           IKey key; 
           int priority; // 用来控制插入先后顺序的优先级
       }
       private InnerWrapper<Key>[] ws;
       private int size;
       @SuppressWarnings("unchecked")
       public MinPQImplementationOfQueue(int N) {
           ws = (InnerWrapper<Key>[])new InnerWrapper[N + 1];
       }
       public boolean isEmpty() { return size == 0; }
       public void insert(Key key) {
           if (size == ws.length - 1)
               throw new IllegalArgumentException("priority queue overflow!");
           InnerWrapper<Key> w = new InnerWrapper<Key>();
           w.key = key;
           w.priority = size++; // 后进入的优先级高
           ws[size] = w;
           int k = size;
           while (k > 1 && ws[k >> 1].priority > ws[k].priority) {
               InnerWrapper<Key> t = ws[k >> 1]; ws[k >> 1] = ws[k]; ws[k] = t;
               k >>= 1;
           }
       }
       public Key delMax() {
           if (size == 0) 
               throw new NoSuchElementException("priority queue underflow");
           InnerWrapper<Key> maxWrapper = ws[1];
           ws[1] = ws[size--];
           int k = 1;
           while ((k << 1) <= size) {
               int j = k << 1;
               if (ws[j].priority > ws[j + 1].priority) j++;
               if (ws[k].priority <= ws[j].priority) break;
               InnerWrapper<Key> t = ws[k]; ws[k] = ws[j]; ws[j] = t;
               k = j;
           }
           ws[size + 1] = null;
           return maxWrapper.key;
       }
       public String toString() {
           StringBuilder sb = new StringBuilder();
           for (int i = 0; i < ws.length; i++)
               if (ws[i] != null)
                   sb.append(String.format("{ %s p : %d }, ",
                           ws[i].key.toString(), ws[i].priority));
               else
                   sb.append("{ }, ");
           return sb.toString();
       }
   }
    public static void main(String[] args) {
        /*
         * 栈测试
         * 
         * 入栈顺序 10 9 8 7 6 5 4 3 2 1 
         * 对应的出栈顺序应该是 1 2 3 4 5 6 7 8 9 10
         */
        MaxPQImplementationOfStack<Integer> pq = new MaxPQImplementationOfStack<Integer>(10);
        for (int i = 10; i > 0; i--)
            pq.insert(i);
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
        
        StdOut.println("\n\n");
        
        /*
         * 队列测试
         * 
         * 入队顺序 10 9 8 7 6 5 4 3 2 1
         * 对应的出队顺序应该是 10 9 8 7 6 5 4 3 2 1
         */
        MinPQImplementationOfQueue<Integer> pq1 = new MinPQImplementationOfQueue<Integer>(10);
        for (int i = 10; i > 0; i--)
            pq1.insert(i);
        while (!pq1.isEmpty())
            StdOut.println(pq1.delMax());
    }
    // output
    /*
     *  1
        2
        3
        4
        5
        6
        7
        8
        9
        10
        
        
        
        10
        9
        8
        7
        6
        5
        4
        3
        2
        1
     */
}
