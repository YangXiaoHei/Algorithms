package Ch_1_3_Bags_Queues_And_Stacks;

import static Ch_1_3_Bags_Queues_And_Stacks.Practise_1_3_04.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_10 {
    /*
     * 思路 :
     * 
     * 准备一张运算符优先级表
     * 使其可以以以下两个例子的方式读取优先级 :
     * 对于 [3][2] 元素解释为 如果当前运算符栈顶元素为 / 并且表达式中读到了 *
     * 那么 栈顶元素 / 优先级小于 表达式中 * 优先级
     * 对于 [2][1] 元素解释为 如果当前运算符栈顶元素为 * 并且表达式中读到了 +
     * 那么栈顶元素 * 优先级大于 表达式中 + 优先级
     * 
     * 对于表达式 (1 + 2) * 3 整个执行流程如下
     * 
     * 在运算符栈中放入 ;
     * 拼接字符串成 (1 + 2) * 3;
     * 
     * ( 的优先级比 ; 高
     * ( 入运算符栈
     * 1 入操作数栈
     * + 的优先级比 ( 高
     * + 入运算符栈
     * 2 入操作数栈
     * ) 的优先级比 + 小
     * 弹出操作数栈中的两个元素 1 2，弹出运算符栈顶元素 +，拼接成 1 2 + 放回到操作数栈中
     * ) 的优先级等于 ( 
     * 弹出 (
     * * 的优先级比 ; 高
     * * 入运算符栈
     * 3 入操作数栈
     * ; 的优先级比 * 低
     * 弹出操作数栈中两个元素 "1 2 +" "3"，弹出运算符栈顶元素 *, 拼接成 1 2 + 3 * 放回到操作数栈中
     * ; 的优先级等于 ; 
     * 弹出 ;
     * 运算符栈为空，跳出循环
     * 操作数栈中结果即为 "1 2 + 3 *"
     */
	static String[][] optrRankTable = {
			// 		+	  - 	 * 	   /   ( 	 )     ;
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
