package Ch_2_1_Elementary_Sorts;

import java.util.regex.Pattern;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_1_21 {
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
    public static void main(String[] args) {
        Transaction t1 = new Transaction("200.23 5-2-1994 lisi"),
                    t2 = new Transaction("342 10-7-2010 zhangsan"),
                    t3 = new Transaction("100 9-27-2017 wangwu"),
                    t4 = new Transaction("342 2-28-1879 zhaoliu");
        StdOut.println(t1.compareTo(t2));
        StdOut.println(t1.compareTo(t3));
        StdOut.println(t1.compareTo(t4));
        StdOut.println(t2.compareTo(t3));
        StdOut.println(t2.compareTo(t4));
        StdOut.println(t3.compareTo(t4));
    }
    // output
    /*
     * -1
        1
        -1
        1
        0
        -1
     */
}
