package 第二章_快速排序;

public class Practise_2_3_15 {
    public static void main(String[] args) {
        /*
         * 假设螺丝是 A0 A1 A2...
         * 假设螺帽是 B0 B1 B2 ...
         * 
         * step1 ：把螺丝螺帽分成两堆
         * 有 A8 A2 A5 A6 A4 A2 A1 A3 A9 A0  
         * 有 B1 B8 B0 B3 B5 B2 B9 B0 B1 B7
         * 
         * 取出螺丝 A8，将螺帽分成大于和小于 A8 的两堆，同时找出了 B8
         * 
         * A8 和 B8 配对成功
         * 
         * 此时螺帽成为如下两堆
         * B1 B0 B3 B5 B2 B0 B1 B7    B9
         * 此时用找出来的 B8 把螺丝分成大于和小于 B8 的两堆
         * A2 A5 A6 A4 A2 A1 A3 A0    A9
         * 
         * A9 和 B9 配对成功
         * 
         * 取出螺丝 A2，将螺帽分成大于和小于 A2 的两堆，同时找出了 B2
         * 
         * A2 和 B2 配对成功
         * 
         * 此时螺帽分成如下两堆
         * B1 B0     B3 B5 B0 B1 B7
         * 此时用找出来的 B2 把螺丝分成大于和小于 B2 的两堆
         * A0 A1     A5 A6 A4 A2 A3 
         * 
         * 重复上述步骤，直到得到所有匹配
         * 
         */
    }
}
