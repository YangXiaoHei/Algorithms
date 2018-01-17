package Ch_2_4_Priority_Queues;

public class Practise_2_4_20 {
    public static void main(String[] args) {
        /*
         * 假设一棵满二叉树，它的根结点高度为 h, 叶结点高度为 0
         * 
         * 那么高度为 h 这一层的交换次数最大为 h
         * 高度为 h - 1 这一层，最大交换次数为 2 * (h - 1)
         * 高度为 h - 2 这一层，最大交换次数为 4 * (h - 2)
         * ...
         * 
         * 总交换次数为 h + 2 * (h - 1) + 4 * (h - 2) + ... + 2^(h - 1)
         * 
         * = 2^(h + 1) - h - 2 = N - h + 1 < N
         * 
         * 因为比较次数是交换次数的两倍（在交换前会进行 less(j, j + 1) 和 less(k, j) 两次比较）
         * 所以最坏情况下比较次数少于 2N 
         */
    }
}
