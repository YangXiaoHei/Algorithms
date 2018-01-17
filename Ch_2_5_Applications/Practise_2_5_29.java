package Ch_2_5_Applications;

import java.io.*;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_5_29 {
    enum OrderType {
      ASCEND,
      DESCEND
    };
    public static Comparator<File> sortByLength(OrderType type) {
        return new Comparator<File>() {
            public int compare(File f1, File f2) {
                switch (type) {
                    case ASCEND : return f1.length() < f2.length() ? -1 : 
                                         f1.length() > f2.length() ? 1 : 0;
                    case DESCEND : return f1.length() > f2.length() ? -1 : 
                                          f1.length() < f2.length() ? 1 : 0;
                }
                throw new RuntimeException("unkonw error");
            }
        };
    }
    public static Comparator<File> sortByLastModified(OrderType type) {
        return new Comparator<File>() {
            public int compare(File f1, File f2) {
                switch (type) {
                    case ASCEND : return f1.lastModified() < f2.lastModified() ? -1 : 
                                         f1.lastModified() > f2.lastModified() ? 1 : 0;
                    case DESCEND : return f1.lastModified() > f2.lastModified() ? -1 : 
                                          f1.lastModified() < f2.lastModified() ? 1 : 0;
                }
                throw new RuntimeException("unkonw error");
            }
        };
    }
    public static Comparator[] cmps(String s) {
        if (s == null || s.length() == 0) return null;
        String[] split = s.split("\\s+");
        int count = 0;
        ArrayList<Comparator> list = new ArrayList<Comparator>();
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("-t")) {
                if (s.contains("-d")) {
                    list.add(sortByLastModified(OrderType.DESCEND));
                } else {
                    list.add(sortByLastModified(OrderType.ASCEND));
                }
            }
            if (split[i].equals("-l")) {
                if (s.contains("-d")) {
                    list.add(sortByLength(OrderType.DESCEND));
                } else {
                    list.add(sortByLength(OrderType.ASCEND));
                }
            }
        }    
        int i = 0;
        Object[] o = list.toArray();
        Comparator[] c = new Comparator[o.length];
        for (Object oo : o)
            c[i++] = (Comparator)oo;
        return c;
    }
    public static void shell(File[] files, Comparator[] cmps) {
        int N = files.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                File t = files[i]; int j;
                for (j = i - h; j >= 0 && less(t, files[j], cmps); j -= h) 
                    files[j + h] = files[j];
                files[j + h] = t;
            }
            h /= 3;
        }
    }
    private static boolean less(File f1, File f2, Comparator[] cmps) {
        return compare(f1,f2, cmps) < 0;
    }
    private static int compare(File f1, File f2,  Comparator[] cmps) {
        for (int i = 0; i < cmps.length; i++) {
            if (cmps[i].compare(f1, f2) < 0) return -1;
            if (cmps[i].compare(f1, f2) > 0) return 1;
        }
        return 0;
    }
    public static void main(String[] args) {
        File file = new File("/Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用");
        File[] files = file.listFiles();
        String s = "-l -t -a";
        shell(files, cmps(s));
        for (File f : files)
            StdOut.println(f);
    }
    // output
    /*
     *  /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_15.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_01.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_05.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_03.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_27.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_31.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_28.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_07.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_25.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_14.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_19.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_06.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_04.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_02.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_08.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_23.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_12.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_10.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_26.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_22.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_16.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_24.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_30.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_18.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_29.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_17.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_11.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_13.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_32.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_09.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_20.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_33.java
        /Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用/Practise_2_5_21.java

     */
}
