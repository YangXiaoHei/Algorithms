package Ch_2_5_Applications;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_5_27 {
    public static int[] indirectSort(int[] a) {
        int[] p = new int[a.length];
        for (int i = 0; i < a.length; i++)
            p[i] = i;
        for (int i = 0; i < a.length; i++) {
            int t = a[p[i]], j, index = p[i];
            for (j = i - 1; j >= 0 && t < a[p[j]]; j--)
                p[j + 1] = p[j];
            p[j + 1] = index;
        }
        return p;
    }
    public static void main(String[] args) {
        int[] a = ints(0, 10);
        print(a);
        int[] p = indirectSort(a);
        StdOut.println();
        for (int i = 0; i < p.length; i++)
            StdOut.print(a[p[i]] + " ");
    }
    // output
    /*
     * 
        0   1   2   3   4   5   6   7   8   9   10  
        0   7   1   10  9   6   5   3   4   2   8   
        
        0 1 2 3 4 5 6 7 8 9 10 
     */
}
