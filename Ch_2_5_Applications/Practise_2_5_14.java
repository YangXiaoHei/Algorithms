package Ch_2_5_Applications;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_5_14 {
    static class Domain implements Comparable<Domain> {
        private final String url;
        private String[] reverse;
        public Domain(String url) { 
            this.url = url;
            String[] s = url.split("\\.");
            reverse = new String[s.length];
            for (int i = s.length - 1, j = 0; i >= 0; i--)
                reverse[j++] = s[i];
        }
        public static Domain[] domains(String[] s) {
            Domain[] d = new Domain[s.length];
            for (int i = 0; i < s.length; i++)
                d[i] = new Domain(s[i]);
            return d;
        }
        public int compareTo(Domain u) {
            int minLen = Math.min(reverse.length, u.reverse.length);
            for (int i = 0; i < minLen; i++) {
                if (reverse[i].compareTo(u.reverse[i]) < 0) return -1;
                if (reverse[i].compareTo(u.reverse[i]) > 0) return 1;
            }
            if (reverse.length < u.reverse.length) return -1;
            if (reverse.length > u.reverse.length) return 1;
            return 0;
        }
        public String toString() { return url; }
    }
    public static void shell(Comparable[] b) {
        int N = b.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                Comparable t = b[i]; int j;
                for (j = i - h; j >= 0 && t.compareTo(b[j]) < 0; j -= h)
                    b[j + h] = b[j];
                b[j + h] = t;
            }
            h /= 3;
        }
    }
    public static void main(String[] args) {
        String[] urls = {
                "cs.princeton.edu",
                "ab.yagknsd.bc",
                "we.lkjsdf.zd",
                "lwe.bnmsdf.com",
                "re.332iro.cn",
        };
        Domain[] d = Domain.domains(urls);
        shell(d);
        for (int i = 0; i < d.length; i++)
            StdOut.println(d[i]);
    }
    // output
    /*
     *  ab.yagknsd.bc
        re.332iro.cn
        lwe.bnmsdf.com
        cs.princeton.edu
        we.lkjsdf.zd
     */
}
