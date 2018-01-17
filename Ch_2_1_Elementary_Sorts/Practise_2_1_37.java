package Ch_2_1_Elementary_Sorts;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_1_37 {
    public static int[] sorted95PercentageAndOthersRandom(int N) {
        int[] sorted = ascendInts((int)(N * 0.95));
        int[] random = ints((int)(N * 0.05));
        int[] combine = new int[sorted.length + random.length];
        int i = 0;
        if (StdRandom.bernoulli()) {
            while (i < sorted.length) { combine[i] = sorted[i]; i++; }
            while (i < sorted.length + random.length) { combine[i] = random[i - sorted.length]; i++; }
        } else {
            while (i < random.length) { combine[i] = random[i]; i++; }
            while (i < sorted.length + random.length) { combine[i] = sorted[i - random.length]; i++; }
        }
        return combine;
    }
    public static int[] within10(int N) {
        return whthinDistance(N, 10);
    }
    public static int[] sorted95PercentageAndOthersRandomOrder(int N) {
        int[] sorted = intsNoDupli(N);
        Arrays.sort(sorted);
        if ((N * 0.05) < 1) return sorted; 
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : sorted) list.add(i);
        Set<Integer> indexs = new HashSet<Integer>();
        int randomCount = (int)(N * 0.05);
        while (randomCount-- > 0) {
            int index = StdRandom.uniform(list.size());
            while (indexs.contains(index))
                index = StdRandom.uniform(list.size());
            indexs.add(index);
        }
        int[] indexsArr = IntegerToInt(indexs.toArray());
        for (int i : indexsArr) {
            int destIndex = StdRandom.uniform(list.size());
            list.add(destIndex, list.remove(i));
        }
        return IntegerToInt(list.toArray());
    }
    public static double shell(int[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                int t = a[i], j;
                for (j = i - h; j >= 0 && t < a[j]; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
            h /= 3;
        }
        return timer.elapsedTime();
    }
    public static void main(String[] args) {
        StdOut.printf("对照试验 : %.3f\n", shell(ints(80000)) * 100);
        StdOut.printf("95%%有序其余随机 : %.3f\n", shell(sorted95PercentageAndOthersRandom(80000)) * 100);
        StdOut.printf("距最终位置不超过10 : %.3f\n", shell(within10(80000)) * 100);
        StdOut.printf("5%%随机分布 : %.3f\n", shell(sorted95PercentageAndOthersRandomOrder(80000)) * 100);
    }
    // output
    /*
     *  对照试验 : 0.070000
        95%有序其余随机 : 0.020000
        距最终位置不超过10 : 0.014000
        5%随机分布 : 0.073000
     */
}
