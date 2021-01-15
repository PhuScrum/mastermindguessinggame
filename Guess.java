import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * You need to implement an algorithm to make guesses
 * for a 4-digits number in the method make_guess below
 * that means the guess must be a number between [1000-9999]
 * PLEASE DO NOT CHANGE THE NAME OF THE CLASS AND THE METHOD
 */
public class Guess {
	static int myguess =1234;
	static int totalGuess = 0;
	static int bothGuessStrike = 0;
	Map<String, String> h = new HashMap<String, String>() {{
		put("a","b");
	}};
	public static int firstGuess(){
		int firstguess=1234;
		return firstguess;
	}

	public static int make_guess(int hits, int strikes) {
		bothGuessStrike = hits+strikes;
		myguess= firstGuess();
		/**
		 * first guess
		 *
		 */
		while(totalGuess==1){
			if(bothGuessStrike==0){
				myguess= 5678;
			}
		}

		/*
		 * IMPLEMENT YOUR GUESS STRATEGY HERE
		 */
		totalGuess++;
		return myguess;
	}
}