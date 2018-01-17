package Ch_2_5_Applications;

public class Practise_2_5_03 {
    public static void main(String[] args) {
        /*
         * Comparable 的比较需要满足传递性，比如 a = b b = c，那么 a = c
         * 
         * 但是该实现会违反传递性
         * 
         * 假设该实现是 
         * 
         * public int compareTo(Balance that) {
         *      if (this.amount < that.amount - 1) return -1;
         *      if (this.amount > that.amount + 1) return  1;
         *      return 0;
         * }
         * 
         * 假设 a.amount = 0  b.amount = 0.5, c.amount = 1.1
         * 
         * 那么显然 a = b, b = c 成立，但是 a < c
         * 
         * 违背了传递性
         * 
         * 
         * 我觉得改进方法是把 后面加减的那个数尽量设置得小一些，比如 1E-30
         */
    }
}
