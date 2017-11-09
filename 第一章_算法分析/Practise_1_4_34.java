package 第一章_算法分析;

import java.util.*;
import edu.princeton.cs.algs4.*;

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
