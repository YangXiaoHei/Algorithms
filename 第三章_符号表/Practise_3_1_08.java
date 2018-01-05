package 第三章_符号表;

import edu.princeton.cs.algs4.*;

public class Practise_3_1_08 {
    public static void main(String[] args) {
        String[] all = new In("/Users/bot/Desktop/algs4-data/tale.txt").readAll().split("\\s+");
        ST<String, Integer> st = new ST<String, Integer>();
        for (String s : all) {
            if (s.length() < 10) continue;
            if (st.contains(s))
                st.put(s, st.get(s) + 1);
            else
                st.put(s, 1);
        }
        String max = " ";
        st.put(max, 0);
        for (String key : st.keys()) {
            if (st.get(key) > st.get(max))
                max = key;
        }
        StdOut.printf("出现频率最高的单词是 : %s 总共出现 %d 次\n", max, st.get(max));
    }
    // output
    /*
     * 出现频率最高的单词是 : monseigneur 总共出现 101 次

     */
}
