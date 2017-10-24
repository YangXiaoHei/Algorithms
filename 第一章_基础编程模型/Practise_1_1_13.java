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
	// output :
	/*
	 * 	==================转置前==================
		4  7  4  5  6  9  2  3  8  9  6  2  2  8  2  2  4  
		3  9  7  9  4  2  8  3  2  3  2  1  9  4  5  7  9  
		1  7  1  7  4  3  8  9  2  4  6  4  3  5  7  6  5  
		5  5  8  2  1  5  7  8  8  6  7  6  4  5  7  7  7  
		5  4  2  9  5  5  3  4  6  8  5  8  2  7  7  8  8  
		3  6  6  9  7  5  5  8  7  5  4  3  8  4  9  3  1  
		==================转置后==================
		4  3  1  5  5  3  
		7  9  7  5  4  6  
		4  7  1  8  2  6  
		5  9  7  2  9  9  
		6  4  4  1  5  7  
		9  2  3  5  5  5  
		2  8  8  7  3  5  
		3  3  9  8  4  8  
		8  2  2  8  6  7  
		9  3  4  6  8  5  
		6  2  6  7  5  4  
		2  1  4  6  8  3  
		2  9  3  4  2  8  
		8  4  5  5  7  4  
		2  5  7  7  7  9  
		2  7  6  7  8  3  
		4  9  5  7  8  1  
	 */
}
