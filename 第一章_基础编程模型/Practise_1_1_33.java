package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_1_1_33 {
	
	/*
	 * 矩阵库
	 */
	static class Matrix {
		/*
		 * 向量点乘
		 */
		public static double dot(double[] x, double[] y) {
			if (x == null || y == null || x.length != y.length)
				throw new RuntimeException("Fatal Error x = " + x + " y = " + y);
			double sum = 0;
			for(int i = 0; i < x.length; i++)
				sum += x[i] * y[i];
			return sum;
		}
		/*
		 * 矩阵相乘
		 */
		public static double[][] mult(double[][] a, double[][] b) {
			if (a == null || b == null || a[0].length != b.length)
				throw new RuntimeException("Fatal Error x = " + a + " y = " + b);
			
			// 分配空间
			double[][] result = new double[a.length][];
			for(int i = 0; i < a.length; i++)
				result[i] = new double[b[0].length];
			
			// 矩阵相乘
			for(int i = 0; i < a.length; i++) {
				for(int j = 0; j < b[0].length; j++) {
					double sum = 0;
					for(int k = 0; k < a[0].length; k++) 
						sum += a[i][k] * b[k][j];
					result[i][j] = sum;
				}
			}
			return result;
		}
		
		/*
		 * 矩阵转置
		 */
		public static double[][] transpose(double[][] a) {
			if (a == null) 
				throw new RuntimeException("Fatal Error a = " + a);
			
			// 分配空间
			int row = a.length;
			int column = a[0].length;
			double[][] result = new double[column][];
			for(int i = 0; i < result.length; i++)
				result[i] = new double[row];
			
			// 转置
			for(int i = 0; i < column; i++)
				for(int j = 0; j < row; j++) 
					result[i][j] = a[j][i];
			return result;
		}
		/*
		 * 矩阵乘向量
		 * 题目中为啥返回一个一维数组？？
		 * 
		 * a(m,n) * x(1,p) 当且仅当 n = 1 时满足矩阵相乘的条件，并且得到一个 (m, p) 的矩阵啊
		 */
		public static double[][] mul(double[][] a, double[] x) {
			if (a == null || x == null || a[0].length != 1)
				throw new RuntimeException("Fatal Error a = " + a + " x = " + x);
			
			// 分配空间
			double[][] result = new double[a.length][];
			for(int i = 0; i < result.length; i++)
				result[i] = new double[x.length];
			
			// 矩阵相乘
			for(int i = 0; i < result.length; i++)
				for(int j = 0; j < result[0].length; j++) {
					result[i][j] = a[i][0] * x[j];
				}
			return result;
		}
		/*
		 * 向量乘矩阵
		 */
		public static double[] mul(double[] y, double[][] a) {
			if(y == null || a == null || y.length != a[0].length)
				throw new RuntimeException("Fatal Error y = " + y + " a = " + a);
			
			//分配空间
			double[] result = new double[a[0].length];
			
			//矩阵相乘
			for(int i = 0; i < result.length; i++) {
				double sum = 0;
				for(int j = 0; j < y.length; j++) {
					sum += y[j] * a[j][i];
				}
				result[i] = sum;
			}
			return result;
		}
	}
	/*
	 * 打印二维数组
	 */
	public static void printArr(double[][] a) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++)
				StdOut.print(a[i][j] + "  ");
			StdOut.println();
		}
		StdOut.println("====================\n");
	}
	/*
	 * 打印一维数组
	 */
	public static void printArr(double[] a) {
		for(int i = 0; i < a.length; i++)
			StdOut.print(a[i] + "  ");
		StdOut.println();
		StdOut.println("====================\n");
	}
	public static void main(String[] args) {
		double[] A0 = { 1, 2, 3, 4, 5};
		double[] A1 = { 7, 8, 9, 10, 11};
		double[][] A = {
				{1, 2},
				{3, 4},
				{5, 6}
		};
		double[][] B = {
				{6, 7, 	8},
				{9, 10, 11},
		};
		double[][] C = {
				{1},
				{2},
				{3}
		};
		double[] D = {3,2,1};
		double[][] E = {
				{9, 8, 7},
				{6, 5, 4},
				{3, 2, 1}
		};
		//向量点乘
		StdOut.println(Matrix.dot(A0, A1));
		StdOut.println("====================\n");
		
		// 矩阵相乘
		printArr(Matrix.mul(C, D));
		
		// 转置矩阵
		printArr(Matrix.transpose(B));
		
		// 矩阵和向量之积
		printArr(Matrix.mul(C, D));
		
		// 向量和矩阵之积
		printArr(Matrix.mul(D, E));
	}
}
