package Ch_1_4_Analysis_Of_Algorithms;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_4_02 {
    /*
     * 思路 :
     * 
     * 对于 int 型的溢出，我们只需要将运算数中的某一个提升为 long, 
     * 那么结果就会被提升为 long int 型的溢出也就被正确处理了
     * 
     */
	static class ThreeSum {
		/*
		 * 正确处理溢出
		 */
		public static int correctCount(int[] a) {
			int N = a.length;
			int cnt = 0;
			for (int i = 0; i < N; i++)
				for (int j = i + 1; j < N; j++)
					for (int k = j + 1; k < N; k++)
						if ((long)a[i] + a[j] + a[k] == 0)
							cnt++;
			return cnt;
		}
		/*
		 * 不处理溢出
		 */
		public static int buggyCount(int[] a) {
			int N = a.length;
			int cnt = 0;
			for (int i = 0; i < N; i++)
				for (int j = i + 1; j < N; j++)
					for (int k = j + 1; k < N; k++)
						if (a[i] + a[j] + a[k] == 0)
							cnt++;
			return cnt;
		}
	}
	public static void main(String[] args) {
		int[] arr = new int[] { Integer.MIN_VALUE, Integer.MIN_VALUE, 0};
		StdOut.println("正确处理溢出 ：" + ThreeSum.correctCount(arr));
		StdOut.println("不处理溢出 : " + ThreeSum.buggyCount(arr));
	}
	// output
	/*
	 * 	正确处理溢出 ：0
		不处理溢出 : 1	

	 */
}
