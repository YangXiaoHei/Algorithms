package Ch_2_5_Applications;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_5_21 {
    static class Vector<T extends Comparable<T>> implements Comparable<Vector<T>> {
        private int dimension;
        private T[] values;
        @SuppressWarnings("unchecked")
        public Vector(int d) { 
            this.dimension = d; 
            values = (T[])new Comparable[d];
        }
        @SuppressWarnings("unchecked")
        public Vector(Double...v) {
            int d = v.length;
            dimension = d;
            values = (T[])new Comparable[d];
            System.arraycopy(v, 0, values, 0, d);
        }
        public void setV(int d, T v) {
            if (d >= dimension || d < 0)
                throw new IllegalArgumentException("维数不在该向量范围内");
            values[d] = v;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{ "); int i;
            for (i = 0; i < dimension - 1; i++)
                sb.append(String.format("%5.2s, ", values[i]));
            sb.append(String.format("%5.2s }", values[i]));
            return sb.toString();
        }
        public int compareTo(Vector<T> v) {
            if (v.dimension != dimension)
                return dimension - v.dimension;
            for (int i = 0; i < dimension; i++) {
                if (values[i].compareTo(v.values[i]) < 0) return -1;
                if (values[i].compareTo(v.values[i]) > 0) return 1;
            }
            return 0;
        }
    }
    @SuppressWarnings("unchecked")
    public static  Vector<Integer>[] VectorGen(int N, int d) {
        Vector<Integer>[] vs = (Vector<Integer>[])new Vector[N];
        for (int i = 0; i < N; i++) {
            Integer[] dv = Integers(d, 1, d);
            vs[i] = new Vector<Integer>(d);
            for (int j = 0; j < d; j++)
                vs[i].setV(j, dv[j]);
        } 
        return vs;
    }
    public static void heap(Comparable[] a) {
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
        // 使用 floyd 方法减少比较次数哦~~~
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
    }
    public static void main(String[] args) {
        Vector<Integer>[] vd = VectorGen(100, 10);
        heap(vd);
        for (Vector<Integer> d : vd)  StdOut.println(d);
    }
    // output
    /*
     *  {     1,     1,     6,    10,     5,     9,     7,     3,     7,     3 }
        {     1,     2,     5,     8,     1,     6,     7,     2,     6,     3 }
        {     1,     2,     6,     1,     9,     2,     9,     3,     1,     9 }
        {     1,     3,     2,     3,     5,     1,     4,     5,     7,     7 }
        {     1,     3,     9,     6,     4,    10,     5,     6,     2,    10 }
        {     1,     7,     7,     8,     8,     6,     8,     4,     5,     7 }
        {     1,     8,     5,     5,    10,     5,     7,     2,     9,     9 }
        {     1,     9,     6,     8,     9,     9,     9,     5,     5,     9 }
        {     1,     9,     7,     6,    10,     7,     8,    10,     6,     5 }
        {     2,     1,     1,    10,    10,     2,     8,     9,     6,     9 }
        {     2,     1,     5,     1,     4,     1,     6,     8,     1,     6 }
        {     2,     2,     7,     9,     5,     5,     7,     8,     8,    10 }
        {     2,     3,     1,     5,     2,     1,     8,     4,    10,     9 }
        {     2,     4,     6,     2,     1,     1,     8,     7,    10,     2 }
        {     2,     5,     2,    10,     7,     7,     9,     3,     1,     6 }
        {     2,     7,     2,     8,    10,     7,     5,    10,     3,    10 }
        {     2,     8,     8,     4,     1,     5,     8,     1,     5,     2 }
        {     2,     9,     9,     4,     4,     8,     1,     2,     8,     7 }
        {     2,    10,     9,     9,     6,     4,     6,     2,     7,     6 }
        {     3,     1,    10,     2,     4,     5,     4,     9,    10,     3 }
        {     3,     2,     6,     7,     6,     3,     6,     6,     2,     2 }
        {     3,     2,     6,     9,     4,     5,     3,     4,     6,     3 }
        {     3,     3,     2,     9,     9,     8,     1,    10,     9,     6 }
        {     3,     3,     3,     7,     5,    10,     8,     3,     9,     6 }
        {     3,     5,     8,     6,     1,     6,     7,     6,     3,     1 }
        {     3,     7,     5,     6,     5,     2,     9,     8,     4,     2 }
        {     3,     7,     6,     2,     8,     4,     8,     6,     8,     5 }
        {     3,     8,     1,     8,     3,     3,     6,     5,     4,     6 }
        {     3,     9,     1,     9,     8,     6,     2,     2,     3,     3 }
        {     3,     9,     8,     1,     1,     1,     5,     1,     3,     5 }
        {     3,     9,     9,     4,     1,     5,     2,     5,     6,    10 }
        {     3,     9,     9,     9,     5,     8,     9,     1,     4,     1 }
        {     4,     1,     4,     7,     2,     5,     6,     5,     5,    10 }
        {     4,     1,     8,     6,     7,     5,     8,    10,     8,     3 }
        {     4,     2,     5,     7,     2,     3,    10,     1,     8,     1 }
        {     4,     2,     8,     3,     4,     6,     9,     5,     4,     4 }
        {     4,     5,     3,     5,     1,     5,     1,     5,    10,     5 }
        {     4,     8,     1,     1,     1,     5,     7,     4,     9,     3 }
        {     4,     8,     1,     3,     9,     3,     3,     9,     4,     6 }
        {     4,     8,     2,     7,     8,     9,     1,     1,     1,    10 }
        {     4,     8,     7,     9,     5,     8,     6,     3,     1,     9 }
        {     4,    10,     1,     7,     4,     9,     8,     8,     7,     5 }
        {     4,    10,     6,     1,     2,     9,     9,     3,     7,     3 }
        {     5,     1,    10,     1,     2,     7,    10,     2,     1,     2 }
        {     5,     2,     8,     7,     2,    10,     5,     7,     1,     2 }
        {     5,     2,     9,     8,     2,     6,     5,    10,     5,     1 }
        {     5,     3,     9,     7,     6,     4,     7,     2,     5,     6 }
        {     5,     4,     6,     8,     7,     7,     5,     9,     1,     5 }
        {     5,     5,     3,     5,     9,    10,     2,     7,     5,     8 }
        {     5,     5,     3,     8,     1,     3,     9,     5,     2,     8 }
        {     5,     6,     3,     9,     1,     3,     4,     7,     1,     1 }
        {     5,     6,     6,     5,     4,    10,     5,     4,     8,     2 }
        {     5,     7,     7,     1,     4,     1,     9,     6,     4,     1 }
        {     5,     8,     6,     3,     9,     8,     4,     8,     6,     6 }
        {     5,     9,     4,     3,     6,     2,     1,     7,     1,     6 }
        {     6,     2,     3,     7,     7,     6,     7,     1,     6,     3 }
        {     6,     6,     1,     3,    10,     5,     4,     6,     6,     8 }
        {     6,     6,     2,     8,     7,     3,     8,     3,     3,    10 }
        {     6,     6,     9,     4,     1,     9,     5,     5,     7,     5 }
        {     6,     7,     1,     6,     3,     6,     1,     7,     1,    10 }
        {     6,     7,     9,     6,    10,     7,     5,     8,     5,     7 }
        {     6,     8,    10,     6,     2,     6,     9,     8,     7,     7 }
        {     6,     9,     1,     3,     7,    10,    10,     2,     3,     5 }
        {     6,     9,     6,     3,     7,     7,     5,    10,     4,     3 }
        {     6,     9,     7,     7,     9,     8,     6,     1,     3,     6 }
        {     7,     3,     4,     4,     3,    10,    10,     3,     4,     5 }
        {     7,     3,     5,     7,     6,     4,     8,     8,     2,     9 }
        {     7,     4,     4,     7,     2,     6,     3,     3,     5,     5 }
        {     7,     4,     5,    10,     8,     9,     9,     4,     9,     4 }
        {     7,     7,     5,    10,     5,     5,    10,     9,    10,     1 }
        {     7,     9,     6,     2,     3,     6,    10,     2,     6,     6 }
        {     7,    10,     4,     9,     8,     9,     2,     7,     8,     7 }
        {     7,    10,     9,     4,     4,     1,     2,     3,    10,    10 }
        {     8,     1,     7,     4,     1,     8,     2,     8,    10,     2 }
        {     8,     2,     6,     1,     7,     4,     6,     7,     1,     1 }
        {     8,     3,     5,     9,     7,     5,     5,     4,     4,     9 }
        {     8,     4,     2,     2,     8,     9,     9,     5,     9,     5 }
        {     8,     4,     3,     6,     1,     7,     5,     9,     5,     3 }
        {     8,     4,     4,     7,     4,     6,     5,     1,     8,     5 }
        {     8,     4,     9,     6,     1,     2,     3,     4,     8,     7 }
        {     8,     5,     4,     1,     8,     7,     9,     7,     7,     6 }
        {     8,     8,     2,     1,     8,     1,     9,     9,     2,     2 }
        {     9,     2,     8,     5,     9,     7,     7,     4,     6,     2 }
        {     9,     4,     1,     6,     5,     6,     1,     6,     1,     2 }
        {     9,     5,     7,     8,     8,     5,     5,     8,     8,     3 }
        {     9,     7,     1,     8,     9,     2,     3,    10,     1,     3 }
        {     9,    10,     3,     9,     2,     9,    10,     7,     6,     2 }
        {     9,    10,     6,     8,     1,     2,     8,     8,     2,     5 }
        {    10,     1,     5,    10,     9,     4,     9,     7,     3,     5 }
        {    10,     1,     9,     6,     8,    10,     6,     6,    10,     2 }
        {    10,     1,    10,     7,     2,    10,     1,     4,     2,     5 }
        {    10,     3,     1,     4,     1,     8,     5,     6,    10,     3 }
        {    10,     3,     5,     3,     6,     7,     5,     2,     5,    10 }
        {    10,     3,     7,     5,     3,     5,     2,     5,     7,     3 }
        {    10,     7,     1,     2,     3,     4,     6,     2,     9,    10 }
        {    10,     7,     1,     9,     3,     5,     4,    10,     1,     5 }
        {    10,     8,    10,     7,     2,     1,     3,     3,    10,     4 }
        {    10,     9,    10,     9,     2,     6,     2,     2,    10,     8 }
        {    10,    10,     2,     2,     7,     2,     8,     2,     7,    10 }
        {    10,    10,     6,     3,     4,     6,     7,    10,     4,     8 }
     */
}
