package 第一章_算法分析;

import java.util.*;
import java.util.concurrent.TimeUnit;

import edu.princeton.cs.algs4.*;

public class Practise_1_4_10 {
    /*
     * 思路 :
     * 
     * 首先我想到一种很简单粗暴的思路，但在最坏情况下时间会达到 O(N)
     * 在这里列出来仅仅为了展示下思路历程
     * 
     * 假设我们在某一次分治操作中，使用 mid 索引命中了目标，那么可以知道，目标序列一定是这样的情况
     * ....... 3 4 5 6 6 6 6 7 7 7 8 9 9 9 10 .........
     *                   👆
     *                   mid
     * 要取得最小索引，我们只需要在此基础上递减 mid 直到 mid 索引指示的位置不再等于查找目标，再将 mid + 1 
     * 即为所求
     * 
     * 上述方法固然很简单，但我们不妨考虑将其应用于以下序列
     * 
     * 1 1 1 1 1 1 1 1 1 1....(都是 1)... 2 2 2 2 2 2 2 2 2 2
     * 
     * 稍加思考就可发现，我们第一次命中目标时，离最小索引的举例在最坏情况下能达到目标序列的一半即 N/2，因此此
     * 查找算法效率降低至 O(N)，显然不满足题目要求的最坏情况下仍然是 O(lgN)
     * 
     * 于是我尝试在原有二分查找算法上做改进
     * 
     * 为了说明改进后的算法策略，我们先来思考，为什么将 while 循环执行条件设置为 lo <= hi ?
     * 
     * 对于序列 ... 2 3 4 5 6 7 8 9 .... 我们想要查找 6 的索引，假设我们某一次 mid 命中了 7
     * 那么我们就将 hi 置为 mid - 1，此时 hi 已经命中了我们的查找目标，由于序列是有序排列，因此 hi 左侧所有元素都小于待查找目标，
     * 因此在随后的所有操作中，lo
     * 都只可能在向靠近 hi 的位置移动，并且最后一定会经历一步 lo = mid + 1 使 lo == hi
     * 此时 lo == hi == mid，查找到指定目标
     * 因此我们会发现，如果在算法执行过程中，某一步 mid 命中了目标的前一个元素，那么 hi = mid - 1 就命中了目标
     * 若某一步 mid 命中了目标的后一个元素，那么 lo = mid + 1 就命中了目标，这两种情况都会导致查找终结在 
     * lo == hi == mid 处
     * 
     * 接下来可以这么想，对于序列，待查找目标是 3，我们在某一步 mid 时命中了 3 的以下索引
     * .... 3 3 3 3 3 4 4 5 ....
     *          👆
     *          mid
     * 这时 lo 肯定不等于 hi，并且我们将新的查找范围缩小至 [0, mid]
     * 对于新的子序列 
     * ....3 3 3
     * 若我们在某一步 mid 命中了 3 的以下索引
     * ... 3 3 3
     *     👆
     *     mid
     * 这时 lo 肯定不等于 hi，然后我们将新的查找范围缩小至 [0, mid]
     * 根据我们上面的推论，该查找会终止于 lo == hi == mid 处,此时即为最小的索引
     * 
     * 这种办法在前一种办法的基础上效率稍微有所提升，但在最坏情况下依然是 N/k * log(N) (k < N)
     * 
     * 但我们得到了一点启发，也就是说，需要实现的算法具有这样的功能，在第一次命中目标时，它仍然对该命中抱有怀疑
     * 因此它会继续深入某个子区域验证该怀疑，直到确定该命中的确是索引最小，或者索引最大方才终止
     * 
     * 因此我们不妨把 判等跳出的分支和大于分支合并，变为如下形式
     * 
     * while (lo < hi) {
     *    mid = (lo + hi) / 2;
     *    (key > arr[mid]) ? lo = mid : hi = mid - 1;
     * }
     * 对任何序列应用此算法，可以看到，都只会在 lo >= hi 时跳出循环
     * 而在循环内部，只要 key <= arr[mid] 查找区域都会缩小至 [0, mid - 1]
     * 因此假如此时命中了序列中的某个目标或者命中比目标小，hi 会持怀疑态度继续缩小查找范围
     * 假设命中了最小索引目标，hi = mid - 1，那么可以知道，以后所有的 mid，都会比 hi 此时的位置小，所以
     * 随着 lo 不断靠近 hi，循环终止于 lo == hi
     * 而这个终止索引，只需要加 1，即为查找目标的最小索引
     */
	static class BinarySearchModified {
		/*
		 *  in the worst situation total time will close to O(N)
		 */
		public static int rankFoolish_A(int key, int[] array) {
			int lo = 0, hi = array.length - 1, mid = 0;
			while (lo <= hi) {
				mid = (lo + hi) / 2;
				if 		(array[mid] > key) hi = mid - 1;
				else if (array[mid] < key) lo = mid + 1;
				else	break;
			}
			if (array[mid] != key) return -1;
			while (mid >= 0 && array[mid] == key) mid--;
			return mid + 1;
		}
		/*
		 * continue to search until the middle index equals to right side index
		 * 
		 * in that way, we can guarantee O((N/k) * logN) in the worst situation
		 */
		public static int rankFoolish_B(int key, int[] array) {
			int lo = 0, hi = array.length - 1, mid = 0;
			while (lo <= hi) {
				mid = (lo + hi) / 2;
				if 		(array[mid] > key) {  hi = mid - 1; }
				else if (array[mid] < key) {  lo = mid + 1; }
				else if (lo != hi)	{ lo = 0; hi = mid; }
				else 	 break; 
			}
			return array[mid] == key ? mid : -1;
		}
	    /*
	     * in that way, we can guarantee O(logN) in the worst situation
	     */
		public static int rank(int key, int[] arr)  {
		    int lo = 0, hi = arr.length - 1, mid = 0;
		    while (lo < hi) {
		        mid = (int)Math.ceil((lo + hi) / 2.0);
		        if (key > arr[mid]) 
		            lo = mid;
		        else 
		            hi = mid - 1;
		    }
		    // hi = 0
		    if (arr[hi] == key) return hi; 
		    // hi != 0
		    return ++hi == arr.length || arr[hi] != key ? -1 : hi;
		}
		public static int maximumRank(int key, int[] arr) {
            int lo = 0, hi = arr.length - 1, mid = 0;
            while (lo < hi) {
                mid = (lo + hi) / 2;
                if (arr[mid] > key)
                    hi = mid;
                else
                    lo = mid + 1;
            }
            // lo = arr.length - 1
            if (arr[lo] == key) return lo;
            // lo != arr.length - 1
            return --lo < 0 || arr[lo] != key ? -1 : lo;
        }
		public static int[] sourceArr(int N) {
//			int[] arr = new int[N];
//			for (int i = 0; i < N; i++)
//				arr[i] = StdRandom.uniform(0, 10);
//			Arrays.sort(arr);
//			return arr;
		    return new int[] {0, 1, 2, 2, 3};
		}
		public static void printArray(int[] arr) {
			for (int i = 0; i < arr.length; i++)
				if ((i + 1) % 10 == 0)
					StdOut.print(arr[i] + "\n");
				else
					StdOut.print(arr[i] + " ");
			StdOut.println();
		}
		public static void test(int key, int N) {
			int[] arr = sourceArr(N);
			printArray(arr);
			StdOut.println("minimum index of " + key + " is " + maximumRank(key, arr));
		}
	}
	public static void main(String[] args)  {
		BinarySearchModified.test(4, 100);
	}
	// output
	/*
	 *  0 0 0 0 0 0 0 0 0 0
		0 0 0 1 1 1 1 1 1 1
		1 1 1 1 1 2 2 2 2 2
		2 2 2 2 3 3 3 3 3 3
		3 3 3 3 4 4 4 4 4 4
		4 4 4 4 4 4 5 5 5 5
		5 5 5 5 6 6 6 6 6 6
		6 6 7 7 7 7 7 7 7 7
		7 7 7 7 8 8 8 8 8 8
		9 9 9 9 9 9 9 9 9 9
		
		minimum index of 3 is 34
	 */
}
