package 第一章_背包_队列和栈;

import static 第一章_背包_队列和栈.Practise_1_3_4.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_3_9 {
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
