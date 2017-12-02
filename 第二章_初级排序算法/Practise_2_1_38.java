package 第二章_初级排序算法;

import edu.princeton.cs.algs4.*;

public class Practise_2_1_38 {
    static class A  implements Comparable<A> {
        String key; double d;
        A (String k, double dd) { key = k; d = dd; }
        public int compareTo(A that) { return key.compareTo(that.key); }
        static A[] gen(int N) {
            A[] arr = new A[N];
            for (int i = 0; i < N; i++) {
                int charCnt = StdRandom.uniform(10, 100);
                StringBuilder sb = new StringBuilder();
                while (charCnt-- > 0)
                    sb.append('a' + StdRandom.uniform(0, 26));
                arr[i] = new A(sb.toString(), StdRandom.uniform());
            }
            return arr;
        }
    }
    static class B implements Comparable<B> {
        double key;
        String s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;
        B (double k, String s1, 
                     String s2, 
                     String s3,
                     String s4, 
                     String s5, 
                     String s6, 
                     String s7, 
                     String s8, 
                     String s9, 
                     String s10) {
            key = k;
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
            this.s4 = s4;
            this.s5 = s5;
            this.s6 = s6;
            this.s7 = s7;
            this.s8 = s8;
            this.s9 = s9;
            this.s10 = s10;
        }
        public int compareTo(B that) {
            return key < that.key ? -1 : key > that.key ? 1 : 0;
        }
        public static String random() {
            int charCnt = StdRandom.uniform(10, 100);
            StringBuilder sb = new StringBuilder();
            while (charCnt-- > 0)
                sb.append('a' + StdRandom.uniform(0, 26));
            return sb.toString();
        }
        public static B[] gen(int N) {
            B[] arr = new B[N];
            for (int i = 0; i < N; i++) 
                arr[i] = new B(StdRandom.uniform(), random(), 
                                                    random(),
                                                    random(),
                                                    random(),
                                                    random(),
                                                    random(),
                                                    random(),
                                                    random(),
                                                    random(),
                                                    random());
            return arr;
        }
    }
    static class C implements Comparable<C> {
        int i;
        int[] arr = new int[20];
        C (int i) { this.i = i; }
        public int compareTo(C that) {
            return i < that.i ? -1 : i > that.i ? 1 : 0;
        }
        static C[] gen(int N) {
            C[] arr = new C[N];
            for (int i = 0; i < N; i++) 
                arr[i] = new C(StdRandom.uniform(N));
            return arr;
        }
    }
    public static double shell(Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                Comparable t = a[i];
                int j;
                for (j = i - h; j >= 0 && t.compareTo(a[j]) < 0; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
            h /= 3;
        }
        return timer.elapsedTime();
    }
    public static void main(String[] args) {
        StdOut.printf("A : %.3f\n", shell(A.gen(400000)));
        StdOut.printf("B : %.3f\n", shell(B.gen(400000)));
        StdOut.printf("C : %.3f\n", shell(C.gen(400000)));
    }
    // output
    /*
     *  A : 1.142
        B : 0.834
        C : 0.656
     */
}
