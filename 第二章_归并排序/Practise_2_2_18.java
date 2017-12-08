package 第二章_归并排序;

import edu.princeton.cs.algs4.*;

public class Practise_2_2_18 {
    static class Node<T extends Comparable<T>> {
        Node<T> next;
        T item;
        Node() {}
        Node (T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
        Node<T> insertAfter(T item) {
            Node<T> n = new Node<T>(item, next);
            next = n;
            return n;
        }
        Node<T> removeFirst() {
            item = null;
            return next;
        }
        void forwardPrint() {
            Node<T> cur = this;
            StdOut.print(cur.item + " ");
            while ((cur = cur.next) != null)
                StdOut.print(cur.item + " ");
            StdOut.println();
        }
        int forwardCount() {
            Node<T> cur = this;
            int count = 1;
            while ((cur = cur.next) != null)
                count++;
            return count;
        }
        static  Node<Integer> list(int N) {
            Node<Integer> header = new Node<Integer>(), tmp = header;
            for (int i = 0; i < N; i++)
                tmp = tmp.insertAfter(i);
            return header.next;
        }
    }
    public static <T extends Comparable<T>> Node<T> nextOfMid(Node<T> head) {
        Node<T> fast = head, slow = head;
        while (true) {
            fast = fast.next;
            if (fast == null) break;
            fast = fast.next;
            if (fast == null) break;
            slow = slow.next;
        }
        Node<T> next = slow.next;
        slow.next = null;
        return next;
    }
    public static <T extends Comparable<T>> Node<T> merge(Node<T> head) {
        if (head == null || head.next == null) return head;
        Node<T> head1 = head;
        Node<T> head2 = nextOfMid(head);
        head1 = merge(head1);
        head2 = merge(head2);
        return mergeShuffle(head1, head2);
    }
    public static boolean less(int count1, int count2) {
        return StdRandom.uniform() < (count1 * 1.0 / (count1 + count2));
    }
    public static <T extends Comparable<T>> Node<T> mergeShuffle(Node<T> list1, Node<T> list2) {
        int count1 = list1.forwardCount();
        int count2 = list2.forwardCount();
        Node<T> header = new Node<T>(), tmp = header;
        while (count1 != 0 || count2 != 0) 
            if      (count1 == 0)           { tmp.insertAfter(list2.item);  list2 = list2.removeFirst(); --count2; }
            else if (count2 == 0)           { tmp.insertAfter(list1.item);  list1 = list1.removeFirst(); --count1; }
            else if (less(count1, count2))  { tmp.insertAfter(list1.item);  list1 = list1.removeFirst(); --count1; }
            else                            { tmp.insertAfter(list2.item);  list2 = list2.removeFirst(); --count2; }
        return header.next;
    }
    public static void main(String[] args) {
        Node<Integer> list = Node.list(20);
        StdOut.println("========== 置乱前 ==============");
        list.forwardPrint();
        list = merge(list);
        StdOut.println("========== 置乱后 ==============");
        list.forwardPrint();
    }
    // output
    /*
     *  ========== 置乱前 ==============
        0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 
        ========== 置乱后 ==============
        5 0 6 11 14 17 16 18 19 13 2 10 3 12 9 4 8 7 1 15 
     */
    
}
