package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_1_1_11 {
	private static Random rand = new Random();
	/*
	 * 用指定的 * 打印 boolean 数组
	 */
	public static void printBooleanArray(boolean[][] arr) {
		if (arr == null)
			throw new RuntimeException("not supported argument type");
		
		int row = arr.length;
		int column = arr[0].length;
		
		int digit = digit(Math.max(row, column));
		digit += 3;
		
		StdOut.print("   ");
		for(int j = 0; j < column; j++) 
			StdOut.print(String.format("%" + digit + "d", j));
		StdOut.println();
		for(int i = 0; i < row; i++) {
			StdOut.print(i + ": ");
			for(int j = 0; j < column; j++) 
				StdOut.print(arr[i][j] ? String.format("%" + digit + "s", "*") : String.format("%" + digit + "s", " "));
			StdOut.println();
		}
	}
	/*
	 * 随机 boolean 数组
	 */
	public static boolean[][] sourceArr(int row, int column) {
		boolean[][] a = new boolean[row][];
		for(int i = 0; i < row; i++) {
			a[i] = new boolean[column];
			for(int j = 0; j < column; j++)
				a[i][j] = rand.nextBoolean();
		}
		return a;
	}
	/*
	 * 求位数
	 */
	public static int digit(int num) {
		int digit = 0;
		while(num > 0) {
			digit++;
			num /= 10;
		}
		return digit;
	}
	
	public static void main(String[] args) {
		printBooleanArray(sourceArr(rand.nextInt(100) + 1, rand.nextInt(100) + 1));
	}
}
