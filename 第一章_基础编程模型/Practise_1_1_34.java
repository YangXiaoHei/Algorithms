package 第一章_基础编程模型;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_1_1_34 {
	/*
	 * 打印出最大和最小值
	 * 
	 * 只需要固定的变量就能实现
	 */
	private static int max = Integer.MIN_VALUE;
	private static int min = Integer.MAX_VALUE;
	public static void MaxMin(int n) {
		if(n > max) StdOut.println("最大值为 : " + (max = n));
		if(n < min) StdOut.println("最小值为 : " + (min = n));
	}
	public static void test1() {
		StdOut.println("========================================");
		for(int i = 0; i < 10; i++)
			MaxMin(StdRandom.uniform(100));
	}
	
	/*
	 * 打印出所有数的中位数
	 * 
	 * 需要用数组把 N 个输入都保存起来
	 */
	public static void median(int[] arr) {
		int[] newArr = new int[arr.length];
		System.arraycopy(arr, 0, newArr, 0, arr.length);
		Arrays.sort(newArr);
		if(newArr.length % 2 == 0) 
			StdOut.println("中位数 : " + 
					(newArr[newArr.length / 2] + newArr[newArr.length / 2 - 1]) / 2);
		else
			StdOut.println("中位数 : " + newArr[newArr.length / 2]);
	}
	public static void test2() {
		StdOut.println("========================================");
		int[] arr = new int[100];
		for(int i = 0; i < 100; i++)
			arr[i] = StdRandom.uniform(1000);
		Arrays.sort(arr);
		
		median(arr);
	}
	
	/*
	 * 打印出第 k 小的数，k 小于100
	 * 
	 * 只需要固定的变量和固定大小的数组
	 */
	private static int k = 3;
	private static int left = k;
	private static int[] arr = new int[k];
	public static int max(int[] arr) {
		int max = arr[0];
		for(int i = 1; i < arr.length; i++)
			if(max < arr[i])
				max = arr[i];
		return max;
	}
	public static void theKthMin(int n) {
		int max = 0;
		int maxIndex = 0;
		if(--left >= 0) {
			arr[left] = n;
			max = max(arr);
		} else {
			maxIndex = 0;
			max = arr[0];
			for(int i = 1; i < arr.length; i++)
				if(max < arr[i]) {
					max = arr[i];
					maxIndex = i;
				}
			if(n < max)
				arr[maxIndex] = n;
			max = max(arr);
		}
		StdOut.println("第 " + k + " 小的元素为 " + max);
	}
	public static void test3() {
		StdOut.println("========================================");
		for(int i = 0; i < 10; i++)
			theKthMin(StdRandom.uniform(10));
	}
	
	/*
	 * 打印出所有数的平方和
	 * 
	 * 可以用固定的变量实现
	 */
	private static double squareSum = 0;
	public static void squareTotalSum(double n) {
		squareSum += n * n;
		StdOut.println("平方和为 : " + squareSum);
	}
	public static void test4() {
		StdOut.println("========================================");
		for(int i = 0; i < 10; i++)
			squareTotalSum(i);
	}
	
	/*
	 * 打印出 N 个数的平均值
	 * 
	 * 可以用固定的变量实现
	 */
	public static int averageCount = 0;
	public static double averageSum = 0;
	public static void average(double n) {
		averageSum += n;
		averageCount++;
		StdOut.println("平均值为 : " + averageSum / averageCount);
	}
	public static void test5() {
		StdOut.println("========================================");
		for(int i = 0; i < 10; i++)
			average(i);
	}
	
	/*
	 * 打印出大于平均值的数的百分比
	 * 
	 * 需要用数组把 N 个输入都保存起来
	 */
	public static void percentage(double[] a) {
		double sum = 0;
		for(int i = 0; i < a.length; i++)
			sum += a[i];
		double average = sum / a.length;
		int count = 0;
		for(int i = 0; i < a.length; i++)
			if(a[i] > average)
				count++;
		StdOut.println("大于平均数的百分比 : " + count * 1.0 / a.length);	
	}
	public static void test6() {
		StdOut.println("========================================");
		double[] arr = new double[100];
		for(int i = 0; i < 100; i++)
			arr[i] = StdRandom.uniform(0, 1000.0);
		percentage(arr);
	}
	
	/*
	 * 将 N 个数按升序打印
	 * 
	 * 需要用数组把 N 个输入都保存起来
	 */
	public static void ascendOrder(double[] a) {
		Arrays.sort(a);
		StdOut.println(Arrays.toString(a));
	}
	public static void test7() {
		StdOut.println("========================================");
		double[] arr = new double[100];
		for(int i = 0; i < 100; i++)
			arr[i] = StdRandom.uniform(0, 1000.0);
		ascendOrder(arr);
	}
	
	/*
	 * 将 N 个数按照随机顺序打印
	 * 
	 * 需要用数组把 N 个输入都保存起来
	 */
	public static void randomPrint(int[] arr) {
		StdOut.print("乱序后 : ");
		boolean[] printed = new boolean[arr.length];
		int index = 0;
		int count = arr.length;
		while(--count >= 0) {
			while(printed[index] == true)
				index = StdRandom.uniform(0, arr.length);
			StdOut.print(arr[index] + " ");
			printed[index] = true;
		}
		StdOut.println();
	}
	public static void test8() {
		StdOut.println("========================================");
		int[] arr = new int[100];
		for(int i = 0; i < 100; i++)
			arr[i] = StdRandom.uniform(0, 100);
		StdOut.println("原数组 : " + Arrays.toString(arr));
		randomPrint(arr);
	}
	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
		test8();
	}
	// output : 
	/*
	 *  ========================================
		最大值为 : 16
		最小值为 : 16
		最大值为 : 32
		最大值为 : 72
		最大值为 : 79
		最小值为 : 0
		最大值为 : 89
		========================================
		中位数 : 448
		========================================
		第 3 小的元素为 2
		第 3 小的元素为 2
		第 3 小的元素为 6
		第 3 小的元素为 6
		第 3 小的元素为 6
		第 3 小的元素为 4
		第 3 小的元素为 4
		第 3 小的元素为 2
		第 3 小的元素为 2
		第 3 小的元素为 2
		========================================
		平方和为 : 0.0
		平方和为 : 1.0
		平方和为 : 5.0
		平方和为 : 14.0
		平方和为 : 30.0
		平方和为 : 55.0
		平方和为 : 91.0
		平方和为 : 140.0
		平方和为 : 204.0
		平方和为 : 285.0
		========================================
		平均值为 : 0.0
		平均值为 : 0.5
		平均值为 : 1.0
		平均值为 : 1.5
		平均值为 : 2.0
		平均值为 : 2.5
		平均值为 : 3.0
		平均值为 : 3.5
		平均值为 : 4.0
		平均值为 : 4.5
		========================================
		大于平均数的百分比 : 0.47
		========================================
		[23.068664149148876, 38.82318689754349, 41.7591766205615, 42.335903391461095, 66.28993314619724, 73.10495573933228, 78.11056676561834, 79.58735644216652, 89.11695993476599, 93.29134717720511, 103.92288920765647, 115.37733445593223, 117.50180637791186, 119.44369334857929, 141.06448881209545, 142.78777902005857, 144.1819379657202, 165.07862308202414, 171.76982333394054, 172.78578156524404, 180.22324092298513, 182.58348863286955, 183.74599526220726, 199.63787081423246, 211.45648407822904, 226.81174744631394, 231.46327955099233, 241.28408330631768, 255.4983160011911, 256.9178418039404, 263.39509192000776, 266.0676345737567, 279.38202993781704, 280.5607970880576, 282.2776596324512, 282.78086355863144, 299.4061717967955, 302.3070686752949, 333.02300412911814, 342.14928100922424, 382.341941739799, 396.7565166529825, 451.10561506692403, 455.08537337701785, 457.2120764532132, 459.09062796592804, 462.13253565318524, 463.1131317686595, 463.2952112815164, 465.19670037070347, 484.19492100140735, 485.0688873895913, 490.9137381663038, 491.3516680888156, 499.87231120184305, 504.17102320779173, 516.6262857276164, 527.71080458842, 537.2250739744254, 538.9175696784214, 541.3895740898743, 545.604912433691, 554.4742079821082, 554.9584028635879, 580.1119031354913, 580.9170931735614, 586.0316642044742, 590.336345068512, 595.530584581264, 601.1318778750056, 621.648776489057, 626.6451332716497, 634.6016823584486, 651.4667497055301, 651.6349396043965, 661.2420741403085, 667.3301701002373, 669.0740905865115, 693.3188809419132, 703.0278219285084, 710.7289536151721, 712.5693902044006, 725.7189659041519, 740.0159647694384, 746.8761197543774, 749.6733229759787, 750.9467069210204, 764.5332304652432, 787.4532658324309, 800.9181290448777, 808.6106315589954, 814.5129625751385, 830.9622444737395, 843.5410745982274, 906.6364334030525, 912.1229063012431, 920.5796379013391, 957.7374744072084, 960.6579617244828, 990.001474910785]
		========================================
		原数组 : [82, 6, 44, 60, 10, 59, 77, 82, 28, 17, 89, 50, 18, 50, 68, 44, 29, 23, 20, 82, 89, 65, 25, 38, 34, 86, 25, 87, 97, 51, 63, 71, 0, 23, 46, 54, 67, 18, 26, 81, 66, 15, 91, 74, 36, 20, 59, 35, 58, 36, 3, 86, 54, 64, 78, 69, 24, 51, 84, 39, 34, 39, 21, 43, 13, 74, 98, 92, 59, 93, 83, 63, 40, 33, 4, 46, 46, 11, 34, 30, 44, 6, 25, 88, 56, 1, 47, 20, 96, 94, 76, 46, 78, 3, 62, 62, 81, 12, 36, 40]
		乱序后 : 82 24 47 59 17 63 62 67 51 50 65 18 26 71 11 3 50 94 20 74 92 66 82 56 59 82 62 6 44 15 81 88 23 51 34 78 4 20 91 77 25 35 76 6 64 13 63 30 46 54 58 25 39 40 69 3 96 39 36 86 84 18 46 89 43 83 34 44 25 97 59 44 86 36 12 1 68 98 34 46 89 23 36 20 38 21 46 29 28 40 78 54 74 87 93 81 60 33 10 0 

	 */
}
