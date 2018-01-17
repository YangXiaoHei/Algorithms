package Ch_1_4_Analysis_Of_Algorithms;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_20 {
    /*
     * 思路 : 
     * 
     * 首先我们通过二分法找出分割点，即在该点 splitIndex 处满足 a[splitIndex] > a[splitIndex + 1]
     * 并且 a[splitIndex] > a[splitIndex - 1]
     * 
     * 找到分割点的思路如下 : 先算出 mid, 如果 mid + 1 小于 mid, 此时我们位于一个单调递减的序列，
     * 分割点在左边，让 hi = mid
     * 如果 mid - 1 小于 mid，此时我们位于一个单调递增的序列，
     * 分割点在右边，让 lo = mid + 1,
     * 假如 hi 率先命中 splitIndex, 那么因为左侧元素全部小于 hi, 所以随着 lo + 1, 最后 hi == lo 跳出循环
     * 假如 lo 率先命中 splitIndex, 那么因为右侧元素全部小于 hi, 所以随着 hi = mid, 最后 hi == lo 跳出循环
     * 
     * 当我们确定了分割点后，就用分割点确定了两个查找区域，我们选择先查找元素较少的一方，假如找到，直接返回结果
     * 假如没找到，再去找元素较多的那个区域，对两个区域都分别用二分法，因此最后的运行时间不过 klog(N) k < 2
     */
	/*
	 * 查找双调数组的分割点
	 */
	public static int searchMax(int a[]) {
		int lo = 0, hi = a.length - 1, mid = 0;
		while (lo != hi) {
			mid = (lo + hi) / 2;
			if (a[mid] > a[mid + 1]) hi = mid;
			if (a[mid] < a[mid + 1]) lo = mid + 1;
		}
		return lo;
	}
	/*
	 * 双调查找
	 */
	public static int bitonicSearch(int key, int[] arr) {
		int splitIndex = searchMax(arr);
		double middleIndex = arr.length / 2.0;
		if (splitIndex < middleIndex) {
			int index = binarySearch(key, arr, 0, splitIndex);
			if (index >= 0) return index;
			return binarySearch(key, arr, splitIndex + 1, arr.length - 1);
		} else {
			int index = binarySearch(key, arr, splitIndex + 1, arr.length - 1);
			if (index >= 0) return index;
			return binarySearch(key, arr, 0, splitIndex);
		}
	}
	/*
	 * 二分查找
	 */
	public static int binarySearch(int key, int[] a, int lo, int hi) {
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if 		(a[mid] > key) hi = mid - 1;
			else if (a[mid] < key) lo = mid + 1;
			else return mid;
		}
		return -1;
	}
	/*
	 * 生成任意的双调数组
	 */
	public static int[] bitonicArr(int N) {
		Set<Integer> set = new HashSet<Integer>();
		int leftCount = StdRandom.uniform(N + 1);
		int rightCount = N - leftCount;
		if (leftCount == 0) {
			int[] arr = new int[N];
			for (int i = 0; i < rightCount; i++) {
				int e = StdRandom.uniform(1, 100);
				while (set.contains(e))
					e = StdRandom.uniform(1, 100);
				arr[i] = e;
				set.add(e);
			}
			Arrays.sort(arr);
			return arr;
		}
		if (rightCount == 0) {
			int[] arr = new int[N];
			for (int i = 0; i < leftCount; i++) {
				int e = StdRandom.uniform(1, 100);
				while (set.contains(e))
					e = StdRandom.uniform(1, 100);
				arr[i] = e;
				set.add(e);
			}
			Arrays.sort(arr);
			return arr;
		}
		set.clear();
		int[] arr = new int[N];
		int[] right = new int[rightCount];
		int[] left = new int[leftCount];
		for (int i = 0; i < leftCount; i++) {
			int e = StdRandom.uniform(1, 100);
			while (set.contains(e))
				e = StdRandom.uniform(1, 100);
			left[i] = e;
			set.add(e);
		}
		Arrays.sort(left);
		set.clear();
		set.add(left[left.length - 1]);
 		for (int i = 0; i < rightCount; i++) {
 			int e = StdRandom.uniform(1, 100);
			while (set.contains(e))
				e = StdRandom.uniform(1, 100);
			right[i] = e;
			set.add(e);
 		}
 		Arrays.sort(right);
 		int lo = 0, hi = right.length - 1;
 		while (lo <= hi) {
 			int tmp = right[lo];
 			right[lo] = right[hi];
 			right[hi] = tmp;
 			lo++; hi--;
 		}
 		int i = 0, j = 0, k = 0;
 		while (true) {
 			if (i < leftCount)
 				arr[k++] = left[i++];
 			else if (j < rightCount) 
 				arr[k++] = right[j++];
 			else break;
 		}
 		return arr;
	}
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			if ((i + 1) % 10 == 0)
				StdOut.printf("%3d\n", arr[i]);
			else
				StdOut.printf("%3d", arr[i]);
		StdOut.println();
	}
	public static void main(String[] args) {
		int[] arr = bitonicArr(100);
		printArray(arr);
		StdOut.println("result index is " + bitonicSearch(16, arr));
	}
	// output
	/*
	 *   1  2  3  4  7  8  9 10 11 12
		 13 14 15 16 17 18 20 22 23 24
		 25 27 28 29 30 33 34 35 37 38
		 39 40 41 43 44 45 47 48 49 50
		 51 52 53 54 55 56 57 58 60 61
		 62 63 64 65 66 68 69 70 71 73
		 74 75 76 77 79 80 81 83 84 85
		 86 87 89 90 91 92 93 94 95 96
		 97 98 99 95 86 74 73 66 64 53
		 52 50 45 44 40 19 18 16 13  3
		
		result index is 13
	 */
}
