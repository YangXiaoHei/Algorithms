package Ch_2_4_Priority_Queues;

import edu.princeton.cs.algs4.StdOut;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;
import static Ch_2_4_Priority_Queues.Practise_2_4_37.*;

public class Practise_2_4_38 {
    public static void main(String[] args) {
        int N = 30;
        /*
         * 正序
         */
        int[] a = ascendInts(0, N - 1);
        MaxPQ<Integer> pq = new MaxPQ<Integer>(N);
        for (int i : a) pq.insert(i);
        while (!pq.isEmpty())
           StdOut.println(pq.delMax());
        StdOut.println("=================");
        /*
         * 逆序
         */
        a = descendInts(N - 1, 0);
        for (int i : a) pq.insert(i);
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
        StdOut.println("=================");
        /*
         * 全部相等
         */
        a = allSameInts(N - 1, 1);
        for (int i : a) pq.insert(i);
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
        StdOut.println("=================");
        /*
         * 只有两个值
         */
        a = intsVrg(N - 1, 1, 2);
        for (int i : a) pq.insert(i);
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
        StdOut.println("=================");
    }
    // output
    /*
     *  29
        28
        27
        26
        25
        24
        23
        22
        21
        20
        19
        18
        17
        16
        15
        14
        13
        12
        11
        10
        9
        8
        7
        6
        5
        4
        3
        2
        1
        0
        =================
        29
        28
        27
        26
        25
        24
        23
        22
        21
        20
        19
        18
        17
        16
        15
        14
        13
        12
        11
        10
        9
        8
        7
        6
        5
        4
        3
        2
        1
        0
        =================
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        =================
        2
        2
        2
        2
        2
        2
        2
        2
        2
        2
        2
        2
        2
        2
        2
        2
        2
        2
        2
        1
        1
        1
        1
        1
        1
        1
        1
        1
        1
        =================

     */
}
