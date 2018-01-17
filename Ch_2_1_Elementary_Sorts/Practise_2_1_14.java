package Ch_2_1_Elementary_Sorts;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_1_14 {
    static class Wrapper {
        int top, topNext;
        Wrapper(int to, int topN) { top = to; topNext = topN; }
        boolean isReverseOrder() { return top > topNext; }
        public String toString() {
            return String.format("{ %d %d }", top, topNext);
        }
    }
    static class Queue {
        private int[] items = new int[2];
        private int head, tail, size;
        boolean isEmpty() { return size == 0; }
        int size() { return size; }
        Queue() {}
        Queue(int[] org) {
            for (int i : org)
                enqueue(i);
        }
        void resize(int newSize) {
            int[] newItems = new int[newSize];
            int cur = head, k = 0;
            do {
                newItems[k++] = items[cur];
                cur = (cur + 1) % items.length;
            } while (cur != tail);
            items = newItems;
            head = 0;
            tail = size;
        } 
        void enqueue(int item) {
            if (size == items.length)
                resize(2 * size);
            size++;
            items[tail] = item;
            tail = (tail + 1) % items.length;
        }
        int dequeue() {
            if (size == 0)
                throw new RuntimeException("dequeue from a empty queue!");
            --size;
            int del = items[head++];
            if (head == items.length) head = 0;
            if (size > 0 && size == items.length / 4)
                resize(items.length / 2);
            return del;
        }
        boolean hasReversePair() {
            int cur = head, pre = head;
            while (true) {
                cur = (cur + 1) % items.length;
                if (cur == tail) return false;
                if (items[cur] < items[pre]) return true;
                pre = cur;
            }
        }
        void print() {
            int cur = head;
            do {
                StdOut.print(items[cur] + " ");
                cur = (cur + 1) % items.length;
            } while (cur != tail);
            StdOut.println();
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i : items)
                sb.append(i + " ");
            return sb.toString();
        }
        /*
         * 查看顶部两个元素
         */
        Wrapper peekTopTwo() {
            if (size < 2)
                throw new RuntimeException("queue items less than required");
            return new Wrapper(items[head], items[(head + 1) % items.length]);
        }
        /*
         * 交换顶部两个元素
         */
        Wrapper exchangeTopTwo() {
            if (size < 2)
                throw new RuntimeException("queue items less than required");
            int t = items[head];
            int nextIndex = (head + 1) % items.length;
            items[head] = items[nextIndex];
            items[nextIndex] = t;
            return new Wrapper(items[head], items[nextIndex]);
        }
        /*
         * 把顶部元素弄到底部
         */
        void makeHeadEnqueueAgain() {
            enqueue(dequeue());
        }
    }
    /*
     * 出队排序
     */
    public static void dequeueSort(Queue queue) {
        int i = queue.size();
       while (queue.hasReversePair()) {
           while (--i > 0) {
               if (queue.peekTopTwo().isReverseOrder())
                   queue.exchangeTopTwo();
               queue.makeHeadEnqueueAgain();  
           }
           queue.makeHeadEnqueueAgain();
           i = queue.size();
       }
    }
    public static void main(String[] args) {
        int[] arr = intsVrgWithEachAmount(13, 1, 2, 3, 4);
        Queue queue = new Queue(arr);
        StdOut.println("================ 排序前 ================");
        queue.print();
        dequeueSort(queue);
        StdOut.println("================ 排序后 ================");
        queue.print();
    }
    // output
    /*
     * 
     *  ================ 排序前 ================
        3 3 2 2 2 1 1 4 4 1 3 4 2 3 2 1 2 2 1 4 3 4 2 3 3 3 4 1 4 1 1 4 1 2 4 2 3 3 4 2 2 3 3 4 2 1 4 3 1 4 1 1 
        ================ 排序后 ================
        1 1 1 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 3 3 3 3 3 3 4 4 4 4 4 4 4 4 4 4 4 4 4 
     * 
     */
}
