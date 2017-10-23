package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_15 {
	public static void main(String[] args) {
		int M = StdRandom.uniform(50);
		StdOut.println("M = " + M);
		
		int[] a = new int[10];
		for(int i = 0; i < a.length; i++)
			a[i] = StdRandom.uniform(M);
		
		StdOut.println("原数组 : ");
		for(int i = 0; i < a.length; i++) 
			StdOut.print(a[i] + " ");
		
		int[] arr = new int[M];
		for(int i = 0; i < M; i++) {
			int count = 0;
			for(int j = 0; j < a.length; j++)
				if(a[j] == i)
					count++;
			arr[i] = count;
		}
		StdOut.println();
		
		StdOut.println("结果数组 : ");
		for(int i = 0; i < M; i++) 
			StdOut.print(arr[i] + " ");
		StdOut.println();
		
		int sum = 0;
		for(int i = 0; i < arr.length; i++)
			sum += arr[i];
		StdOut.println("========================");
		StdOut.println("a.length = " + a.length);
		StdOut.println("返回数组之和 = " + sum);	
	}
}
