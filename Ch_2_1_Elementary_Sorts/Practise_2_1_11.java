package Ch_2_1_Elementary_Sorts;

import static Tool.ArrayGenerator.*;
import java.util.*;

public class Practise_2_1_11 {
    public static void shell(int[] a) {
        int N = a.length, h = 1;
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(h);
        while (h < N / 3)
            list.add((h = 3 * h + 1));
        int[] hs = IntegerToInt(list.toArray());
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
        int[] arr = ints(30);
        print(arr);
        shell(arr);
        print(arr);
    }
    // output
    /*
     * 
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26   27   28   29   
        -1   99   -31  48   52   83   57   -99  17   51   36   73   -9   -51  49   51   67   -40  50   10   -16  24   3    65   84   19   -48  22   85   78   
        
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26   27   28   29   
        -99  -51  -48  -40  -31  -16  -9   -1   3    10   17   19   22   24   36   48   49   50   51   51   52   57   65   67   73   78   83   84   85   99   

     */
}
