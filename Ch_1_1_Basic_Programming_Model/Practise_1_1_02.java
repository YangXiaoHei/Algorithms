package Ch_1_1_Basic_Programming_Model;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_02 {
	public static void main(String[] args) {
		/*
		 * 整型被提升为浮点数
		 */
		StdOut.println((1 + 2.236) / 2);
		/*
		 * 整型被提升为浮点数
		 */
		StdOut.println(1 + 2 + 3 + 4.0);
		/*
		 * 整型被提升为浮点数
		 */
		StdOut.println(4.1 >= 4);
		/*
		 * 重载的 +，连接字符串
		 */
		StdOut.println(1 + 2 + "3");
	}
	// output :
	/*
	 * 	1.618
		10.0
		true
		33

	 */
}
