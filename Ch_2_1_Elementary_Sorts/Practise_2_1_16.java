package Ch_2_1_Elementary_Sorts;

import edu.princeton.cs.algs4.*;

public class Practise_2_1_16 {
    public static void sort_A(Double[] a) {
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) 
                for (int j = i; j >= h && a[j].compareTo(a[j - h]) < 0; j -= h) {
                    Double t = a[j];
                    a[j] = a[j - h];
                    a[j - h] = t;
                }
            h /= 3;
        }
    }
    public static void sort_B(Double[] a) {
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++)
                for (int j = i; j >= h && a[j].compareTo(a[j - h]) < 0; j -= h) {
                    Double t = new Double(a[i].doubleValue());
                    a[j] = a[j - h];
                    a[j - h] = t;
                }
            h /= 3;
        }
    }
    public static Double[] shallowCopy(Double[] a) {
        Double[] shallowCopy = new Double[a.length];
        for (int i = 0; i < a.length; i++)
            shallowCopy[i] = a[i];
        return shallowCopy;
    }
    public static boolean eachCheck(Double[] src, Double[] sorted) {
        for (int i = 0; i < src.length; i++) 
            for (int j = 0; j < sorted.length; j++) 
                if (src[i].equals(sorted[j]) && src[i] != sorted[j]) 
                    return false;
        return true; 
    }
    public static boolean check(Double[] a) {
        Double[] copy = shallowCopy(a);
        
        // false
        sort_B(a);
        
        // true
//      sort_A(a);
        
        return eachCheck(copy, a);
    }
    
    public static void main(String[] args) {
        Double[] d = new Double[] {3.0, 2.0, 1.0, 4.0};
        StdOut.println(check(d));
    }
    // output
    /*
     * false
     */
}
