package 第一章_基础编程模型;

import java.util.Arrays;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_28 {
	/*
	 * 生成一个随机数组
	 */
	public static int[] randomArray() {
		int size = 10000;
		int[] arr = new int[size];
		for(int i = 0; i < size; i++) 
			arr[i] = StdRandom.uniform(10);
		Arrays.sort(arr);
		return arr;
	}
	/*
	 * 去重
	 */
	public static int[] distinct(int[] arr) {
		int index = 1;
		int len = arr.length;
        for (int i = 1; i < len; i++) 
            if (arr[i] != arr[i - 1]) 
            		arr[index++] = arr[i];
        int[] old = arr;
        arr = new int[index];
        for(int i = 0; i < arr.length; i++)
        		arr[i] = old[i];
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = randomArray();
		StdOut.println("去重前 : " + Arrays.toString(arr));
		arr = distinct(arr);
		StdOut.println("去重后 : " + Arrays.toString(arr));
	}
}
