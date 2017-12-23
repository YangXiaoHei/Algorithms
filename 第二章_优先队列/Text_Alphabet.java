package 第二章_优先队列;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Text_Alphabet {
    private static String[] alphabet = new String[26];
    static {
        for (int i = 1; i <= 26; i++)
            alphabet[i - 1] = String.format("%c", 'A' + i - 1);
    }
    public static String[] random(int count) {
        String[] s = new String[count];
        for (int i = 0; i < count; i++)
            s[i] = alphabet[StdRandom.uniform(alphabet.length)];
        return s;
    }
    public static String[] allRandom() {
        String[] s = new String[26];
        System.arraycopy(alphabet, 0, s, 0, 26);
        for (int i = 0; i < 25; i++) {
            int r = StdRandom.uniform(i, 26 - i);
            String t = s[r];
            s[r] = s[i];
            s[i] = t;
        }
        return s;
    }
    public static String[] allOrdered() {
        String[] s = new String[26];
        System.arraycopy(alphabet, 0, s, 0, 26);
        return s;
    }
    public static void printLetter(String[] s) {
        for (int i = 0; i < s.length; i++)
            StdOut.printf("%4d", i);
        StdOut.println();
        for (int i = 0; i < s.length; i++)
            StdOut.printf("%4s", s[i]);
        StdOut.println("\n");
    }
}
