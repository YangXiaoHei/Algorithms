package Ch_2_5_Applications;

import static Tool.ArrayGenerator.*;
import java.util.regex.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_5_33 {
    static class Transaction implements Comparable<Transaction> {
        private final String who;
        private final String when;
        private final String amount;
        private final double amountValue;
        Transaction(String transaction) {
            String[] result = transaction.split("\\s+");
            if (result.length != 3)
                throw new RuntimeException("input " + transaction + " invalid");
            
            who = result[0];
            if (Pattern.compile("[^a-zA-Z'·]").matcher(who).find())
                throw new RuntimeException("invalid name " + who);
            
            when = result[1];
            if (!Pattern.compile("\\d{1,4}[-/\\\\]\\d{1,2}[-/\\\\]\\d{1,2}").matcher(when).find())
                throw new RuntimeException("invalid date " + when);
            
            amount = result[2];
            amountValue = Double.parseDouble(amount);
            if (!Pattern.compile("[1-9]\\d*(\\.\\d+)?").matcher(amount).find())
                throw new RuntimeException("invalid amount " + when);
        }
        String who() { return who; }
        String when() { return when; }
        String amount() { return amount; }
        public static Transaction[] randomGen(int N) {
            Transaction[] t = new Transaction[N];
            for (int i = 0; i < N; i++)
                t[i] = new Transaction(randomName() + " "
                                     + randomDate() + " "
                                     + randomMoney());
            return t;
        }
        public static Transaction[] copy(Transaction[] a) {
            return a.clone();
        }
        public String toString() {
            return String.format("Name : %s\nDate : %s\nAmount : %s", who, when, amount);
        }
        public int compareTo(Transaction t) {
            return amountValue < t.amountValue ? -1 :
                   amountValue > t.amountValue ? 1 : 0;
        }
        public boolean equals(Object o) {
            if (o == null) return false;
            if (o == this) return true;
            if (o.getClass() != Transaction.class) return false;
            Transaction that = (Transaction)o;
            if (who.equals(that.who) && when.equals(that.when) &&
                Double.parseDouble(amount) == Double.parseDouble(that.amount))
                return true;
            return false;
        }
    }
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    public static String randomName() {
        int len = StdRandom.uniform(4, 14);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++)
            sb.append((char)('a' + StdRandom.uniform(0, 25)));
        return sb.toString();
    }
    public static String randomMoney() {
        return StdRandom.uniform(1000, 100000) + "";
    }
    public static String randomDate() {
        int year = StdRandom.uniform(1900, 2018);
        int month = StdRandom.uniform(1, 12);
        int day;
        if (month == 2) {
            day = isLeapYear(year) ? StdRandom.uniform(1, 29) : StdRandom.uniform(1, 28);
        } else if (month == 1 || 
                   month == 3 ||
                   month == 5 ||
                   month == 7 ||
                   month == 8 ||
                   month == 10 ||
                   month == 12) {
            day = StdRandom.uniform(1, 31);
        } else {
            day = StdRandom.uniform(1, 30);
        }
        String m = month + "";
        String d = day + "";
        String y = year + "";
        return y + "/" + m + "/" + d;
    }
    /*
     * 希尔排序
     */
    public static double shell(Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length;
        int[] ints = { 4188161, 2354689, 1045055, 587521, 260609, 
                146305, 64769, 36289, 16001, 8929, 3905, 2161, 
                929, 505, 209, 109, 41, 19, 5, 1};
        for (int k = 0; k < ints.length; k++) 
            for (int h = ints[k], i = h; i < N; i++) {
                Comparable t = a[i]; int j;
                for (j = i - h; j >= 0 && t.compareTo(a[j]) < 0; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
        return timer.elapsedTime();
    }
    /*
     * 归并排序
     */
    public static double merge(Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        Comparable[] aux = a.clone();
        merge(aux, a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void merge(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if (hi - lo + 1 < 8) {
            for (int i = lo; i <= hi; i++) {
                Comparable t = dst[i]; int j;
                for (j = i - 1; j >= lo && t.compareTo(dst[j]) < 0; j--)
                    dst[j + 1] = dst[j];
                dst[j + 1] = t;
            }
            return;
        }
        int mid = (lo + hi) >> 1;
        merge(dst, src, lo, mid);
        merge(dst, src, mid + 1, hi);
        if (src[mid].compareTo(src[mid + 1]) < 0) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        mergeSort(src, dst, lo, mid, hi);
    }
    private static void mergeSort(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)                       dst[k] = src[j++];
            else if (j > hi)                        dst[k] = src[i++];
            else if (src[j].compareTo(src[i]) < 0)  dst[k] = src[j++];
            else                                    dst[k] = src[i++];
    }
    /*
     * 快速排序
     */
    public static double quick(Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick(Comparable[] a, int lo, int hi) {
        if (hi - lo + 1 < 8) {
            for (int i = lo; i <= hi; i++) {
                Comparable t = a[i]; int j;
                for (j = i - 1; j >= lo && t.compareTo(a[j]) < 0; j--)
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
            return;
        }
        int mid = (lo + hi) >> 1;
        if (a[mid].compareTo(a[lo]) < 0) /* exch (a, mid, lo); */ { Comparable t = a[mid]; a[mid] = a[lo]; a[lo] = t; }
        if (a[hi].compareTo(a[lo]) < 0)  /* exch (a, hi, lo); */  { Comparable t = a[hi]; a[hi] = a[lo]; a[lo] = t; }
        if (a[mid].compareTo(a[hi]) < 0) /* exch (a, mid, hi); */ { Comparable t = a[mid]; a[mid] = a[hi]; a[hi] = t; }
        
        int i = lo - 1, p = lo - 1, j = hi, q = hi; Comparable v = a[hi];
        while (true) {
            while (a[++i].compareTo(v) < 0);
            while (a[--j].compareTo(v) > 0);
            if (i >= j) break;
            /* exch(a, i, j); */ { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }
            if (a[i].compareTo(v) == 0) /* exch(a, i, ++p); */ { ++p; Comparable t = a[p]; a[p] = a[i]; a[i] = t; }
            if (a[j].compareTo(v) == 0) /* exch(a, j, --q); */ { --q; Comparable t = a[q]; a[q] = a[j]; a[j] = t; }
        }
        /* exch(a, hi, i); */ { Comparable t = a[hi]; a[hi] = a[i]; a[i] = t; }
        
        int lt = i - 1, gt = i + 1, k = lo, m = hi - 1;
        while (k <= p) /* exch(a, k++, lt--); */ {  Comparable t = a[k]; a[k] = a[lt]; a[lt] = t; k++; lt--; }
        while (m >= q) /* exch(a, m--, gt++); */ {  Comparable t = a[m]; a[m] = a[gt]; a[gt] = t; m--; gt++; }
        
        quick(a, lo, lt);
        quick(a, gt, hi);
    }
    /*
     * 堆排序
     */
    public static double heap(Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length;
        for (int i = N >> 1; i > 0; i--) {
            int k = i, j; Comparable t = a[i - 1];
            while ((j = k << 1) <= N) {
                if (j < N && a[j - 1].compareTo(a[j]) < 0) j++;
                if (t.compareTo(a[j - 1]) >= 0) break;
                a[k - 1] = a[j - 1];
                k = j;
            }
            a[k - 1] = t;
        }
        while (N > 1) {
            Comparable tmp = a[N - 1];
            a[N - 1] = a[0];
            --N; 
            int k = 1, j;
            while ((j = k << 1) <= N) {
                if (j < N && a[j - 1].compareTo(a[j]) < 0) j++;
                a[k - 1] = a[j - 1];
                k = j;
            }
            a[k - 1] = tmp;
            while (k > 1 && tmp.compareTo(a[(k >> 1) - 1]) > 0) {
                a[k - 1] = a[(k >> 1) - 1];
                k >>= 1;
            }
            a[k - 1] = tmp;
        }
        return timer.elapsedTime();
    }
    public static void main(String[] args) {
        Transaction[] a = Transaction.randomGen(1000000);
        Transaction[] b = Transaction.copy(a);
        Transaction[] c = Transaction.copy(a);
        Transaction[] d = Transaction.copy(a);
        StdOut.printf("希尔排序 : %.3f\n", shell(a));
        assert isSorted(a);
        assert !isSorted(b);
        assert !isSorted(c);
        assert !isSorted(d);
        StdOut.printf("归并排序 : %.3f\n", merge(b));
        assert isSorted(b);
        assert !isSorted(c);
        assert !isSorted(d);
        StdOut.printf("快速排序 : %.3f\n", quick(c));
        assert isSorted(c);
        assert !isSorted(d);
        StdOut.printf("堆排序 : %.3f\n", heap(d));
        assert isSorted(d);
        StdOut.println("\n排序成功");
    }
    // output
    /*
     *  希尔排序 : 1.267
        归并排序 : 0.660
        快速排序 : 0.858
        堆排序 : 1.295

        排序成功
     */
}
