package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_25 {
    public static void main(String[] args) {
        /*
         *  设有两个数 a b, 要求 a b 最大公约数
         *  欧几里得算法计算过程如下 : 假如在 r4 = 0
         *  
         *  a = q0 b + r0
            b = q1 r0 + r1
            r0 = q2 r1 + r2
            r1 = q3 r2 + r3
            r2 = q4 r3
         * 
         * 我们可以知道 第 k 个余数满足
         * 
         * r(k-2) = qr(k-1) + r(k)
         * 
         * 因为 r(k-2) 会不断的减去 r(k-1)，因此 r(k) 一定小于 r(k-1)
         * 因此必定存在第 N 步使得 r(k) 等于 0， 此时 r(k - 1) 即是最大公约数
         * 
         * 我们知道在第 N 步时，r(N-2) = q(N)r(N-1) 因此 r(N-1) 能整除 r(N-2)
         * 因此 r(N-3) = q(N-1)r(N-2) + r(N-1) 
         * = q(N-1)r(N-2) + q(N)r(N-2) 
         * = (q(N) + q(N-1)) * r(N-2)
         * 
         * 以此类推，可以知道 r(N-1) 能够整除之前步骤的所有余数，包括 a 和 b
         * 
         * 假设最大公约数为 g
         * 根据定义，a和b可以写成g的倍数：a = mg、b = ng，
         * 其中 m 和 n 是自然数。因为r0 = a − q0b = mg − q0ng = (m − q0n)g，所以g整除r0。
         * 同理可证 g 整除每个余数r1, r2, ..., rN-1。因为最大公约数g整除rN−1，因而g ≤ rN−1。
         * 
         */
    }
}
