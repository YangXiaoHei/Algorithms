package Review;

import static Tool.ArrayGenerator.*;

public class Practise_2_1_01 {
    public static void selection(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++)
                if (a[min] > a[j]) min = j;
            if (min != i) { // 避免自己和自己交换
                int t = a[min];
                a[min] = a[i];
                a[i] = t;
            }
        }
    }
    public static void main(String[] args) {
        int[] a = ints(0, 10);
        selection(a);
        print(a);
        
        /*
         * E A S Y Q U E S T I O N
         * 
         * A E S Y Q U E S T I O N
         * 
         * A E S Y Q U E S T I O N
         * 
         * A E E Y Q U S S T I O N
         * 
         * A E E I Q U S S T Y O N
         * 
         * A E E I O U S S T Y Q N
         * 
         * A E E I O N S S T Y Q U
         * 
         * A E E I O N Q S T Y S U
         * 
         * A E E I O N Q S T Y S U 
         * 
         * A E E I O N Q S S Y T U
         * 
         * A E E I O N Q S S T Y U
         * 
         * A E E I O N Q S S T U Y
         * 
         * 
         * 
         * 
         * 
         */
    }
}
