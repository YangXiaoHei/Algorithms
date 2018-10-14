package Ch_4_1_Undirected_Graphs;

public class Practise_4_1_04 {
    public static void main(String[] args) {
        /*
         * 很简单，提一点小优化
         * 
         * 在 Bag 中提供一个查询元素数量的接口 int size();
         * 
         * 对于查询 hasEdge(v, w)，我们比较 bag[v].size() 和 bag[w].size() 谁
         * 比较小，选择小的那个遍历它的所有邻接点，这样可以少遍历一些点
         */
    }
}
