package Ch_3_2_Binary_Search_Trees;

public class Practise_3_2_27 {
    public static void main(String[] args) {
        /*
         * 对于 SequentialSearchST 每对键值由一个Node结点表示
         * 一个引用8字节，因此 N 对不同的键值总共占用 (16 + 8 + 8) * N = 32N 字节
         * 
         * 对于 BinarySearchST 用一个数组表示键，一个数组表示值
         * 当涉及到缩容扩容时，数组的大小范围是 N ~ 4N 
         * 因此总共占用 32 + 16N ~ 32 + 64N 字节
         * 
         * 对于 BinarySearchTree 没对键值由一个Node结点表示
         * 每个Node结点为了维护自身的树结构，至少要由两个引用，分表表示左右孩子
         * 因此一个 Node 结点至少有 16 + 8 + 8 + 8 + 8 = 48字节
         * 因此 N 对不同的键值总共占据 48N 字节
         */
    }
}
