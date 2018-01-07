package 第三章_符号表;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_3_1_31 {
    public static String randomStr() {
        StringBuilder sb = new StringBuilder();
        int len = StdRandom.uniform(2, 51);
        for (int i = 0; i < len; i++)
            sb.append((char)('a' + StdRandom.uniform(26)));
        return sb.toString();
    }
    public static void shuffle(String[] sa) {
     // 打乱
        for (int i = 0; i < sa.length; i++) {
            int r = i + StdRandom.uniform(sa.length - i);
            String t = sa[r];
            sa[r] = sa[i];
            sa[i] = t;
        }
    }
    public static void main(String[] args) {
        Practise_3_1_26.ST<String, Integer> st = new Practise_3_1_26.ST<String, Integer>();
        Set<String> set = new HashSet<String>();
        int M = 40000, N = 10 * M;
        String[] useForHit = new String[N];
        /*
         * 用这个数组初始化一张符号表
         */
        String[] useForInit = new String[M];
        for (int i = 0, j = 0; i < M;) {
            String s = randomStr();
            useForInit[i] = s;
            
            if (set.contains(s)) 
                continue;
            else    
                set.add(s);
            
            ++i;
            
            int cnt = 10;
            while (cnt-- > 0) 
                useForHit[j++] = s;
        }
        shuffle(useForHit);
        shuffle(useForInit);
        /*
         * 创建一个未命中数组
         */
        String[] useForHitFail = new String[N];
        for (int i = 0, j = 0; i < M;) {
            String s = randomStr();
            
            if (set.contains(s)) 
                continue;
            else    
                set.add(s);
            
            ++i;
            
            int cnt = 10;
            while (cnt-- > 0) 
                useForHitFail[j++] = s;
        }
        shuffle(useForHitFail);
        
        StdOut.println("开始初始化符号表");
        
        // 计时
        Stopwatch timer = new Stopwatch();
        for (String s : useForInit) // 初始化符号表
            st.put(s, 1);
        for (String s: useForHit) // 成功的命中
            st.get(s);
        for (String s : useForHitFail) // 失败的命中
            st.get(s);
        StdOut.printf("总耗时 : %.3f\n", timer.elapsedTime());
    }
    // output
    /*
     *  开始初始化符号表
        总耗时 : 3.022

     */
}
