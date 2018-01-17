package Ch_2_1_Elementary_Sorts;

import java.util.regex.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_1_22 {
    static class Transaction implements Comparable<Transaction> {
        private final double amount;
        private final String date;
        private final String name;
        private final String info;
        Transaction(String info) {
            String[] infos = info.split("\\s+");
            if (infos.length != 3)
                throw new IllegalArgumentException("arguments invalid!");
            date = infos[1];
            name = infos[2];
            if (!date.matches("\\d{1,2}-\\d{1,2}-\\d{1,4}"))
                throw new IllegalArgumentException("date format invalid!");
            if (Pattern.compile("\\d").matcher(name).find())
                throw new IllegalArgumentException("name cannot contain digit!");
            if (!infos[0].matches("[1-9]\\d*(\\.\\d+)?"))
                throw new IllegalArgumentException("money format invalid!");
            amount = Double.parseDouble(infos[0]);
            this.info = info;
        }
        public String toString() { return info; }
        public int compareTo(Transaction that) {
            return amount < that.amount ? -1 : amount > that.amount ? 1 : 0;
        }
    }
    public static void shell(Transaction[] a) {
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                int j;
                Transaction t = a[i];
                for (j = i - h; j >= 0 && t.compareTo(a[j]) < 0; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
            h /= 3;
        }
    }
    public static void main(String[] args) {
        Transaction t1 = new Transaction("200.23 5-2-1994 lisi"),
                    t2 = new Transaction("342 10-7-2010 zhangsan"),
                    t3 = new Transaction("100 9-27-2017 wangwu"),
                    t4 = new Transaction("342 2-28-1879 zhaoliu"),
                    t5 = new Transaction("500.3 6-30-2011 lisi"),
                    t6 = new Transaction("500 5-21-2012 zhangsan"),
                    t7 = new Transaction("400 5-20-2013 zhaoliu");
        Transaction[] arr = new Transaction[] {t1, t2, t3, t4, t5, t6, t7};
        shell(arr);
        for (Transaction t : arr)
            StdOut.println(t);
    }
    // output
    /*
     *  100 9-27-2017 wangwu
        200.23 5-2-1994 lisi
        342 10-7-2010 zhangsan
        342 2-28-1879 zhaoliu
        400 5-20-2013 zhaoliu
        500 5-21-2012 zhangsan
        500.3 6-30-2011 lisi
     */
}
