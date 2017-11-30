package 第二章_初级排序算法;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_2_1_11 {
    public static void insertion(int[] a) {
        int N = a.length, h = 1;
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(h);
        while (h < N / 3)
            list.add((h = 3 * h + 1));
        int[] hs = integerToInt(list.toArray());
        int cursor = hs.length - 1;
        while (cursor >= 0) {
            for (int i = hs[cursor]; i < N; i++) 
                for (int j = i; j >= hs[cursor] && a[j - 1] > a[j]; j--) {
                    int t = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = t;
                }
            cursor--;
        }
    }
    public static void main(String[] args) {
        int[] arr = intRandom_size(30);
        print(arr);
        insertion(arr);
        print(arr);
    }
}
