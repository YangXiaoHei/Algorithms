package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_13 {
	public static int[][] transposeMatrix(int[][] matrix) {
		if (matrix == null || matrix[0] == null)
			throw new RuntimeException("not supported argument type");
		
		int row = matrix.length;
		int column = matrix[0].length;
		
		int[][] transpose = new int[column][row];
		for(int i = 0; i < column; i++) 
			for(int j = 0; j < row; j++) 
				transpose[i][j] = matrix[j][i];
		return transpose;
	}
	
	public static void main(String[] args) {
		/*
		 * 随机数组
		 */
		int orgRow = StdRandom.uniform(1, 10);
		int orgColumn = StdRandom.uniform(1, 10);
		
		int[][] matrix = new int[orgRow][];
		for(int i = 0; i < matrix.length; i++) {
			matrix[i] = new int[orgColumn];
			for(int j = 0; j < matrix[i].length; j++)
				matrix[i][j] = StdRandom.uniform(1, 10);
		}
		
		// 转置
		int[][] transposed = transposeMatrix(matrix);
		
		// 打印
		StdOut.println("=====转置前======");
		for(int i = 0; i < orgRow; i++) {
			for(int j = 0; j < orgColumn; j++)
				StdOut.print(matrix[i][j] + "  ");
			StdOut.println();
		}
		StdOut.println("=====转置后======");
		for(int i = 0; i < orgColumn; i++) {
			for(int j = 0; j < orgRow; j++)
				StdOut.print(transposed[i][j] + "  ");
			StdOut.println();
		}
	}
}
