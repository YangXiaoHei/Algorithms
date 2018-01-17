package Ch_3_1_Symbol_Tables;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_3_1_07 {
    public static void main(String[] args) {
        
        for (int N = 10, j = 1; j <= 6; N *= 10, j++) {
            ST<Integer, Integer> st = new ST<Integer, Integer>();
            for (Integer i : Integers(N, 0, 1000 - 1)) {
                if (st.contains(i))
                    st.put(i, st.get(i) + 1);
                else
                   st.put(i, 1); 
            }
            StdOut.printf("规模 : %d, 总共能找到 : %d 个不同的键\n",N, st.size());
        }
    }
    // output
    /*
     *  规模 : 10, 总共能找到 : 10 个不同的键
        规模 : 100, 总共能找到 : 94 个不同的键
        规模 : 1000, 总共能找到 : 637 个不同的键
        规模 : 10000, 总共能找到 : 1000 个不同的键
        规模 : 100000, 总共能找到 : 1000 个不同的键
        规模 : 1000000, 总共能找到 : 1000 个不同的键

     */
}
