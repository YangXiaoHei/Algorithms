package Review;

import edu.princeton.cs.algs4.StdOut;

public class Solution2 {
    public static int countAndSay(int n) {
        String[] arr = new String[n];
        arr[0] = "1";
        for (int i = 1; i < n; i++) {
            arr[i] = computeNext(arr[i - 1]);
            for (int j = i - 1; j >= 0 && arr[j] != null; j--) 
                arr[j] = null;
            System.gc();
        }
        return arr[n - 1].length();
    }
    public static String computeNext(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0, count = 0;
        while (j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                j++;
                count++;
            } else {
                sb.append(count + "" + s.charAt(i));
                i = j;
                count = 0;
            }
        }
        sb.append(count + "" + s.charAt(i));
        return sb.toString();
    }
    public static void main(String[] args) {
        int k = 1;
        while (true) {
            int len = countAndSay(k);
            StdOut.printf("n = %d length = %d\n", k++, len);
            System.gc();
        }
    }
}
