package Ch_2_1_Elementary_Sorts;

import static Tool.ArrayGenerator.*;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_1_30 {
    static class Wrapper implements Comparable<Wrapper>{
        int t; double time;
        Wrapper(int t, double time) { this.t = t; this.time = time; }
        public int compareTo(Wrapper that) {
            return time < that.time ? -1 : time > that.time ? 1 : 0;
        }
        public String toString() { return String.format("t = %d time = %.3f", t, time); }
    }
    public static int[] incs(int t) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        int i = 1;
        while (list.get(list.size() - 1) < 1000000)
            list.add((int)Math.pow(t, i++)); 
        int[] a = IntegerToInt(list.toArray());
        int k = 0, j = a.length - 1;
        while (k < j) {
            int te = a[k];
            a[k] = a[j];
            a[j] = te;
            k++; j--;
        }
        return a;
    }
    /*
     * 对 Wrapper[] 排序
     */
    public static void shell(Comparable[] a) {
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
    }
    /*
     * 不更改原数组的排序，只是为了获取耗时时长
     */
    public static double shell(int[] a, int t) {
        a = copy(a);
        int[] incs = incs(t);
        int N = a.length;
        Stopwatch timer = new Stopwatch();
        for (int k = 0; k < incs.length; k++)
            for (int h = incs[k], i = h; i < N; i++) {
                int te = a[i], j;
                for (j = i - h; j >= 0 && te < a[j]; j -= h)
                    a[j + h] = a[j];
                a[j + h] = te;
            }
        return timer.elapsedTime();
    }
    public static Wrapper find(ArrayList<Wrapper> ws, int t) {
        for (Wrapper w : ws)
            if (w.t == t) return w;
        return null;
    }
    public static Wrapper[] wrapperSortedByTime(int[] a) {
        ArrayList<Wrapper> list = new ArrayList<Wrapper>();
        int T = 10;
        for (int i = 1; i < T; i++) {
            for (int t = 2; t < 20; t++) {
                double time = shell(a, t);
                Wrapper w = find(list, t);
                if (w == null)
                    list.add(new Wrapper(t, time));
                else {
                    list.remove(w);
                    list.add(new Wrapper(t, time + w.time));
                }
            }
            StdOut.printf("第 %d 轮结束\n", i);
        }
        for (Wrapper w : list) w.time /= T;
        Wrapper[] arr = new Wrapper[list.size()];
        for (int i = 0; i < list.size(); i++)
            arr[i] = list.get(i);
        shell(arr);
        return arr;
    }
    public static void main(String[] args) {
        int[] a = ints(1000000, 1, 10000000);
        Wrapper[] ws = wrapperSortedByTime(a);
        for (int i = 0; i < 3; i++) 
            StdOut.printf("t = %d averageTime : %.3f increments = %s\n", 
                    ws[i].t, ws[i].time, Arrays.toString(incs(ws[i].t)));
    }
    // output
    /*
     *  第 1 轮结束
        第 2 轮结束
        第 3 轮结束
        第 4 轮结束
        第 5 轮结束
        第 6 轮结束
        第 7 轮结束
        第 8 轮结束
        第 9 轮结束
        t = 2 averageTime : 0.810 increments = [1048576, 524288, 262144, 131072, 65536, 32768, 16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1]
        t = 5 averageTime : 0.811 increments = [1953125, 390625, 78125, 15625, 3125, 625, 125, 25, 5, 1]
        t = 3 averageTime : 0.876 increments = [1594323, 531441, 177147, 59049, 19683, 6561, 2187, 729, 243, 81, 27, 9, 3, 1]
     */
}
