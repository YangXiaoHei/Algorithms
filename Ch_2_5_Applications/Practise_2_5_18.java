package Ch_2_5_Applications;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_5_18 {
    static class Wrapper {
        Comparable key;
        int originalIndex;
        public Wrapper(Comparable key, int index) {
            this.key = key;
            this.originalIndex = index;
        }
        public boolean keyEquals(Wrapper w) { return key.equals(w.key); }
        public String toString() { return String.format("{ %s, %d }", key, originalIndex); }
        public static Wrapper[] ws(Comparable[] a) {
            Wrapper[] w = new Wrapper[a.length];
            for (int i = 0; i < a.length; i++)
                w[i] = new Wrapper(a[i], i);
            return w;
        }
        public static Comparator<Wrapper> sortKey() {
            return new Comparator<Wrapper>() {
                public int compare(Wrapper a, Wrapper b) {
                    return a.key.compareTo(b.key);
                }
            };
        }
        public static Comparator<Wrapper> sortIndex() {
            return new Comparator<Wrapper>() {
                public int compare(Wrapper a, Wrapper b) {
                    return a.originalIndex < b.originalIndex ? -1 :
                           a.originalIndex > b.originalIndex ? 1 : 0;
                }
            }; 
        }
    }
    public static void quick(Object[] a) {
        quick(a, Wrapper.sortKey(), 0, a.length - 1);
    }
    private static void exch(Object[] a, int i, int j) {
        Object t = a[i]; a[i] = a[j]; a[j] = t;
    }
    private static void quick(Object[] a, Comparator c, int lo, int hi) {
        if (hi - lo + 1 < 5) {
            for (int i = lo; i <= hi; i++) {
                Object t = a[i];
                int j;
                for (j = i - 1; j >= lo && c.compare(t, a[j]) < 0; j--)
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
            return;
        }
        int mid = (lo + hi) >> 1;
        if (c.compare(a[mid], a[lo]) < 0) exch(a, lo, mid);
        if (c.compare(a[hi], a[lo]) < 0) exch(a, lo, hi);
        if (c.compare(a[mid], a[hi]) < 0) exch(a, mid, hi);
        Object v = a[hi]; int i = lo - 1, j = hi, p = lo - 1, q = hi;
        while (true) {
            while (c.compare(a[++i], v) < 0);
            while (c.compare(a[--j], v) > 0);
            if (i >= j) break;
            exch(a, i, j);
            if (c.compare(a[i], v) == 0) exch(a, ++p, i);
            if (c.compare(a[j], v) == 0) exch(a, --q, j);
        }
        exch(a, hi, i);
        int lt = i - 1, gt = i + 1, m = lo, n = hi - 1;
        while (m <= p) exch(a, m++, lt--);
        while (n >= q) exch(a, n--, gt++);
        quick(a, c, lo, lt);
        quick(a, c, gt, hi);
    }
    public static int binarySearch_LB(Object[] a, Object key, Comparator c) {
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int mid = (int)(Math.ceil((lo + hi) / 2.0));
            hi = c.compare(a[mid], key) >= 0 ? mid - 1 : hi;
            lo = c.compare(a[mid], key) < 0 ? mid : lo;
        }
        if (c.compare(a[lo], key) == 0) return lo; // 在左右端点处命中
        if (++lo == a.length) return -1;
        if (c.compare(a[lo], key) == 0) return lo;
        return -1;
    }
    public static int binarySearch_RB(Object[] a, Object key, Comparator c) {
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            hi = c.compare(a[mid], key) > 0 ? mid : hi;
            lo = c.compare(a[mid], key) <= 0 ? mid + 1 : lo;
        }
        if (c.compare(a[lo], key) == 0) return lo;
        if (--lo < 0) return -1;
        if (c.compare(a[lo], key) == 0) return lo;
        return -1;
    }
    public static void stablize(Wrapper[] a) {
        for (int i = 1; i < a.length; i++) 
            if (Wrapper.sortKey().compare(a[i - 1], a[i]) > 0)
                throw new IllegalArgumentException("the array is not ordered with main key");
        int left, right;
        for (int i = 0; i < a.length; i++) {
            if ((left  = binarySearch_LB(a, a[i], Wrapper.sortKey())) != 
                (right = binarySearch_RB(a, a[i], Wrapper.sortKey()))) {
                quick(a, Wrapper.sortIndex(), left, right);
            }
        }
    }
    public static void main(String[] args) {
        
        // 包装类数组
        Wrapper[] ws = Wrapper.ws(intToInteger(intsVrg(30, 1, 3, 5, 7)));
        
        // 使用主键排序
        quick(ws);
        
        StdOut.println("对主键的不稳定排序结果 : ");
        for (Wrapper w : ws)
            StdOut.println(w);
        
        // 强制稳定
        stablize(ws);
        
        StdOut.println("\n强制让排序结果稳定后 : ");
        for (Wrapper w : ws)
            StdOut.println(w);
    }
    // output
    /*
     *  对主键的不稳定排序结果 : 
        { 1, 12 }
        { 1, 11 }
        { 1, 28 }
        { 1, 24 }
        { 1, 6 }
        { 1, 25 }
        { 1, 17 }
        { 1, 16 }
        { 1, 2 }
        { 1, 27 }
        { 1, 14 }
        { 3, 15 }
        { 3, 21 }
        { 3, 8 }
        { 3, 3 }
        { 3, 19 }
        { 5, 22 }
        { 5, 26 }
        { 5, 0 }
        { 5, 7 }
        { 5, 10 }
        { 7, 29 }
        { 7, 20 }
        { 7, 5 }
        { 7, 1 }
        { 7, 9 }
        { 7, 23 }
        { 7, 4 }
        { 7, 13 }
        { 7, 18 }
        
        强制让排序结果稳定后 : 
        { 1, 2 }
        { 1, 6 }
        { 1, 11 }
        { 1, 12 }
        { 1, 14 }
        { 1, 16 }
        { 1, 17 }
        { 1, 24 }
        { 1, 25 }
        { 1, 27 }
        { 1, 28 }
        { 3, 3 }
        { 3, 8 }
        { 3, 15 }
        { 3, 19 }
        { 3, 21 }
        { 5, 0 }
        { 5, 7 }
        { 5, 10 }
        { 5, 22 }
        { 5, 26 }
        { 7, 1 }
        { 7, 4 }
        { 7, 5 }
        { 7, 9 }
        { 7, 13 }
        { 7, 18 }
        { 7, 20 }
        { 7, 23 }
        { 7, 29 }
     */
}
