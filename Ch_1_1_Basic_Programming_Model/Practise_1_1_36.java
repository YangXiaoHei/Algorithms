package Ch_1_1_Basic_Programming_Model;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_1_36 {
	/*
	 * 该打乱算法存在打乱后和原数组相等的可能
	 */
	public static void shuffle(double[] a) {
		int N = a.length;
		for(int i = 0; i < N; i++) {
			int r = StdRandom.uniform(N);
			double temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}
	/*
	 * 创建大小为 M 的数组
	 */
	public static double[] sourceArr(int M) {
		double[] arr = new double[M];
		for(int i = 0; i < M; i++)
			arr[i] = i;
		return arr;
	}
	/*
	 * 重置数组为下标序列
	 */
	public static void resetArr(double[] arr) {
		for(int i = 0; i < arr.length; i++)
			arr[i] = i;
	}
	/*
	 * 打印数组
	 */
	public static void printArr(int[][] arr) {
		double sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum = 0;
			for(int j = 0; j < arr[0].length; j++) {
				sum += arr[i][j];
				StdOut.printf("%-5d", arr[i][j]);
			}
			StdOut.println();
		}
		StdOut.println("===============");
	}
	/*
	 * 打乱测试
	 * 
	 * @param M 准备打乱的数组大小 
	 * @param N 打乱数组的次数
	 */
	public static void ShuffleTest(int M, int N) {
		double[] source = sourceArr(M);
		
		int[][] recordTable = new int[M][];
		for(int i = 0; i < M; i++)
			recordTable[i] = new int[M];
		
		record(recordTable, N, source);
		
		StdOut.println("N / M = " + N / M);
		printArr(recordTable);
	}
	/*
	 * 执行 N 次打乱并记录每个元素落在每列的次数
	 */
	public static void record(int[][] recordTable, int shuffleCount, double[] source) {
		for(int n = 0; n < shuffleCount; n++) {
			resetArr(source);
			shuffle(source);
			for(int i = 0; i < source.length; i++)
				for(int k = 0; k < source.length; k++)
					if((int)source[k] == i) 
						recordTable[i][k]++;
		}
	}
	
	public static void main(String[] args) {
		ShuffleTest(10 /* 数组大小 */, 10000 /* 打乱次数 */);
	}
	// output :
	/*
	 * 	N / M = 1000
		943  958  1068 1033 1019 956  982  956  1027 1058 
		1330 996  961  890  1002 998  924  928  975  996  
		1216 1235 861  968  931  912  960  967  924  1026 
		1119 1180 1204 901  865  883  933  998  927  990  
		994  1057 1211 1145 818  862  914  955  1027 1017 
		981  987  1031 1118 1139 893  901  982  970  998  
		903  971  986  1099 1128 1174 870  941  967  961  
		854  920  950  959  1115 1112 1252 901  958  979  
		840  823  911  983  1025 1139 1145 1242 916  976  
		820  873  817  904  958  1071 1119 1130 1309 999  
		===============

	 */
}
