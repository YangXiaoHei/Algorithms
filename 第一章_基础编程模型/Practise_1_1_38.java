package 第一章_基础编程模型;

import java.util.*;
import java.util.concurrent.TimeUnit;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_38 {
	/*
	 * 二分查找
	 */
	public static int binarySearch(int key, int[] arr) {
		int l = 0, h = arr.length - 1, mid = 0;
		while(l <= h) {
			mid = (l + h) / 2;
			if 		(key < arr[mid]) h = mid - 1;
			else if (key > arr[mid]) l = mid + 1;
			else return mid;
		}
		return -1;
	}
	/*
	 * 暴力查找
	 */
	public static int bruteForceSearch(int key, int[] arr) {
		for(int i = 0; i < arr.length; i++)
			if(arr[i] == key) return i;
		return -1;
	}
	public static String path = "/Users/bot/Desktop/algs4-data";
	public static void testBruteForceSearchAndBinarySearch(int key, int[] arr) {
		StdOut.println(arr.length + " 条数据中查找 " + key);
		long start = System.nanoTime();
		int index = bruteForceSearch(key, arr);
		long end = System.nanoTime();
		long timeInterval = end - start;
		StdOut.println("暴力查找用时 : " + timeInterval);
		
		start = System.nanoTime();
		index = binarySearch(key, arr);
		end = System.nanoTime();
		timeInterval = end - start;
		StdOut.println("二分查找用时 : " + timeInterval);
		StdOut.println("=======================");
	}
	public static void main(String[] args) {
		int key = 10000;
		int[] largeT = new In(path + "/largeT.txt").readAllInts();
		int[] largeW = new In(path + "/largeW.txt").readAllInts();
		testBruteForceSearchAndBinarySearch(key, largeT);
		testBruteForceSearchAndBinarySearch(key, largeW);
	}
	// output : 
	/*
	 * 	1000000 条数据中查找 10000
		暴力查找用时 : 575251
		二分查找用时 : 5009
		=======================
		1000000 条数据中查找 10000
		暴力查找用时 : 1559985
		二分查找用时 : 2490
		=======================
	 */
}
