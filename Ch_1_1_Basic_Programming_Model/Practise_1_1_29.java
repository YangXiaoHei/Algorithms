package Ch_1_1_Basic_Programming_Model;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_1_1_29 {
	public static int[] randomArray() {
		int size = 20;
		int[] arr = new int[size];
		for(int i = 0; i < size; i++) 
			arr[i] = StdRandom.uniform(10);
		Arrays.sort(arr);
		return arr;
	}
	public static int rank(int key, int[] arr) {
		int count = 0;
		for(int i = 0; i < arr.length; i++)
			if (arr[i] >= key) 
				break;
			else
				count++;
		return count;
	}
	public static int count(int key, int[] arr) {
		int index = Practise_1_1_22.binarySearch(key, arr);
		if (index < 0) return 0;
		int beg = index, end = index;
		while(beg >= 0 && arr[beg] == key) beg--;
		while(end < arr.length && arr[end] == key) end++;
		beg++;
		end--;
		return end - beg + 1;
	}
	
	public static void main(String[] args) {
		int[] arr = randomArray();
		StdOut.println(Arrays.toString(arr));
		StdOut.println("i = " + rank(4, arr));
		StdOut.println("j = " + count(4, arr));
	}
	// output :
	/*
	 *  [0, 0, 0, 1, 1, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 8]
		i = 7
		j = 5

	 */
}
