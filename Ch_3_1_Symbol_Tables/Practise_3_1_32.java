package Ch_3_1_Symbol_Tables;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_3_1_32 {
    public static void main(String[] args) {
        Practise_3_1_26.ST<Integer, String> st = new Practise_3_1_26.ST<Integer, String>();
        int N = 1000;
        Integer[] ascend = ascendIntegers(0, N - 1);
        Integer[] descend = descendIntegers(N - 1, 0);
        Integer[] allSame = intToInteger(allSameInts(N, 5));
        Integer[] twoValue = intToInteger(intsVrg(N, 1, 2));
//        for (Integer i : ascend)
//            st.put(i, "A");
//        StdOut.println(st);
//        for (Integer i : descend)
//            st.put(i, "A");
//        StdOut.println(st);
//        for (Integer i : allSame)
//            st.put(i, "A");
//        StdOut.println(st);
        for (Integer i : twoValue)
            st.put(i, "A");
        StdOut.println(st);
    }
}
