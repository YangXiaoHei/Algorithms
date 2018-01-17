package Ch_1_1_Basic_Programming_Model;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_1_35 {
	/*
	 * 精确数据
	 */
	public static int SIDES = 6;
	public static double dist[] = new double[2 * SIDES + 1];
	static {
		for(int i = 1; i <= SIDES; i++)
			for(int j = 1; j <= SIDES; j++)
				dist[i + j] += 1.0;
		for(int k = 2; k <= 2 * SIDES; k++)
			dist[k] /= 36.0;
	}
	/*
	 * 实验数据
	 */
	public static double[] sidesTest(int N) {
		double[] counter = new double[13];
		for(int i = 0; i < N; i++) {
			int d1 = StdRandom.uniform(1, 7);
			int d2 = StdRandom.uniform(1, 7);
			counter[d2 + d1] += 1.0; 
		}
		double[] result = new double[11];
		for(int i = 0; i < result.length; i++)
			result[i] = counter[i + 2] / N;
		return result;
		
	}
	
	public static void main(String[] args) {
		/*
		 * 据多次实验，N 至少要 一亿次 才能使吻合程度次次都能达到小数点后三位
		 */
		int N = 100000000;
		double[] testResult = sidesTest(N);
		double[] preciseResult = dist;
		for(int i = 0; i < testResult.length; i++) 
			StdOut.printf("实验 : %.3f  精确 : %.3f\n", 
						  testResult[i], preciseResult[i + 2]);
 	}
	// output :
	/*
	 * 	实验 : 0.028  精确 : 0.028
		实验 : 0.056  精确 : 0.056
		实验 : 0.083  精确 : 0.083
		实验 : 0.111  精确 : 0.111
		实验 : 0.139  精确 : 0.139
		实验 : 0.167  精确 : 0.167
		实验 : 0.139  精确 : 0.139
		实验 : 0.111  精确 : 0.111
		实验 : 0.083  精确 : 0.083
		实验 : 0.056  精确 : 0.056
		实验 : 0.028  精确 : 0.028
	 */
}
