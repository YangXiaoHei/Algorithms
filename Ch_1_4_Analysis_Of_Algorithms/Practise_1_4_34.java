package Ch_1_4_Analysis_Of_Algorithms;

import java.util.*;
import edu.princeton.cs.algs4.*;

/*
 * 思路 :
 * 
 * 首先我们把距离定义为无穷大，然后用两个索引指向 lo = 0 和 hi = max
 * 猜一次 lo, 再猜一次 hi, 因为后面这次猜了 hi，所以假如结果是冷，说明神秘数字在 mid 的左边，更靠近 lo，
 * 那么就令 hi = mid
 * 如果结果是热，说明神秘数字在 mid 的右边，更靠近 hi，那么就令 lo = mid
 * 然后重复上述步骤，直到猜中
 * 
 */
public class Practise_1_4_34 {
	static class GuessGame {
		public enum Result { CORRECT, HOT, COLD };
		private int secretNumber;
		private int prevDistance;
		public int maximum; // [0, maximum]
		public GuessGame(int limit) { maximum = limit; }
		public void play() { 
			prevDistance = Integer.MAX_VALUE;
			secretNumber = StdRandom.uniform(0, maximum + 1); 
			StdOut.printf("范围是 %d ~ %d, 要猜测的数字是 %d\n",0, maximum, secretNumber);
		}
		public Result guess(int guessNumber) {
			int curDistance = Math.abs(guessNumber - secretNumber);
			if (curDistance == 0) return Result.CORRECT;
			Result r = curDistance < prevDistance ? Result.HOT : Result.COLD;
			prevDistance = curDistance;
			return r;
		}
	}
	public static void playGame_2lgN(GuessGame game) {
		game.play();
		int lo = 0, hi = game.maximum, guessTimes = 0;
		GuessGame.Result result;
		while (lo <= hi) {
			// 猜一个下界
			result = game.guess(lo);  guessTimes++;
			if (result == GuessGame.Result.CORRECT) 
				break;
			
			// 猜一个上界
			result = game.guess(hi); guessTimes++;
			if (result == GuessGame.Result.CORRECT)
				break;
			
			// 开始二分查找
			int mid = (lo + hi) / 2;
			switch (result) {
				case COLD : { hi = mid; } break;
				case HOT : { lo = mid; } break;
				default : break;
			}
		}
		StdOut.printf("一共猜了 %d 次\n", guessTimes);
	}
	public static void main(String[] args) {
		GuessGame game = new GuessGame(5);
		playGame_2lgN(game);	
	}
	// output
	/*
	 *  范围是 0 ~ 5, 要猜测的数字是 5
		一共猜了 2 次

	 */
}
