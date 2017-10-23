package 第一章_基础编程模型;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_1_39 {
	public static int binarySearch(int key, int[] arr) {
		int lo = 0, hi = arr.length - 1, mid = 0;
		while(lo <= hi) {
			mid = (lo + hi) / 2;
			if 		(key < arr[mid]) hi = mid - 1;
			else if (key > arr[mid]) lo = mid + 1;
			else		return mid;
		}
		return -1;
	}
	public static int[] sourceArr(int N) {
		int[] arr = new int[N];
		for(int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(100000, 1000000);
		Arrays.sort(arr);
		return arr;
	}
	public static int[] getNormalTypeArr(List<Integer> list) {
		int[] newArr = new int[list.size()];
		for(int k = 0; k < newArr.length; k++)
			newArr[k] = list.get(k);
		return newArr;
	}
	
	public static List<Integer> initList(int[] arr) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i = 0; i < arr.length; i++)
			list.add(arr[i]);
		return list;
	}
	/*
	 * 有序数组寻找共同元素算法
	 */
	public static int commonCount(int[] arr1, int[] arr2) {
		int count = 0;
		int i = 0, j = 0;
		while(i < arr1.length && j < arr2.length) {
			if 		(arr1[i] < arr2[j]) i++;
			else if (arr2[j] < arr1[i]) j++;
			else	 	{ i++;  j++; count++; }
		}
 		return count;
	}
	public static void main(String[] args) {
		int N = 1000;
		int T = 10;
		double average = 0;
		for (int i = 0; i < 4; i++) {
			for(int loop = 0; loop < T; loop++) 
				average += (double)commonCount(sourceArr(N), sourceArr(N));
			average /= T;
			StdOut.println("N = " + N + "  " + T + " 次实验平均值 : " + average);
			N *= 10;
		}
	}
}
