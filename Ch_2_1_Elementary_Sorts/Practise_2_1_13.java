package Ch_2_1_Elementary_Sorts;

public class Practise_2_1_13 {
    public static void main(String[] args) {
        /*
         * 其实这个问题就是如何排序 1111111111111 2222222222222 3333333333333 4444444444444 这个序列
         * 这里我们使用 1 2 3 4 分别代表 spades hearts clubs diamonds
         * 每个数字各有 13 个
         * 
         * 选择排序法 :
         * 翻开第 k (k < 13) 张和第 i (i > 0) 张，找到 spades 就停止，然后交换 spades 和 第 k 张的位置
         * 翻开第 k (12 < k < 25) 张和第 i (i > 12) 张，找到 hearts 就停止，然后交换 hearts 和 第 k 张的位置
         * 翻开第 k (25 < k < 38) 张和第 i (i > 25) 张，找到 clubs 就停止，然后交换 clubs 和 第 k 张的位置
         * 排序结束
         * 
         * 插入排序法 :
         * 从第 k (k > 0) 张开始，检查当前牌和前一张牌，如果后面位置的花色小，就交换两张牌，
         * 交换后，重复前面步骤，一旦前面花色较小，就停止，然后让 k 递增，并重复整个完整步骤
         * 
         * 希尔排序法 :
         * 40-sorting
         * 13-sorting
         * 4-sorting
         * 1-sorting
         */
    }
}
