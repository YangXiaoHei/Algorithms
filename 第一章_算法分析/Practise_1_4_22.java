package 第一章_算法分析;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_22 {
	
	static class FibonacciSearch {
		/*
		 * 判断 int 加法操作是否溢出
		 */
		public static boolean isOverflow(int x, int y) {
			long r = x + y;
			return ((x ^ r) & (y ^ r)) < 0;
		}
		/*
		 * 计算斐波纳切数列第 k 项
		 */
		public static int fibo(int k) {
			int prev = 1, next = 1;
			while (--k > 0) {
				if (isOverflow(next, prev))
					StdOut.printf("next = %d prev = %d 开始溢出\n", next, prev);
				next = next + prev;
				prev = next - prev;
			}
			return next;
		}
		/*
		 * 首次加载该 Class 文件时就把斐波纳切前 47 项全部准备好
		 */
		public static final int[] fibo;
		static {
			/*
			 * 斐波纳切第 46 项 int 溢出
			 * 第 93 项 long 溢出
			 */
			fibo = new int[47];
//			fibo[0] = 0;
			int index = 0;
			for (int i = 0; i < fibo.length - 1; i++)
				fibo[index++] = fibo(i);
			
//			StdOut.println(Arrays.toString(fibo));
		}
		/*
		 * 在 lo 和 hi 间寻找斐波纳切分割点
		 */
		public static int splitBetween(int lo, int hi) {
			int fIndex = 0;
			while (fibo[fIndex] <= (hi - lo))  fIndex++;
			return fibo[--fIndex] + lo - 1;
		}
		/*
		 * 斐波纳切查找
		 */
		public static int rank(int key, int[] arr) {
			int lo = 0, hi = arr.length - 1, split = 0;
			while (lo < hi) {
				split = splitBetween(lo, hi);
				if 		(arr[split] > key) hi = split;
				else if (arr[split] < key) lo = split + 1;
				else	return split;
			}
			return -1;
		}
	}
	/*
	 * 产生已排序随机数组
	 */
	public static int[] sourceArr(int N) {
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(0, 100);
		Arrays.sort(arr);
		return arr;
	}
	/*
	 * 打印数组
	 */
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			if ((i + 1) % 10 == 0)
				StdOut.printf("%2d\n", arr[i]);
			else
				StdOut.printf("%2d ", arr[i]);
		StdOut.println();
	}
	
	public static void main(String[] args) {
		int[] arr = sourceArr(100);
		printArray(arr);
		StdOut.println("斐波纳切查找结果 : " + FibonacciSearch.rank(4, arr));
	}
	// output
	/*
	 *   0  0  0  0  0  1  2  3  3  3
		 4  4  4  6  6  6  9 11 12 12
		14 15 16 18 20 20 21 22 22 23
		24 24 25 25 27 28 29 29 30 31
		31 31 32 34 34 35 36 36 40 41
		42 44 44 47 47 47 47 49 51 52
		53 53 54 55 57 59 61 61 61 62
		63 66 66 66 68 68 69 70 74 79
		81 82 83 84 85 86 86 87 90 90
		91 91 92 93 96 96 96 97 97 98

		斐波纳切查找结果 : 7

	 */
	
}
