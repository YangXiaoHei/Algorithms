package 第三章_符号表;

public class Practise_3_1_10 {
    public static void main(String[] args) {
        /*
         * E A S Y Q U E S T I O N
         * 
         * 插入 E
         * 
         * header -> E 0
         * 
         * 插入 A
         * 
         * header -> A -> E 1
         * 
         * 插入 S
         * 
         * header -> S -> A -> E 2
         * 
         * 插入 Y
         * 
         * header -> Y -> S -> A -> E 3
         * 
         * 插入 Q
         * 
         * header -> Q -> Y -> S -> A -> E 4
         * 
         * 插入 U
         * 
         * header -> U -> Q -> Y -> S -> A -> E 5 
         * 
         * 插入 E 
         * 
         * header -> U -> Q -> Y -> S -> A -> E 6
         * 
         * 插入 S
         * 
         * header -> S -> U -> Q -> Y -> S -> A -> E 6
         * 
         * 插入 T
         * 
         * header -> T -> S -> U -> Q -> Y -> S -> A -> E 7
         * 
         * 插入 I
         * 
         * header -> I -> T -> S -> Q -> Y -> S -> A -> E 8
         * 
         * 插入 O
         * 
         * header -> O -> I -> T -> S -> Q -> Y -> S -> A -> E 9
         * 
         * 插入 N
         * 
         * header -> N -> O -> I -> T -> S -> Q -> Y -> S -> A -> E 10
         * 
         * 
         * 总共进行了 1 + 2 + 3 + 4 + 5 + 6 + 6 + 7 + 8 + 9 + 10 = 61次比较
         * 
         */
    }
}
