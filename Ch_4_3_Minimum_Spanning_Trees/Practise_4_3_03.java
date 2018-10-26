package Ch_4_3_Minimum_Spanning_Trees;

public class Practise_4_3_03 {
    public static void main(String[] args) {
        /*
         * 假设最小生成树不唯一，那么假设在 G 中有两个不同的最小生成树 T1 和 T2，再假设 G 中
         * 有一条最小权重的边 f，该边 f 在 T1 而不在 T2 中，把 f 添加到 T2 中，此时便形成了一个
         * 闭环，因为在这个闭环中 f 比所有边权重都要小，那么此时删掉其他一条边而保留 f，便形成
         * 了一个新的最小生成树 T3，此时 T3 == T1
         */
    }
}
