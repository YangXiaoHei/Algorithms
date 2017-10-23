package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_1_1_11 {
	private static Random rand = new Random();
	public static void printBooleanArray(boolean[][] arr) {
		if (arr == null || arr[0] == null)
			throw new RuntimeException("not supported argument type");
		
		int row = arr.length;
		int column = arr[0].length;
		
		StdOut.print("   ");
		for(int j = 0; j < column; j++) 
			StdOut.print(" " + j + " ");
		StdOut.println();
		for(int i = 0; i < row; i++) {
			StdOut.print(i + ": ");
			for(int j = 0; j < column; j++) 
				StdOut.print(arr[i][j] ? " * " : "   ");
			StdOut.println();
		}
	}
	public static void main(String[] args) {
		/*
		 * 随机 boolean 数组
		 */
		int row = rand.nextInt(10) + 1;
		int column = rand.nextInt(10) + 1;
		boolean[][] a = new boolean[row][];
		for(int i = 0; i < row; i++) {
			a[i] = new boolean[column];
			for(int j = 0; j < column; j++)
				a[i][j] = rand.nextBoolean();
		}
		printBooleanArray(a);
	}
}
