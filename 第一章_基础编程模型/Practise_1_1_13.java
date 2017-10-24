package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_13 {
	/*
	 * 转置
	 */
	public static int[][] transposeMatrix(int[][] matrix) {
		if (matrix == null)
			throw new RuntimeException("not supported argument type");
		
		int row = matrix.length;
		int column = matrix[0].length;
		
		int[][] transpose = new int[column][row];
		for(int i = 0; i < column; i++) 
			for(int j = 0; j < row; j++) 
				transpose[i][j] = matrix[j][i];
		return transpose;
	}
	/*
	 * 随机数组
	 */
	public static int[][] sourceArr(int row, int column) {
		int[][] matrix = new int[row][];
		for(int i = 0; i < matrix.length; i++) {
			matrix[i] = new int[column];
			for(int j = 0; j < matrix[i].length; j++)
				matrix[i][j] = StdRandom.uniform(1, 10);
		}
		return matrix;
	}
	/*
	 * 打印数组
	 */
	public static void printArr(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++)
				StdOut.print(arr[i][j] + "  ");
			StdOut.println();
		}
	}
	/*
	 * 转置测试
	 */
	public static void transposeTest() {
		int[][] matrix = sourceArr(StdRandom.uniform(1, 20), StdRandom.uniform(1, 20));
		
		// 转置
		int[][] transposed = transposeMatrix(matrix);
		
		// 打印
		StdOut.println("==================转置前==================");
		printArr(matrix);
		
		StdOut.println("==================转置后==================");
		printArr(transposed);
	}
	
	public static void main(String[] args) {
		transposeTest();
	}
}
