package 第二章_快速排序;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_3_12 {
    private static void parition(char[] a, int lo, int hi) {
        int l = lo, i = lo + 1, g = hi, v = a[lo];
        while (i <= g) {
            if      (a[i] < v) exch(a, l++, i++);
            else if (a[i] > v) exch(a, i, g--);
            else    i++;
        }
        StdOut.printf("l = %d  g = %d", l, g);
    }
    private static void exch(char[] a, int i, int j) {
        char t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        /*
         *  B A B A B A B A C A D A B R A
         *  👆👆                       👆
         *  
         *  A B B A B A B A C A D A B R A
         *    👆👆                     👆
         *  A B B A B A B A C A D A B R A
         *    👆  👆                    👆
         *    
         *  A A B B B A B A C A D A B R A
         *      👆  👆                  👆
         *      
         *  A A B B B A B A C A D A B R A
         *      👆    👆                👆
         *  A A A B B B B A C A D A B R A
         *        👆    👆              👆
         *  A A A B B B B A C A D A B R A
         *        👆      👆            👆
         *  A A A A B B B B C A D A B R A
         *          👆      👆          👆
         *  A A A A B B B B A A D A B R C
         *          👆      👆       👆
         *  A A A A A B B B B A D A B R C
         *            👆      👆     👆
         *  A A A A A B B B B A D A B R C
         *            👆      👆     👆
         *  A A A A A A B B B B D A B R C
         *              👆      👆    👆
         *  A A A A A A B B B B R A B D C
         *              👆      👆  👆
         *  A A A A A A B B B B B A R D C
         *              👆      👆👆
         *  A A A A A A B B B B B A R D C
         *              👆        👆
         *  A A A A A A A B B B B B R D C
         *                👆      👆👆  i > gt 跳出循环
         */
        // 验证如下
        char[] a = parseChar("B A B A B A B A C A D A B R A");
        parition(a, 0, a.length - 1);
        print(a);
    }
    // output
    /*
     * l = 7  g = 11
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  
        A   A   A   A   A   A   A   B   B   B   B   B   R   D   C   
     */
}
