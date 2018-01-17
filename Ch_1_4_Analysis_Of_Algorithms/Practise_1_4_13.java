package Ch_1_4_Analysis_Of_Algorithms;

import java.util.*;
import edu.princeton.cs.algs4.*;
/*
 * 
 * 思路 :
 * 
 * 请看下方注释
 * 
 */
public class Practise_1_4_13 {
	
	 // 16 /* 对象开销 */ + 8 /* double */ + 4 /* int */ = 28 + 4 /* 填充字节 */ = 32
	static class Accumulator {
		/*
		 * 8
		 */
		private double total;
		/*
		 * 4
		 */
		private int N;
		// ...
	}
	
	// 16 + 8 * 3 = 40
	static class Transaction {
		/*
		 * 8 + 
		 */
		private final String who = "";
		/*
		 * 8
		 */
		private final String when = "";
		/*
		 * 8
		 */
		private final String amount = "";
		// ...
	}
	
	/*
	 * 当容量为 C 且含有 N 个元素时
	 * 
	 * 
	 * 16 + 8 + 4 + 8 * C = 28 + 8C
	 * 
	 */
	static class FixedCapacityStackOfStrings {
		/*
		 * 8
		 */
		private String[] a;
		/*
		 * 4
		 */
		private int N;
		// ...
	}
	
	/*
	 * 16 + 8 + 8 = 32
	 */
	static class Point2D {
		/*
		 * 0
		 */
	    public static  Comparator<Point2D> X_ORDER;
	    /*
	     * 0
	     */
	    public static  Comparator<Point2D> Y_ORDER;
	    /*
	     * 0
	     */
	    public static  Comparator<Point2D> R_ORDER;
	    /*
	     * 8
	     */
	    private  double x;    // x coordinate
	    /*
	     * 8
	     */
	    private  double y;    // y coordinate
	    // ...
	}
	
	/*
	 * 16 + 8 + 8 = 32
	 */
	static class Interval1D {
		/*
		 * 8
		 */
		private  Interval1D x;
		/*
		 * 8
		 */
	    private  Interval1D y;

	}
	
	/*
	 * 16 + 8 + 8 = 32
	 */
	static class Interval2D {
		/*
		 * 8
		 */
		private  Interval1D x;
		/*
		 * 8
		 */
	    private  Interval1D y;
	    // ...
	}
	
	// 16 + 8 = 24
	static class Double {
		/*
		 * 0
		 */
	    public static final double POSITIVE_INFINITY = 1.0 / 0.0;
	    /*
	     * 0
	     */
	    public static final double NEGATIVE_INFINITY = -1.0 / 0.0;
	    /*
	     * 0
	     */
	    public static final double NaN = 0.0d / 0.0;
	    /*
	     * 0
	     */
	    public static final double MAX_VALUE = 0x1.fffffffffffffP+1023; 
	    /*
	     * 0
	     */
	    public static final double MIN_NORMAL = 0x1.0p-1022; 
	    /*
	     * 0
	     */
	    public static final double MIN_VALUE = 0x0.0000000000001P-1022; 
	    /*
	     * 0
	     */
	    public static final int MAX_EXPONENT = 1023;
	    /*
	     * 0
	     */
	    public static final int MIN_EXPONENT = -1022;
	    /*
	     * 0
	     */
	    public static final int SIZE = 64;
	    /*
	     * 0
	     */
	    public static final int BYTES = SIZE / Byte.SIZE;
	    /*
	     * 0
	     */
	    public static  Class<Double>   TYPE;
	    /*
	     * 8
	     */
	    private double value;
	    // ...
	}
}
