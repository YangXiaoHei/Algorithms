package 第二章_归并排序;

public class Practise_2_2_05 {
    public static void main(String[] args) {
        /*
         * N = 39
         * 
         * 自顶向下 : 假设要归并两个大小分别为 3 2 的数组，写作 m32
         * 
         * sort(0, 39) -> sort(0, 19) -> sort(0, 9) -> sort(0, 4) -> sort(0, 2) -> sort(0, 1) 
         * 
         * -> m11 -> m21 -> sort(3, 4) -> m11 -> m32 -> sort(5, 9) -> sort(5, 7) -> sort(5, 6)
         * 
         * -> m11 -> m21 -> sort(8, 9) -> m11 -> m32 -> m55 -> sort(10, 19) -> sort(10, 14) ->
         * 
         * -> sort(10, 12) -> sort(10, 11) -> m11 -> m21 -> sort(13, 14) -> m11 -> sort(15, 19)
         * 
         * -> sort(15, 17) -> sort(15, 16) -> m11 -> m21 -> sort(18, 19) -> m11 -> m32 -> m55 ->m1010
         * 
         * -> sort(20, 39) -> sort(20, 29) -> sort(20, 24) -> sort(20, 22) -> sort(20, 21) ->
         * 
         * m11 -> m21 -> sort(23, 24) -> m11 -> m32 -> sort(25, 29) -> sort(25, 27) -> sort(25, 26) ->
         * 
         * -> m11 -> m21 -> sort(28, 29) -> m11 -> m32 -> m55 -> sort(30, 39) -> sort(30, 34) -> 
         * 
         * -> sort(30, 32) -> sort(30, 31) -> m11 -> m21 -> sort(33, 34) -> m11 -> m32 -> sort(35, 39)
         * 
         * -> sort(35, 37) -> sort(35, 36) -> m11 -> m21 -> sort(38, 39) -> m11 -> m32 -> m55 -> m1010
         * 
         * ->  m2020
         * 
         */
    }
}
