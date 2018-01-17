package Ch_3_1_Symbol_Tables;

import edu.princeton.cs.algs4.*;

public class Practise_3_1_09 {
    public static void main(String[] args) {
        int count = 0; String last = null;
        String[] all = new In("/Users/bot/Desktop/algs4-data/tale.txt").readAll().split("\\s+");
        ST<String, Integer> st = new ST<String, Integer>();
        for (String s : all) {
            if (s.length() < 10) continue;
            count++;
            last = s;
            if (st.contains(s))
                st.put(s, st.get(s) + 1);
            else
                st.put(s, 1);
        }
        StdOut.printf("最后一个插入的单词是 : %s 此前总共处理了 %d 个单词\n", last, count - 1);
    }
    // output
    /*
     * 最后一个插入的单词是 : known 此前总共处理了 135642 个单词

     * 最后一个插入的单词是 : faltering 此前总共处理了 14345 个单词

     * 最后一个插入的单词是 : disfigurement 此前总共处理了 4578 个单词

     */
}
