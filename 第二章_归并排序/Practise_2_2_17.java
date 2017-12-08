package 第二章_归并排序;

import edu.princeton.cs.algs4.*;

public class Practise_2_2_17 {
    static class ONode<T extends Comparable<T>> {
        ONode<T> next;
        T item;
        ONode () {}
        ONode (T item, ONode<T> next) {
            this.item = item;
            this.next = next;
        }
        ONode<T> insertAfter(T item) {
            ONode<T> n = new ONode<T>(item, next);
            next = n;
            return n;
        }
        ONode<T> forwardSearch(T item) {
            ONode<T> tnext = this;
            if (this.item.compareTo(item) == 0) return this;
            while ((tnext = tnext.next) != null)
                if (tnext.item.compareTo(item) == 0) return tnext;
            return null;
        }
        void forwardPrint() {
            ONode<T> tnext = this;
            StdOut.print(tnext.item + " ");
            while ((tnext = tnext.next) != null)
                StdOut.print(tnext.item + " ");
            StdOut.println();
        }
        ONode<T> removeFirst() {
            item = null;
            return next;
        }
        boolean less(ONode<T> other) {
            return item.compareTo(other.item) < 0;
        }
        public static ONode<Integer> list(int N) {
            ONode<Integer> first = new ONode<Integer>(StdRandom.uniform(N * 10), null);
            ONode<Integer> tmp = first;
            while (--N > 0) 
                tmp = tmp.insertAfter(StdRandom.uniform(N * 10));
            return first;
        }
        public static ONode<Integer> list(int lo, int hi) {
            ONode<Integer> header = new ONode<Integer>();
            ONode<Integer> tmp = header;
            for (int i = lo; i <= hi; i++)
                tmp = tmp.insertAfter(i);
            return header.next;
        }
    }
    public static <T extends Comparable<T>> ONode<T> mid(ONode<T> list) {
        ONode<T> slow = list, fast = list;
        while (true) {
            fast = fast.next;
            if (fast == null) break;
            fast = fast.next;
            if (fast == null) break;
            slow = slow.next;
        }
        ONode<T> next = slow.next; 
        slow.next = null;
        return next;
    }
    public static <T extends Comparable<T>> ONode<T> merge(ONode<T> list) {
        if (list.next == null || list == null) return list;
        ONode<T> head1 = list;
        ONode<T> head2 = mid(list);
        head1 = merge(head1);
        head2 = merge(head2);
        return mergeSort(head1, head2); 
    }
    public static <T extends Comparable<T>> ONode<T> mergeSort(ONode<T> list1, ONode<T> list2) {
        ONode<T> header = new ONode<T>();
        ONode<T> tmp = header;
        while (list1 != null || list2 != null) {
            if      (list1 == null)     { tmp = tmp.insertAfter(list2.item); list2 = list2.removeFirst(); }
            else if (list2 == null)     { tmp = tmp.insertAfter(list1.item); list1 = list1.removeFirst(); }
            else if (list1.less(list2)) { tmp = tmp.insertAfter(list1.item); list1 = list1.removeFirst(); }
            else                        { tmp = tmp.insertAfter(list2.item); list2 = list2.removeFirst(); }
        }
        return header.next;
    }
    public static void main(String[] args) {
        ONode<Integer> list = ONode.list(20);
        StdOut.println("========== 排序前 ==============");
        list.forwardPrint();
        StdOut.println("========== 排序后 ==============");
        list = merge(list);
        list.forwardPrint();
    }
    // output
    /*
     * ========== 排序前 ==============
        35 172 97 75 153 83 85 4 59 78 3 1 59 34 34 6 39 3 8 8 
        ========== 排序后 ==============
        1 3 3 4 6 8 8 34 34 35 39 59 59 75 78 83 85 97 153 172 
     */
}
