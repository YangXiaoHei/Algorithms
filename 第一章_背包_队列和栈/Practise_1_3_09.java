package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;
import static 第一章_背包_队列和栈.Practise_1_3_04.*;

public class Practise_1_3_09 {
    /*
     * 思路 :
     * 
     * 准备两个栈，一个用于存放拼接字符串的结果栈，一个用于读取运算符的运算符栈
     * 
     * 浏览表达式，读到 + - * '/' 就入运算符栈
     * 读到 右括号 就在结果栈弹两个元素出来，再把运算符栈顶元素弹出来，拼接成 补全的字符串 再放回结果栈
     * 
     * 
     */
	public static String transform(String str) {
		 LinkedListStack<String> complete = new LinkedListStack<String>();
		 LinkedListStack<String> optr = new LinkedListStack<String>();
		 for(String s : str.split("")) {
			 if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
				 optr.push(s);
			 else if (s.charAt(0) >= '0' && s.charAt(0) <= '9') 
				 complete.push(s);
			 else if (s.equals("(") || s.equals(")")) {
				 String op2 = complete.pop(),
						op1 = complete.pop(),
						opt = optr.pop();
				 complete.push("(" + op1 + opt + op2 + ")");
			 }
		 }
		 return complete.pop();
	 }

   public static void main(String[] args) {
       StdOut.println(transform("1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )"));
   }
}
