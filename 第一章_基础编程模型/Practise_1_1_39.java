package 第一章_基础编程模型;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_1_39 {
	/*
	 * 随机数组
	 */
	public static int[] sourceArr(int N) {
		int[] arr = new int[N];
		for(int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(100000, 1000000);
		Arrays.sort(arr);
		return arr;
	}
	/*
	 * 有序数组寻找共同元素算法
	 */
	public static int commonCount(int[] arr1, int[] arr2) {
		int count = 0;
		int i = 0, j = 0;
		while(i < arr1.length && j < arr2.length) {
			if 		(arr1[i] < arr2[j]) i++;
			else if (arr2[j] < arr1[i]) j++;
			else	 	{ i++;  j++; count++; }
		}
 		return count;
	}
	public static void main(String[] args) {
		int N = 1000;
		int T = 10;
		double average = 0;
		for (int i = 0; i < 4; i++) {
			for(int loop = 0; loop < T; loop++) 
				average += (double)commonCount(sourceArr(N), sourceArr(N));
			average /= T;
			StdOut.println("N = " + N + "  " + T + " 次实验平均值 : " + average);
			N *= 10;
		}
	}
}
