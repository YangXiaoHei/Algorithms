package Ch_2_2_Mergesort;

public class Practise_2_2_03 {
    private static int[] aux;
    public static void merge(int[] a) {
        int N = a.length;
        aux = new int[N];
        for (int sz = 1; sz < N; sz += sz) 
            for (int lo = 0; lo < N - sz; lo += 2 * sz)
                mergeSort(a, lo, lo + sz - 1, Math.min(lo + 2 * sz - 1, N - 1));
    }
    private static void mergeSort(int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)
            if      (i > mid)       a[k] = aux[j++];
            else if (j > hi)        a[k] = aux[i++];
            else if (a[j] < a[i])   a[k] = aux[j++];
            else                    a[k] = aux[i++];
    }
    public static void main(String[] args) {
        /*
         * E A S Y Q U E S T I O N
         * 
         * A E   S Y   Q U   E S   I T   N O    sz = 1
         * 
         * A E S Y   E Q S U   I N O T          sz = 2
         * 
         * A E E Q S S U Y   I N O T            sz = 4
         * 
         * A E E I N O Q S S T U Y              sz = 8
         */
    }
}
