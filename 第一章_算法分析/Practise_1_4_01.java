package 第一章_算法分析;

public class Practise_1_4_01 {
	public static void main(String[] args) {
		/*
		 * n = 3，3 * 2 * 1 / 6 = 1，加法满足交换律和结合律，三个数字的和只有一种组合
		 * 设 n = k 时成立，则 g(k) = k(k - 1)(k - 2)/6;
		 * 则 n = k + 1 时有 g(k + 1)
		 *  = k(k + 1)(k - 1)/6
		 * 	= k(k^2 - 1)/6
		 * 	= [k(k^2 - 3k + 2) + k(3k - 3)]/6
		 * 	= k(k - 2)(k - 1)/6 + k(k - 1)/2
		 *  = k(k - 2)(k - 1)/6 + C(n,2)
		 *  得证
		 */
	}
}
