package Ch_3_1_Symbol_Tables;

public class Practise_3_1_21 {
    public static void main(String[] args) {
        /*
         * 对于 SequentailSearchST 一个结点包含了 key 和 value 的引用, 同时还有 16 字节对象固定开销
         * 所以没创建一个新结点就增加 16 + 8 + 8 = 32 字节内存使用
         * 
         * 对于 N 对键值，也就使用了 32N 字节的内存
         * 
         * 对于 BinarySearchST 两个数组各表示 key 和 value, 对象固定开销为 32字节
         * 其间涉及到数组的缩容和扩容操作，那么 N 个键数组的最小尺寸为 N, 最大尺寸为 4N
         * 所以内存使用为 8N ~ 32N, 再加上值数组的内存使用，总共为 16N ~ 64N
         * 所以 BinarySearchST 的内存使用为 (16N + 32) ~ (64N + 32)
         * 
         * 
         */
    }
}
