import java.util.*;


public class Guess {
	/**
	 * pre-defined variables
	 */
	static HashSet<String> possibleAnswers = new HashSet<>(); // create a list of strings, 'possibleAnswers'
	static Result result = new Result(); // create new result
	static String guess; // create a string call move, which represent a step generating a guess


	/**
	 * initialize a pool of possible answers range from 1000-9999.
	 * @return
	 */
	private static HashSet<String> GetAllPossibleAnswers() {
		for (int i = 1000; i <= 9999; i++) {
			possibleAnswers.add(String.valueOf(i)); // change int type to string type
		}
		return possibleAnswers;
	}


	/**
	 * function to eliminate elements in possibleAnswers by comparing guess with result
	 * @param guess: e.g 9431 a guess number.
	 * @param result: (number of strikes, number of hits)
	 */
	private static void eliminatePossibilities(String guess, Result result) { // take guess and result as parameters
		List<String> temp = new ArrayList<>(possibleAnswers); // create a temporary string type list, then assign it to 'possibleAnswers'
		
        for (String s : temp) {// loop through each element in string type list of possible answers

            // Check if amount of hits or strikes of element when comparing with guess are or not equal to  amount of hits or strikes of result
			if (ResultToGuess(s, guess).getHits() != result.getHits() || ResultToGuess(s, guess).getStrikes() != result.getStrikes()) { 
				possibleAnswers.remove(s); // remove elements if not equal
			}
		}
	}


	/**
	 * function to compare 2 numbers as string type, return hits and strikes
	 * @param target:
	 * @param guess
	 * @return (number of hits, number of strikes)
	 */
	public static Result ResultToGuess(String target, String guess) { // take element and guess as parameters
		char[] des = (target).toCharArray(); // des as target
		char[] src = (guess).toCharArray(); // src as guess
		int hits=0;
		int strikes=0;

		// process strikes
		for (int i=0; i<4; i++) {
			if (src[i] == des[i]) {
				strikes++;
				des[i] = 'a';
				src[i] = 'a';
			}
		}
		// process hits
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				if (src[i]!='a') {
					if (src[i]==des[j]) {
						hits++;
						des[j] = 'a';
						break;
					}
				}
			}
		}

		result.setHits(hits); // call set hits function
		result.setStrikes(strikes); // call set strikes function

		return result; // return result(hits, strikes)
	}

	/**
	 *
	 * @param set of possible answers
	 * @return
	 */
	private static String getRandomFromSet(HashSet<String> set){
		Random rand = new Random();
		System.out.println(set.size());
		int index = rand.nextInt(set.size());
		Iterator<String> iter = set.iterator();
		for (int i = 0; i < index; i++) {
			iter.next();
		}
		return iter.next();
	}

	/**
	 * main strategy to make guess after receiving responses of (hits, strikes)
	 * @param hits
	 * @param strikes
	 * @return
	 */
	public static int make_guess(int hits, int strikes) {

		/**
		 * first guess is to get random numbers from 1000-9999 in the pool of PossibleAnswers
		 */
		if (guess == null) {
			possibleAnswers = GetAllPossibleAnswers();
			guess = getRandomFromSet(possibleAnswers); // generate random numbers of string type in 'possibleAnswers' list
			return Integer.parseInt(guess);  // return first guess as the random number from 1000-9999
		}
		/**
		 * Following Guesses after the first guess. like guess 2, guess 3, guess 4, etc.
		 * eliminate other possibilities, based on responses of hits & strikes of the previous guess.
		 * and randomly pick a number from the pool of possible answers.
		 */
		Result result = new Result(hits, strikes);
		eliminatePossibilities(guess, result);

		/**
		 * new guess after eliminate the possibilities.
		 */
		guess = getRandomFromSet(possibleAnswers);
		return Integer.parseInt(guess); // return guess
	}
}