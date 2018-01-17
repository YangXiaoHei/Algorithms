package Ch_3_1_Symbol_Tables;

import edu.princeton.cs.algs4.*;

public class Practise_3_1_06 {
    public static void main(String[] args) {
        /*
         * 输入中的单词总数 W 和不同单词总数 D
         */
        int minLen = Integer.parseInt(args[0]);
        ST<String, Integer> st = new ST<String, Integer>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLen) continue;
            /*
             * 在这里每个单词都对应一次 put,因此此循环中 put 总数为 W
             * 
             * 不同的单词总数为 D, 那么假设这 D 个单词先放入符号表，那么一次 get 都不会发生
             * 接下来每个单词都会触发 get, 因为不同的单词已经都放完了，
             * 接下来放的每一个都会和已有键重复， 因此此循环中 get 总数等于 W - D
             */
            if (!st.contains(word)) st.put(word, 1);
            else                    st.put(word, st.get(word) + 1);
        }
        String max = " ";
        /*
         * put 1次
         */
        st.put(max, 0);
        for (String word : st.keys()) 
            if (st.get(word) > st.get(max)) // 这里的 get 次数为不同的单词数 * 2 = 2 * D
                max = word;
        StdOut.println(max + " " + st.get(max)); // 这里有的 get 1次
        /*
         * 所以总的 put 次数为 W + 1
         * 总的 get 次数为 W - D + 2 * D + 1 = W + D + 1
         */
    }
}
