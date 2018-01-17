package Ch_2_4_Priority_Queues;

public class Practise_2_4_09 {
    public static void main(String[] args) {
        /*
         * 我们知道，由 A B C D E 构造出来的堆只有三层，并且根结点一定是 E
         * 而第二层一定是 C D，第三层一定是 A B
         * 
         * 所以所有可能的情况如下所列四种
         * 
         * E  C D   A B
         * 
         * E  C D   B A
         * 
         * E  D C   A B 
         * 
         * E  D C   B A
         * 
         * 对于序列 A A A B B 这五个元素构造出来的堆也只有三层，根结点一定是 B
         * 第二层一定是 A B 第三层一定是 A A
         * 
         * 所以所有可能的情况如下所列两种
         * 
         * B  A B  A A
         * B  B A  A A
         */
    }
}
