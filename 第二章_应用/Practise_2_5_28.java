package 第二章_应用;

import java.io.File;
import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_2_5_28 {
    public static void main(String[] args) {
        File directory = new File("/Users/bot/Desktop/Algorithms4/Algorithms4/src/第二章_应用");
        File[] files = null;
        if (directory.exists() && 
            directory.isDirectory() &&
            (files = directory.listFiles()) != null) {
        }
        Arrays.sort(files);
        for (int i = 0; i < files.length; i++)
            StdOut.println(files[i].getName());
    }
    // output
    /*
     *  Practise_2_5_01.java
        Practise_2_5_02.java
        Practise_2_5_03.java
        Practise_2_5_04.java
        Practise_2_5_05.java
        Practise_2_5_06.java
        Practise_2_5_07.java
        Practise_2_5_08.java
        Practise_2_5_09.java
        Practise_2_5_10.java
        Practise_2_5_11.java
        Practise_2_5_12.java
        Practise_2_5_13.java
        Practise_2_5_14.java
        Practise_2_5_15.java
        Practise_2_5_16.java
        Practise_2_5_17.java
        Practise_2_5_18.java
        Practise_2_5_19.java
        Practise_2_5_20.java
        Practise_2_5_21.java
        Practise_2_5_22.java
        Practise_2_5_23.java
        Practise_2_5_24.java
        Practise_2_5_25.java
        Practise_2_5_26.java
        Practise_2_5_27.java
        Practise_2_5_28.java
        Practise_2_5_30.java
        Practise_2_5_31.java
        Practise_2_5_32.java
        Practise_2_5_33.java
     */
}
