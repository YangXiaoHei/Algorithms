package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_01 {
	public static void main(String[] args) {
		/*
		 * 整型相除，向0舍入
		 */
		StdOut.println( (0 + 15) / 2 );
		/*
		 * 字面值是双精度
		 */
		StdOut.println( 2.0e-6 * 100000000.1);
		/*
		 *  && 优先级高于 ||
		 */
		StdOut.println( true && false || true && true );
	}
	// output :
	/*
	 * 	7
		200.0000002
		true
	 */
}
