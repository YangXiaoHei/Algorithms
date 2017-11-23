package 第二章_初级排序算法;

import edu.princeton.cs.algs4.*;

public class Text_RandomArray {
    public static Integer[] sourceArr(int N) {
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) 
            arr[i] = new Integer(StdRandom.uniform(-100, 100));
        return arr;
    }
    public static Integer[] sourceArr(int N, int low, int high) {
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) 
            arr[i] = new Integer(StdRandom.uniform(low, high));
        return arr;
    }
}
