package Ch_3_3_Balanced_Search_Trees;

import edu.princeton.cs.algs4.StdOut;

public class Practise_3_3_20 {
    public static int ipl(int N) {
        int h = (int)(Math.log(N + 1) / Math.log(2));
        int sum = 0;
        for (int i = 0; i < h; i++) 
            sum += i * (1 << i);
        return sum;
    }
    public static void main(String[] args) {
        int N = 63;
        StdOut.printf("大小为 : %d 内部路径长度 : %d\n",N, ipl(N));
    }
    // output
    /*
     * 大小为 : 63 内部路径长度 : 258

     */
}
