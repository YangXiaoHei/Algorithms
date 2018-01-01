package 第二章_应用;

import static 第二章_初级排序算法.Text_Array.*;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_5_08 {
    static class Record implements Comparable<Record> {
        private final String s;
        private final int freq;
        public Record(String s, int freq) {
            this.s = s;
            this.freq = freq;
        }
        public int compareTo(Record that) {
            return freq > that.freq ? -1 : freq < that.freq ? 1 : 0;
        }
        public String toString() {
            return String.format("%s  %d", s, freq);
        }
    }
    public static void heap(Comparable[] a) {
        heap(a, 0, a.length - 1);
    }
    public static void heap(Comparable[] b, int lo, int hi) {
        if (lo >= hi) return;
        Comparable[] a = new Comparable[hi - lo + 1];
        System.arraycopy(b, lo, a, 0, hi - lo + 1);
        int N = a.length;
        for (int i = N >> 1; i > 0; i--) {
            int k = i, j;
            Comparable tmp = a[i - 1];
            while ((j = k << 1) <= N) {
                if (j < N && a[j - 1].compareTo(a[j]) < 0) j++;
                if (tmp.compareTo(a[j - 1]) >= 0) break;
                a[k - 1] = a[j - 1];
                k = j;
            }
            a[k - 1] = tmp;
        }
        // 使用 flody 的方法改进，以减少比较次数
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
        System.arraycopy(a, 0, b, lo, hi - lo + 1);
    }
    public static void frequency(String[] s) {
        int N = s.length;
        heap(s);
        Record[] r = new Record[N];
        String word = s[0];
        int freq = 1, m = 0;
        for (int i = 1; i < N; i++) {
            if (!s[i].equals(word)) {
                r[m++] = new Record(word, freq);
                word = s[i];
                freq = 0;
            }
            freq++;
        }
        r[m++] = new Record(word, freq);
        heap(r, 0, m - 1);
        for (int i = 0; i < m; i++)
            StdOut.println(r[i]);
    }
    public static void main(String[] args) {
        frequency(new String[] {"yanghan", 
                "lijie", "yanghan", "zhansdf", 
                "lijie", "yanghan",
                "yangkaiyun", "zhuyangkai",
                "wangjinyuan", "zhangyafang",
                "wangjinyuan",
                "lijie", "yanghan", "maliping",
                "zhuyangkai"});
     }
    // output
    /*
     *  yanghan  4
        lijie  3
        wangjinyuan  2
        zhuyangkai  2
        zhansdf  1
        zhangyafang  1
        yangkaiyun  1
        maliping  1

     */
}
