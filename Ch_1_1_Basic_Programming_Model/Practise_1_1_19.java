package Ch_1_1_Basic_Programming_Model;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_19 {
	/*
	 * 递归实现
	 */
	public static long F(int N) {
		if(N == 0) return 0;
		if(N == 1) return 1;
		return F(N - 1) + F(N - 2);
	}
	/*
	 * 数组改进
	 */
	public static long F_modified(int N) {
		if (N < 0) return 0;
		if (N == 0) return 1;
		if (N == 1) return 1;
		long[] storage = new long[N + 1];
		storage[0] = 1;
		storage[1] = 1;
		for(int i = 2; i < N + 1; i++) {
			storage[i] = storage[i - 2] + storage[i - 1]; 
		}
		return storage[N];
	}
	/*
	 * 节省空间
	 */
	public static long F_fast(int N) {
		if (N == 0) return 1;
		long prev = 0, next = 1;
		while(N-- > 0) {
			long temp = next;
			next += prev;
			prev = temp;
		}
		return next;
	}
	public static void testThreeApproaches() {
		StdOut.println("============ 节省时间和空间 ==============");
		for(int i = 0; i < 50; i++)
			StdOut.println(F_fast(i));
		
		StdOut.println("============= 数组改进 =============");
		for(int i = 0; i < 50; i++)
			StdOut.println(F_modified(i));
		
		StdOut.println("============= 递归实现 =============");
		for(int i = 0; i < 50; i++)
			StdOut.println(F(i));
	}
	public static void main(String[] args) {
		testThreeApproaches();
	}
	// output :
	/*
	 * ============ 节省时间和空间 ==============
		1
		1
		2
		3
		5
		8
		13
		21
		34
		55
		89
		144
		233
		377
		610
		987
		1597
		2584
		4181
		6765
		10946
		17711
		28657
		46368
		75025
		121393
		196418
		317811
		514229
		832040
		1346269
		2178309
		3524578
		5702887
		9227465
		14930352
		24157817
		39088169
		63245986
		102334155
		165580141
		267914296
		433494437
		701408733
		1134903170
		1836311903
		2971215073
		4807526976
		7778742049
		12586269025
		============= 数组改进 =============
		1
		1
		2
		3
		5
		8
		13
		21
		34
		55
		89
		144
		233
		377
		610
		987
		1597
		2584
		4181
		6765
		10946
		17711
		28657
		46368
		75025
		121393
		196418
		317811
		514229
		832040
		1346269
		2178309
		3524578
		5702887
		9227465
		14930352
		24157817
		39088169
		63245986
		102334155
		165580141
		267914296
		433494437
		701408733
		1134903170
		1836311903
		2971215073
		4807526976
		7778742049
		12586269025
		============= 递归实现 =============
		0
		1
		1
		2
		3
		5
		8
		13
		21
		34
		55
		89
		144
		233
		377
		610
		987
		1597
		2584
		4181
		6765
		10946
		17711
		28657
		46368
		75025
		121393
		196418
		317811
		514229
		832040
		1346269
		2178309
		3524578
		5702887
		9227465
		14930352
		24157817
		39088169
		63245986
		102334155
		165580141
		267914296
		...
	 */
}
