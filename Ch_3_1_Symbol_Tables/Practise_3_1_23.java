package Ch_3_1_Symbol_Tables;

import static Tool.ArrayGenerator.*;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class Practise_3_1_23 {
    private static int compares;
    public static int binarySearch(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            int less = compare(a[mid], key);
            if      (less < 0) lo = mid + 1;
            else if (less > 0) hi = mid - 1;
            else    return mid;
        }
        return -1;
    }
    public static int compare(int i, int j) {
        compares++;
        return i - j;
    }
    public static void main(String[] args) {
        int N = 1000, k = 1;
        int[] a = ints(0, N - 1);
        Arrays.sort(a);
        StdOut.println("k 的索引为 : " + binarySearch(a, k));
        StdOut.println("比较次数 : " + compares);
        StdOut.println("N 的二进制长度 : " + Integer.toBinaryString(N).length());
    }
    // output
    /*
     *  k 的索引为 : 1
        比较次数 : 10
        N 的二进制长度 : 10

     */
}
