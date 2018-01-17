package Ch_1_1_Basic_Programming_Model;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_1_1_30 {
	/*
	 * 求最大公约数
	 */
	public static int gcd(int p, int q) {
		while(q != 0) {
			int r = p % q;
			p = q;
			q = r;
		}
		return p;
	}
	public static boolean[][] createArr() {
		int N = StdRandom.uniform(20);
		boolean[][] arr = new boolean[N][];
		for(int i = 0; i < N; i++)
			arr[i] = new boolean[N];
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				arr[i][j] = gcd(i, j) == 1;
		return arr;
	}
	public static void printArr(boolean[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) 
				StdOut.print(arr[i][j] ? " * " : "   ");
			StdOut.println();
		}
	}
	public static void main(String[] args) {
		printArr(createArr());
	}
	// output :
	/*
	 *     *                               
		 *  *  *  *  *  *  *  *  *  *  *  * 
		    *     *     *     *     *     * 
		    *  *     *  *     *  *     *  * 
		    *     *     *     *     *     * 
		    *  *  *  *     *  *  *  *     * 
		    *           *     *           * 
		    *  *  *  *  *  *     *  *  *  * 
		    *     *     *     *     *     * 
		    *  *     *  *     *  *     *  * 
		    *     *           *     *     * 
		    *  *  *  *  *  *  *  *  *  *    

	 */
}
