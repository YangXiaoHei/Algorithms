package Ch_2_3_Quicksort;

public class Practise_2_3_13 {
    public static void main(String[] args) {
        /*
         * 最坏情况是 N-1
         * 比如对于升序序列，降序序列来说，每次切分都会得到一个 0 数组，一个 N - 1 数组
         * 所以递归深度仅比数组长度少一
         * 
         * 最佳情况是 logN  
         * 
         * 比如所有元素都相同的数组，每次切分差不多刚好分割成相等长度的两个子数组
         * 所以递归深度约等于 logN
         * 
         * 平均情况是 NlogN 
         * 
         */
    }
}
