package Ch_2_2_MergeSort;

public class Practise_2_2_07 {
    public static void main(String[] args) {
        /*
         * 因为当 N 是 2的幂次方时， 比较次数 C(N) = N * log(N)
         * 
         * 因此 C(N + 1) - C(N) = (N + 1) *log(N + 1) - N * log(N) 
         * 
         * 因为 N 和 log(N) 都是单调递增函数，而单调递增函数之积仍然是单调递增函数
         * 
         * 所以 C(N) 是单调递增的
         */
    }
}
