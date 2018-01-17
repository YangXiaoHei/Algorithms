package Ch_2_5_Applications;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_5_02 {
    public static void quick(String[] s) {
        quick(s, 0, s.length - 1);
    }
    public static void quick(String[] s, int lo, int hi) {
        if (hi - lo + 1 < 7) {
            for (int i = lo; i <= hi; i++) {
                String t = s[i];
                int j;
                for (j = i - 1; j >= lo && less(t, s[j]); j--) 
                    s[j + 1] = s[j];
                s[j + 1] = t;
            }
            return;
        }
        int mid = (lo + hi) >> 1;
        if (less(s[mid], s[lo])) exch(s, lo, mid);
        if (less(s[hi],  s[lo])) exch(s, hi, lo);
        if (less(s[mid], s[hi])) exch(s, mid, hi);
        String v = s[hi]; int i = lo - 1, j = hi, p = lo - 1, q = hi;
        while (true) {
            while (less(s[++i], v));
            while (grea(s[--j], v));
            if (i >= j) break;
            exch(s, i, j);
            if (equal(s[i], v)) exch(s, i, ++p);
            if (equal(s[j], v)) exch(s, j, --q);
        }
        exch(s, i, hi);
        int lt = i - 1, gt = i + 1, m = lo, n = hi - 1;
        while (m <= p) exch(s, lt--, m++);
        while (n >= q) exch(s, gt++, n--);
        quick(s, lo, lt);
        quick(s, gt, hi);
    }
    private static void exch(String[] s, int i, int j) {
        String t = s[i]; s[i] = s[j]; s[j] = t;
    }
    public static boolean equal(String s1, String s2) {
        return s1.compareTo(s2) == 0;
    }
    public static boolean grea(String s1, String s2) {
        return s1.compareTo(s2) > 0;
    }
    public static boolean less(String s1, String s2) {
        return s1.compareTo(s2) < 0;
    }
    public static int rank(String[] s, String key) {
        int lo = 0, hi = s.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if      (less(s[mid], key))  lo = mid + 1;
            else if (less(key, s[mid])) hi = mid - 1;
            else    return mid;
        }
        return -1;
    }
    public static void printCompound(String[] s) {
        quick(s);
        for (int i = 0; i < s.length; i++) {
            if (isCompound(s, s[i]))
                StdOut.println(s[i]);
        }
    }
    public static boolean isCompound(String[] s, String c) {
        for (int j = 1; j <= c.length(); j++) {
            String sub = c.substring(0, j);
            if (rank(s, sub) < 0) continue;
            sub = c.substring(j, c.length());
            if (rank(s, sub) >= 0) return true;
        }
        return false;
    }
    public static void main(String[] args) {
        String[] s = { "after", "thought", "yang",
                "afterthought", "han", "li", 
                "lijie", "yanghan", "jie" };
        printCompound(s);
    }
    // output
    /*
     *  afterthought
        lijie
        yanghan

     */
    
}
