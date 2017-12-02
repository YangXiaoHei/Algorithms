package 第二章_初级排序算法;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_1_34 {
    public static void shell(int[] a) {
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
    }
    public static void main(String[] args) {
        int[] sorted = intSorted_size(10);
        print(sorted);
        shell(sorted);
        print(sorted);
        
        int[] reverse = intReverseOrder_bounds(10, 0);
        print(reverse);
        shell(reverse);
        print(reverse);
        
        int[] allSame = intAllSame_size_key(10, 10);
        print(allSame);
        shell(allSame);
        print(allSame);
        
        int[] twoValue = int_size_vrg(10, 1, 2);
        print(twoValue);
        shell(twoValue);
        print(twoValue);
        
        int[] zero = new int[0];
        print(zero);
        shell(zero);
        print(zero);
        
        int[] one = intRandom_size(1);
        print(one);
        shell(one);
        print(one);
    }
    // output
    /*
     * 
        0    1    2    3    4    5    6    7    8    9    
        -92  -68  -48  -36  14   15   26   33   73   97   
        
        0    1    2    3    4    5    6    7    8    9    
        -92  -68  -48  -36  14   15   26   33   73   97   
        
        0    1    2    3    4    5    6    7    8    9    10   
        10   9    8    7    6    5    4    3    2    1    0    
        
        0    1    2    3    4    5    6    7    8    9    10   
        0    1    2    3    4    5    6    7    8    9    10   
        
        0    1    2    3    4    5    6    7    8    9    
        10   10   10   10   10   10   10   10   10   10   
        
        0    1    2    3    4    5    6    7    8    9    
        10   10   10   10   10   10   10   10   10   10   
        
        0    1    2    3    4    5    6    7    8    9    
        2    2    2    1    1    2    1    1    2    1    
        
        0    1    2    3    4    5    6    7    8    9    
        1    1    1    1    1    2    2    2    2    2    
        
        0    
        -75  
        
        0    
        -75  
     */
}
