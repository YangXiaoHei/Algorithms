package 第一章_算法分析;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_19 {
	/*
	 * 矩阵局部最小元素
	 */
	public static int[] localMinimumOfMatrix(int[][] a) {
		int N = a.length;
		int lo = 0, hi = N - 1, midRow = 0, midCol = N / 2;
		while (lo < hi) {
			midRow = (lo + hi) / 2;
			if (midRow == 0 || midRow == N - 1) break;
			
			int minColIndex = minimumColumnIndex(a[midRow]);
			if (a[midRow - 1][minColIndex] > a[midRow][minColIndex] &&
				a[midRow + 1][minColIndex] > a[midRow][minColIndex]) 
				return  new int[] {midRow, minColIndex, a[midRow][minColIndex]};
			
			int minRowIndex = minimumRowIndex(a, midCol, lo, hi);
			if (a[minRowIndex][midCol - 1] > a[minRowIndex][midCol] &&
				a[minRowIndex][midCol + 1] > a[minRowIndex][midCol]) 
				return  new int[] {minRowIndex, midCol, a[midRow][minColIndex]};
			
			if (a[midRow - 1][minColIndex] < a[midRow + 1][minColIndex]) {
				lo = 0;
				hi = midRow - 1;
			} else {
				lo = midRow + 1;
				hi = N - 1;
			}
		}
		return null;
	}
	/*
	 * 获取中间列最小元素的行索引
	 */
	public static int minimumRowIndex(int[][] a,int column, int rowStart, int rowEnd) {
		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int i = rowStart; i <= rowEnd; i++) {
			if (a[i][column] < min) {
				min = a[i][column];
				index = i;
			}
		}
		return index;
	}
	
	/*
	 * 获取一行中最小元素的列索引
	 */
	public static int minimumColumnIndex(int[] a) {
		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < min) {
				min = a[i];
				index = i;
			}
		}
		return index;
	}
	/*
	 * 生成 N * N 个无重复的整数
	 */
	public static int[][] sourceArr(int N) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[][] a = new int[N][];
		for (int i = 0; i < N; i++) {
			a[i] = new int[N];
			for (int j = 0; j < N; j++) {
				int random = StdRandom.uniform(1, 150);
				while (map.containsKey(random))
					random = StdRandom.uniform(1, 150);
				map.put(random, random);
				a[i][j] = random;
			}
		}
		return a;
	}
	
	/*
	 * 打印数组，并打印行索引和列索引
	 */
	public static void printArray(int[][] arr) {
		StdOut.printf("%-5s", " ");
		for (int i = 0; i < arr[0].length; i++) {
			StdOut.printf("%-4d", i);
		}
		StdOut.println("\n");
		
		for (int i = 0; i < arr.length; i++) {
			StdOut.printf("%-3d  ", i);
			for (int j = 0; j < arr[i].length; j++) 
				StdOut.printf("%-4d", arr[i][j]);
			StdOut.println();
		}
		StdOut.println();
	}
	
	public static void main(String[] args) {
		int[][] a = sourceArr(6);
		printArray(a);
		int[] results = localMinimumOfMatrix(a);
		if (results == null)
			StdOut.println("没找到咯");
		else
			StdOut.printf("row : %d column %d result : %d",
				results[0], results[1], results[2]);
	}
	// output
	/*
	 *       0   1   2   3   4   5   

		0    122 108 78  97  116 141 
		1    52  131 103 115 90  129 
		2    69  95  99  117 27  145 
		3    136 1   72  147 61  8   
		4    55  28  149 48  5   37  
		5    18  76  42  112 80  13  
		
		row : 2 column 4 result : 27
	 */
}
