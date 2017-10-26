package 第一章_背包_队列和栈;

import static 第一章_背包_队列和栈.Practise_1_3_04.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_10 {
	static String[][] optrRankTable = {
			// 		+	  - 	 	* 	   /   ( 	 )     ;
		/* + */	{  "<",  "<",   ">",  ">", ">", "<",  "<"},
		/* - */	{  "<",  "<",   ">",  ">", ">", "<",  "<"},
		/* * */	{  "<",  "<",   "<",  "<", ">", "<",  "<"},
		/* / */	{  "<",  "<",   "<",  "<", ">", "<",  "<"},
		/* ( */	{  ">",  ">",   ">",  ">", ">", "=",  " "},
		/* ) */	{  " ",  " ",   " ",  " ", " ", " ",  " "},
		/* ; */	{  ">",  ">",   ">",  ">", ">", ">",  "="}
	
	};
	public static int rank(String optr) {
		if 		(optr.equals("+")) return 0;
		else if (optr.equals("-")) return 1;
		else if (optr.equals("*")) return 2;
		else if (optr.equals("/")) return 3;
		else if (optr.equals("(")) return 4;
		else if (optr.equals(")")) return 5;
		else if (optr.equals(";")) return 6;
		return -1;
	}
	public static String compare(String push, String stkTop) {
		return optrRankTable[rank(stkTop)][rank(push)];
	}
	public static boolean isOptr(String s) {
		return rank(s) >= 0;
	}
	public static boolean isOpnd(String s) {
		return s.charAt(0) >= '0' && s.charAt(0) <= '9';
	}
	public static String InfixToPostfix(String s) {
		s = s.replaceAll("\\s", "");
		StringBuilder sb = new StringBuilder(s + ";");
		LinkedListStack<String>
				optrStack = new LinkedListStack<String>(),
				opndStack = new LinkedListStack<String>();
		optrStack.push(";");
		while(!optrStack.isEmpty()) {
			if(isOpnd(sb.substring(0, 1))) {
				int cur = 0;
				while(cur != sb.length() && 
					 isOpnd(sb.substring(cur, cur + 1))) cur++;
				opndStack.push(sb.substring(0, cur));
				sb.delete(0, cur);
			} else {
				String optr = sb.substring(0, 1),
				       stkTop = optrStack.peek(),
				       rank = compare(optr, stkTop);
				if (rank.equals(">")) {
					optrStack.push(sb.substring(0, 1));
					sb.delete(0, 1);
				} else if (rank.equals("<")) {
					String opnd2 = opndStack.pop(),
						   opnd1 = opndStack.pop(),
					       optrpop = optrStack.pop(),
					       newString = opnd1 + " " + opnd2 + " " + optrpop;
					opndStack.push(newString);
				} else {
					sb.delete(0, 1);
					optrStack.pop();
				}
			}
		}
		return opndStack.pop();
	}
	public static void main(String[] args) {
		StdOut.println(InfixToPostfix("2 * 3 / (2 - 1) + 3 * ( 4 - 1 )"));
		StdOut.println(InfixToPostfix("11 + 2 * 3 / (4 + 5) - 9 * (8 + 3)"));
	}
	// output 
	/*
	 * 	2 3 * 2 1 - / 3 4 1 - * +
		11 2 3 * 4 5 + / + 9 8 3 + * -
	 */
}
