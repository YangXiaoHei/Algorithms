package 第三章_二叉查找树;

public class Practise_3_2_20 {
    public static void main(String[] args) {
        /*
         * rank(K lo) 方法只会自根结点向 lo 结点靠拢，访问的结点不会小于 lo
         * rank(K hi) 方法只会自根结点向 hi 结点靠拢，访问的结点不会大于 hi
         * 
         * 因为 rank(K lo) 和 rank(K hi) 两次方法调用访问了 lo ~ hi 内的所有结点
         * 
         * 同时在访问过程中，rank() 的递归调用深度最多不过树高，所以
         * size(K lo, K hi) 方法所需运行时间最多为树高的倍数加上查找范围内的键的数量
         * 
         * 
         */
    }
}
