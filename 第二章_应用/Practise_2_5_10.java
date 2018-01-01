package 第二章_应用;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_5_10 {
    static class Version implements Comparable<Version> {
        private final String s;
        private final int first;
        private final int mid;
        private final int last;
        public Version(String s) {
            String[] a = s.split("\\.");
            if (a.length != 3)
                throw new IllegalArgumentException("invalid argument format");
            for (int i = 0; i < 3; i++)
                if (!a[i].matches("\\d+"))
                    throw new IllegalArgumentException("invalid digit format");
            first = Integer.parseInt(a[0]);
            mid = Integer.parseInt(a[1]);
            last = Integer.parseInt(a[2]);
            this.s = s;
        }
        public String toString() { return s; }
        public int compareTo(Version v) {
            if      (first < v.first)   return -1;
            else if (first > v.first)   return 1;
            else if (mid < v.mid)       return -1;
            else if (mid > v.mid)       return 1;
            else if (last < v.last)     return -1;
            else if (last > v.last)     return 1;
            return 0;
        }
    }
    public static void shell(Comparable[] a) {
        int[] ints = { 4188161, 2354689, 1045055, 587521, 260609, 
                146305, 64769, 36289, 16001, 8929, 3905, 2161, 
                929, 505, 209, 109, 41, 19, 5, 1};
        for (int k = 0; k < ints.length; k++) {
            for (int h = ints[k], i = h; i < a.length; i++) {
                Comparable t = a[i];
                int j;
                for (j = i - h; j >= 0 && t.compareTo(a[j]) < 0; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
        }
    }
    public static void main(String[] args) {
        Version[] a = {
          new Version("115.5.1"),
          new Version("114.3.2"),
          new Version("113.2.1"),
          new Version("115.4.2"),
          new Version("115.5.5"),
          new Version("114.5.1"),
          new Version("114.2.1"),
          new Version("114.5.0"),
          new Version("112.1.1"),
          new Version("111.8.1"),
          new Version("111.23.7"),
          new Version("110.3.1"),
          new Version("110.2.1"),
          new Version("117.5.1"),
          new Version("118.10.1"),
          new Version("114.11.1"),
          new Version("110.4.1"),
          new Version("113.2.4"),
          new Version("110.7.15"),
          new Version("114.9.3"),
          new Version("115.10.17"),
          new Version("116.19.18"),
          new Version("100.0.19"),
          new Version("108.10.1"),
          new Version("107.8.1"),
          new Version("107.14.1"),
          new Version("108.19.6"),
          new Version("105.15.2"),
          new Version("109.13.16"),
          new Version("112.12.7"),
          new Version("111.5.3"),
          new Version("111.7.6"),
          new Version("112.4.4"),
          new Version("108.2.2"),
          new Version("109.5.1"),
          new Version("109.9.10"),
          new Version("110.5.5"),
        };
        shell(a);
        for (Version v : a)
            StdOut.println(v);
    }
    // output
    /*
     *  100.0.19
        105.15.2
        107.8.1
        107.14.1
        108.2.2
        108.10.1
        108.19.6
        109.5.1
        109.9.10
        109.13.16
        110.2.1
        110.3.1
        110.4.1
        110.5.5
        110.7.15
        111.5.3
        111.7.6
        111.8.1
        111.23.7
        112.1.1
        112.4.4
        112.12.7
        113.2.1
        113.2.4
        114.2.1
        114.3.2
        114.5.0
        114.5.1
        114.9.3
        114.11.1
        115.4.2
        115.5.1
        115.5.5
        115.10.17
        116.19.18
        117.5.1
        118.10.1
     */
}
