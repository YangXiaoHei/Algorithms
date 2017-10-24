package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_1_1_21 {
	public static void main(String[] args) {
		int rows = 0;
		Object[][] item = null;
		Object[][] newItem = null;
		String name = null;
		while(!(name = StdIn.readString()).equals("end")) {
			int number1 = StdIn.readInt();
			int number2 = StdIn.readInt();
			rows++;
			
			if(item != null) {
				Object[][] old = item;
				newItem = new Object[rows][];
				for(int i = 0; i < rows; i++) 
					newItem[i] = new Object[4];
				for(int i = 0; i < rows - 1; i++)
					for(int j = 0; j < 4; j++)
						newItem[i][j] = old[i][j];
				newItem[rows - 1][0] = name;
				newItem[rows - 1][1] = number1;
				newItem[rows - 1][2] = number2;
				newItem[rows - 1][3] = String.format("%.3f", (double)number1 / number2);
				item = newItem;
			} else {
				item = new Object[1][];
				for(int i = 0; i < 1; i++) 
					item[i] = new Object[4];
				item[0][0] = name;
				item[0][1] = number1;
				item[0][2] = number2;
				item[0][3] = String.format("%.3f", (double)number1 / number2);
				newItem = item;
			}	
		}
		StdOut.println(Arrays.deepToString(newItem));
	}
	// output :
	/*
	 * 	yangxiaohei
		32
		43
		yangdahei
		43
		54
		yangzhonghei
		99
		99
		end
		[[yangxiaohei, 32, 43, 0.744], [yangdahei, 43, 54, 0.796], [yangzhonghei, 99, 99, 1.000]]
	 */
}
