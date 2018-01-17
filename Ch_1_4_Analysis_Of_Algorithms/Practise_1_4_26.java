package Ch_1_4_Analysis_Of_Algorithms;

public class Practise_1_4_26 {
	public static void main(String[] args) {
		/*
		 * 如果 A(a, a^3) B(b, b^3) C(c, c^3) 三点共线，说明 AB 和 BC 斜率相等
		 * 
		 * (b^3 - a^3) / (b - a) = (c^3 - b^3) / (c - b)
		 * a^2 + ab + b^2 = c^2 + cb + b^2
		 * a^2 - c^2 + b(a - c) = 0
		 * (a - c)(a + c) + b(a - c) = 0
		 * (a - c)(a + b + c) = 0
		 * 故 a = c 或者 a + b + c = 0
		 */
	}
}
