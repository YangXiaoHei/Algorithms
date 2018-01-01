package 第二章_应用;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_5_09 {
    static class DJIA {
        private final int month;
        private final int day;
        private final int id;
        private final String s;
        private final long dealAmount;
        public DJIA (String s, long d) {
            this.s = s;
            String[] ar = s.split("-");
            if (ar.length != 3)
                throw new IllegalArgumentException("invalid arguments format");
            id = Integer.parseInt(ar[0]);
            if (!ar[0].matches("\\d+"))
                throw new IllegalArgumentException("id format invalid");
            day = Integer.parseInt(ar[2]);
            if (!ar[2].matches("\\d+"))
                throw new IllegalArgumentException("day format invalid");
            String mo = ar[1];
            if      (mo.toLowerCase().contains("jan")) month = 1;
            else if (mo.toLowerCase().contains("feb")) month = 2;
            else if (mo.toLowerCase().contains("mar")) month = 3;
            else if (mo.toLowerCase().contains("apr")) month = 4;
            else if (mo.toLowerCase().contains("may")) month = 5;
            else if (mo.toLowerCase().contains("jun")) month = 6;
            else if (mo.toLowerCase().contains("jul")) month = 7;
            else if (mo.toLowerCase().contains("aug")) month = 8;
            else if (mo.toLowerCase().contains("sep")) month = 9;
            else if (mo.toLowerCase().contains("oct")) month = 10;
            else if (mo.toLowerCase().contains("nov")) month = 11;
            else if (mo.toLowerCase().contains("dec")) month = 12;
            else throw new IllegalArgumentException("month format invalid");
            dealAmount = d;
        }
        public static Comparator<DJIA> accordingDate() {
            return new Comparator<DJIA>() {
                public int compare(DJIA a, DJIA b) {
                    if      (a.month < b.month) return -1;
                    else if (a.month > b.month) return 1;
                    else if (a.day < b.day)     return -1;
                    else if (a.day > b.day)     return 1;
                    return 0;
                }
            };
        }
        public static Comparator<DJIA> accordingID() {
            return new Comparator<DJIA>() {
                public int compare(DJIA a, DJIA b) {
                    return a.id < b.id ? -1 : a.id > b.id ? 1 : 0;
                }
            };
        }
        public static Comparator<DJIA> accordingAmount() {
            return new Comparator<DJIA>() {
                public int compare(DJIA a, DJIA b) {
                    return a.dealAmount < b.dealAmount ? -1 : a.dealAmount > b.dealAmount ? 1 : 0;
                }
            };
        }
        public String toString() {
            return String.format("[%s  \t%d]", s, dealAmount);
        }
    }
    public static void quick(Object[] a, Comparator c) {
        quick(a, c, 0, a.length - 1);
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
    public static void main(String[] args) {
        DJIA[] a = {
           new DJIA ("1-Oct-28", 3500000),
           new DJIA ("2-Oct-22", 3850000),
           new DJIA ("3-Oct-23", 4060000),
           new DJIA ("4-Oct-28", 4330000),
           new DJIA ("5-Oct-29", 4360000),
           
           new DJIA ("30-Dec-31", 1500000),
           new DJIA ("21-Dec-23", 9500000),
           new DJIA ("1-Aug-12", 4509000),
           new DJIA ("14-Aug-30", 2500000),
           new DJIA ("3-Jul-28", 3500000),
           new DJIA ("19-Jun-6", 8500000),
           new DJIA ("32-Jul-28", 6509900),
           new DJIA ("3-Jul-28", 7500000),
           new DJIA ("12-Nov-00", 3503001),
           new DJIA ("11-Sep-28", 3500010),
           new DJIA ("1-Sep-00", 3500100),
           new DJIA ("14-Jun-28", 1501000),
           new DJIA ("1-Feb-12", 3510000),
           new DJIA ("31-Mar-28", 2520000),
           new DJIA ("29-May-28", 3530000),
           new DJIA ("24-May-31", 3505000),
           new DJIA ("28-Feb-28", 8500600),
           new DJIA ("25-Jan-28", 4500090),
           new DJIA ("17-Nov-28", 9500010),
        };
        StdOut.println("根据交易量排序 : \n");
        quick(a, DJIA.accordingAmount());
        for (DJIA d : a)
            StdOut.println(d);
        StdOut.println("========================\n");
        StdOut.println("根据日期排序 : \n");
        quick(a, DJIA.accordingDate());
        for (DJIA d : a)
            StdOut.println(d);
        StdOut.println("========================\n");
        StdOut.println("根据ID排序 : \n");
        quick(a, DJIA.accordingID());
        for (DJIA d : a)
            StdOut.println(d);
    }
    // output
    /*
     *  根据交易量排序 : 
     *  
        [30-Dec-31      1500000]
        [14-Jun-28      1501000]
        [14-Aug-30      2500000]
        [31-Mar-28      2520000]
        [3-Jul-28   3500000]
        [1-Oct-28   3500000]
        [11-Sep-28      3500010]
        [1-Sep-00   3500100]
        [12-Nov-00      3503001]
        [24-May-31      3505000]
        [1-Feb-12   3510000]
        [29-May-28      3530000]
        [2-Oct-22   3850000]
        [3-Oct-23   4060000]
        [4-Oct-28   4330000]
        [5-Oct-29   4360000]
        [25-Jan-28      4500090]
        [1-Aug-12   4509000]
        [32-Jul-28      6509900]
        [3-Jul-28   7500000]
        [19-Jun-6   8500000]
        [28-Feb-28      8500600]
        [21-Dec-23      9500000]
        [17-Nov-28      9500010]
        ========================
        
        根据日期排序 : 
        
        [25-Jan-28      4500090]
        [1-Feb-12   3510000]
        [28-Feb-28      8500600]
        [31-Mar-28      2520000]
        [29-May-28      3530000]
        [24-May-31      3505000]
        [19-Jun-6   8500000]
        [14-Jun-28      1501000]
        [3-Jul-28   7500000]
        [3-Jul-28   3500000]
        [32-Jul-28      6509900]
        [1-Aug-12   4509000]
        [14-Aug-30      2500000]
        [1-Sep-00   3500100]
        [11-Sep-28      3500010]
        [2-Oct-22   3850000]
        [3-Oct-23   4060000]
        [1-Oct-28   3500000]
        [4-Oct-28   4330000]
        [5-Oct-29   4360000]
        [12-Nov-00      3503001]
        [17-Nov-28      9500010]
        [21-Dec-23      9500000]
        [30-Dec-31      1500000]
        ========================
        
        根据ID排序 : 
        
        [1-Sep-00   3500100]
        [1-Oct-28   3500000]
        [1-Aug-12   4509000]
        [1-Feb-12   3510000]
        [2-Oct-22   3850000]
        [3-Jul-28   3500000]
        [3-Jul-28   7500000]
        [3-Oct-23   4060000]
        [4-Oct-28   4330000]
        [5-Oct-29   4360000]
        [11-Sep-28      3500010]
        [12-Nov-00      3503001]
        [14-Jun-28      1501000]
        [14-Aug-30      2500000]
        [17-Nov-28      9500010]
        [19-Jun-6   8500000]
        [21-Dec-23      9500000]
        [24-May-31      3505000]
        [25-Jan-28      4500090]
        [28-Feb-28      8500600]
        [29-May-28      3530000]
        [30-Dec-31      1500000]
        [31-Mar-28      2520000]
        [32-Jul-28      6509900]
     */
}
