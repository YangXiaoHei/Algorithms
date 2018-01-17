package Ch_1_4_Analysis_Of_Algorithms;

/*
 * 思路 :
 * 
 * 请看下方注释
 * 
 */
public class Practise_1_4_06 {
	/*
	 * a
	 */
	public static void a(int N) {
		int sum = 0;
		/*
		 * 假设 N 是 2 的幂次方
		 *  N + N/2 + N/4 + N/8 + = 2N - 1 ... ~2N
		 */
		for (int n = N; n > 0; n /= 2) // -> 外循环总共执行 floor(logn) + 1 次
			for (int i = 0; i < n; i++) // 内循环执行 1 + 2 + 4 + 8 + .... m 总共有 floor(logn) + 1 项    
				sum++;
	}
	/*
	 * b
	 */
	public static void b(int N) {
		int sum = 0;
		/*
		 * 假设 N 是 2 的幂次方
		 * 1 + 2 + 4 + 8 + ... N = 2N - 1 ~ 2N
		 */
		for (int i = 1; i < N; i *= 2)
			for (int j = 0; j < i; j++)
				sum++;
	}
	/*
	 * c
	 */
	public static void c(int N) {
		int sum = 0;
		/*
		 * 1 * N + 2 * N + 4 * N + ... N * N
		 * = N *(1 + 2 + 4 + ...N) ~ N * log(N)
		 */
		for (int i = 1; i < N; i *= 2)
			for (int j = 0; j < N; j++)
				sum++;
	}
}
