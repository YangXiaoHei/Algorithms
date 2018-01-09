package 第三章_二叉查找树;

public class Practise_3_2_03 {
    public static void main(String[] args) {
        /*
         * A X C S E R H 共有 7 个结点，我们知道一个3层满二叉树的结点总共有 2^3-1 = 7
         * 
         * 所以我们的目标是用这7个结点构造一棵满二叉树
         * 
         * 可以由中序遍历反推，若要构造出一棵中序遍历结果为 A C E H R S X 的满二叉树
         * 
         * 根结点必定是 H，也就是说排列的第一个插入元素必须是 H
         * 
         * 接下来仅次于根结点的两个结点必须是 C 和 S，只有这样，才能让剩下的元素填满 C 和 S
         * 的左右孩子，而不会留下空链接
         * 
         * 因此可行的排列有
         * 
         * H C E A S R X
         * H C E A S X R
         * H C A E S R X
         * H C A E S X R
         * H S R X C A E
         * H S R X C E A
         * H S X R C A E
         * H S X R C E A
         * 
         * 
         */
    }
}
