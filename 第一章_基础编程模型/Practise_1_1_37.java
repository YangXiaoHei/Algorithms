package 第一章_基础编程模型;

import java.awt.Color;
import edu.princeton.cs.algs4.*;

public class Practise_1_1_37 {
	
	/*
	 * 正常的打乱
	 */
	public static void shuffle(int[] arr) {
		int N = arr.length;
		for(int i = 0; i < N; i++) {
			int r = i + StdRandom.uniform(N - i);
			int temp = arr[i];
			arr[i] = arr[r];
			arr[r] = temp;
		}
	}
	/*
	 * 傻逼的打乱
	 */
	public static void foolishShuffle(int[] arr) {
		int N = arr.length;
		for(int i = 0; i < N; i++) {
			int r = StdRandom.uniform(N);
			int temp = arr[i];
			arr[i] = arr[r];
			arr[r] = temp;
		}
	}
	public static int[] sourceArr(int N) {
		int[] arr = new int[N];
		for(int i = 0; i < N; i++)
			arr[i] = i;
		return arr;
	}
	
	public static void reset(int[] arr) {
		for(int i = 0; i < arr.length; i++)
			arr[i] = i;
	}
	/*
	 * 打印数组
	 */
	public static void printArr(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) 
				StdOut.printf("%-5d", arr[i][j]);
			StdOut.println();
		}
	}
	/*
	 * 计算标准差
	 */
	public static double standardeviation(int[][] arr) {
		double sum = 0;
		for(int i = 0; i < arr.length; i++) 
			for(int j = 0; j < arr[0].length; j++)
				sum += arr[i][j];
		double average = sum / (arr.length * arr[0].length);
		
		double variance = 0;
		for(int i = 0; i < arr.length; i++)
			for(int j = 0; j < arr[0].length; j++)
				variance += Math.pow(arr[i][j] - average, 2);
		variance /= (arr.length * arr[0].length);
		return variance;
	}
	
	/*
	 * 傻逼打乱的测试
	 */
	public static double foolishTest(int M, int shuffleCount) {
		
		// 记录数组
		int[][] recordTable = new int[M][];
		for(int i = 0; i < M; i++)
			recordTable[i] = new int[M];
		
		//待打乱数组
		int[] source = sourceArr(M);
		
		// 打乱并记录
		record(true, recordTable, shuffleCount, source);
		double stdDev = standardeviation(recordTable);
		return stdDev;
	}
	/*
	 * 正确打乱测试
	 */
	public static double normalTest(int M, int shuffleCount) {
		// 记录数组
		int[][] recordTable = new int[M][];
		for(int i = 0; i < M; i++)
			recordTable[i] = new int[M];
		
		//待打乱数组
		int[] source = sourceArr(M);
		
		// 打乱并记录
		record(false, recordTable, shuffleCount, source);
		double stdDev = standardeviation(recordTable);
		return stdDev;
	}
	
	public static void record(boolean foolish, int[][] record, int shuffleCount, int[] arr) {
		for(int i = 0; i < shuffleCount; i++) {
			reset(arr);
			if (foolish)
				foolishShuffle(arr);
			else
				shuffle(arr);
			int M = arr.length;
			for(int k = 0; k < M; k++)
				for(int l = 0; l < M; l++)
					if(arr[l] == k)
						record[k][l]++;
		}
	}
	/*
	 * 傻逼打乱和正常打乱的比较，通过绘制标准差的图像，
	 * 标准差小的，代表值普遍趋紧于平均值，是合理的打乱，标准差大，说明值分散程度大，是不合理的打乱
	 * 
	 * @param arrSize 数组容量
	 * @param shuffleCount 打乱次数
	 * @param samplePointCount 采样点数目，即每隔多少点画一个连线
	 */
	public static void twoApproachesComparasion(int arrSize, int shuffleCount, int samplePointCount) {
		StdDraw.setPenRadius(.001);
		StdDraw.setXscale(0, shuffleCount);
		StdDraw.setYscale(0, 300);
		
		class Point { 
			double x;
			double y;
			Point() { this(0, 0); }
			Point(double x, double y) { this.x = x; this.y = y; }
		}
		
		Point[] normalPoints = new Point[shuffleCount];
		Point[] foolishPoints = new Point[shuffleCount];
		
		// 正常的打乱
		for(int i = 1; i <= shuffleCount; i++) 
			normalPoints[i - 1] = new Point(i, normalTest(arrSize, shuffleCount));
		
		// 傻逼的打乱
		for(int i = 1; i <= shuffleCount; i++)
			foolishPoints[i - 1] = new Point(i, foolishTest(arrSize, shuffleCount)); 
		
		// 绘图
		for(int i = samplePointCount; i < shuffleCount; i += samplePointCount) {
			StdDraw.setPenColor(Color.red);
			Point prev = normalPoints[i - samplePointCount];
			Point next=  normalPoints[i];
			StdDraw.line(prev.x, prev.y, next.x, next.y);
		}
		for(int i = samplePointCount; i < shuffleCount; i += samplePointCount) {
			StdDraw.setPenColor(Color.BLACK);
			Point prev = foolishPoints[i - samplePointCount];
			Point next=  foolishPoints[i];
			StdDraw.line(prev.x, prev.y, next.x, next.y);
		}
	}
	
	public static void main(String[] args) {
		/*
		 * 红线是正确打乱的标准差
		 * 黑线是傻逼打乱的标准差
		 */
			int arrSize = 10, 
				shuffleCount = 1000, 
				samplePointCount = 13;
		twoApproachesComparasion(arrSize, 
								shuffleCount, 
								samplePointCount);
	}
}
