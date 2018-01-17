package Ch_1_4_Analysis_Of_Algorithms;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_15 {
	static class TwoSumFaster {
		/*
		 * 思路 :
		 * 
		 * 对于已排序数组，右边的值都比左侧大，所以如果 
		 * a[left] + a[right] > 0
		 * 那么，此时无论这个左侧索引的右边选择什么值来与 a[right] 相加，
		 * 都不可能让和小于或等于 0，此时我们让右侧索引减1
		 * 同样的，如果 a[left] + a[right] < 0
		 * 说明无论在这个右侧索引的左边选择什么值，都不能让和大于等于0，
		 * 因为都它们都小于 a[right],此时我们让左侧索引加1
		 * 
		 * 进一步优化，如果最小元素都大于0，全部都是正数，那也就不可能找得到 TwoSum 了
		 * 所以第一步，判断首元素是否大于等于0
		 */
		public static int count(int[] a) {
		    if (a == null || a.length == 0 || a[0] > 0) return 0;
			int cnt = 0, N = a.length, lo = 0, hi = N - 1;
			while (lo <= hi) {
				if 		(a[lo] + a[hi] < 0) lo++;
				else if (a[lo] + a[hi] > 0) hi--;
				else 	{ cnt++; lo++; hi--; }
			}
			return cnt;
		}
	}
	static class ThreeSumFaster {
		public static int count(int[] a) {
		    if (a == null || a.length == 0 || a[0] > 0) return 0;
			int cnt = 0, N = a.length, lo = 0, hi = 0;
			for (int i = 0; i < N; i++) {
				lo = i + 1; hi = N - 1;
				while (lo <= hi) {
					if 		(a[lo] + a[hi] > -a[i]) hi--;
					else if (a[lo] + a[hi] < -a[i]) lo++;
					else	{ cnt++; lo++; hi--; }
				}
			}
			return cnt;
		}
	}
	/*
	 * 产生已排序随机数组
	 */
	public static int[] sourceArr(int N) {
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(-100, 100);
		Arrays.sort(arr);
		return arr;
	}
	/*
	 * 打印数组
	 */
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			if ((i + 1) % 10 == 0)
				StdOut.printf("%5d\n", arr[i]);
			else
				StdOut.printf("%5d", arr[i]);
		StdOut.println();
	}
	public static void main(String[] args) {
		int[] arr = sourceArr(30);
		printArray(arr);
		StdOut.println("two sum count : " + TwoSumFaster.count(arr) + "\n");
		StdOut.println("three sum count : " + ThreeSumFaster.count(arr));
	}
	// output
	/*
	 *    -100  -98  -97  -88  -84  -80  -78  -66  -63  -59
		  -51  -39  -25  -16  -10    0    3    4   12   14
		   19   24   32   36   54   55   63   84   87   91
		
		a[4] = -84 a[27] = 84
		a[8] = -63 a[26] = 63
		a[15] = 0 a[15] = 0
		
		two sum count : 3
		
		a[19] = 14 a[27] = 84 a[1] = -98
		a[17] = 4 a[27] = 84 a[3] = -88
		a[15] = 0 a[27] = 84 a[4] = -84
		a[21] = 24 a[24] = 54 a[6] = -78
		a[12] = -25 a[29] = 91 a[7] = -66
		a[16] = 3 a[26] = 63 a[7] = -66
		a[18] = 12 a[24] = 54 a[7] = -66
		a[15] = 0 a[26] = 63 a[8] = -63
		a[12] = -25 a[27] = 84 a[9] = -59
		a[17] = 4 a[25] = 55 a[9] = -59
		a[20] = 19 a[22] = 32 a[10] = -51
		a[13] = -16 a[25] = 55 a[11] = -39
		a[16] = 3 a[23] = 36 a[11] = -39
		a[17] = 4 a[18] = 12 a[13] = -16
		
		three sum count : 14
	 */
}
