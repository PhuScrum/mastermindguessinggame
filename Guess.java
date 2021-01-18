import java.util.*;


public class
Guess {
	static HashSet<String> possibleAnswers = new HashSet<>(); // create a list of strings, 'possibleAnswers'
	static Result result = new Result(); // create new result
	static String guess; // create a string call move, which represent a step generating a guess

	// create a list of strings, 'GetAllPossibleAnswers',
	private static HashSet<String> GetAllPossibleAnswers() {
		for (int i = 1000; i <= 9999; i++) { // add numbers of int type from 1000 to 9999 to the list
			possibleAnswers.add(String.valueOf(i)); // change int type to string type
		}
		return possibleAnswers; // return numbers of string type
	}

    // function to eliminate elements in possibleAnswers by comparing guess with result
	private static void eliminateGuess(String guess, Result result) { // take guess and result as parameters
		List<String> temp = new ArrayList<>(possibleAnswers); // create a temporary string type list, then assign it to 'possibleAnswers'
		
        for (String s : temp) {// loop through each element in string type list of possible answers

            // Check if amount of hits or strikes of element when comparing with guess are or not equal to  amount of hits or strikes of result
			if (ResultToGuess(s, guess).getHits() != result.getHits() || ResultToGuess(s, guess).getStrikes() != result.getStrikes()) { 
				possibleAnswers.remove(s); // remove elements if not equal
			}
		}
	}

    //function to compare 2 numbers as string type, return hits and strikes
	public static Result ResultToGuess(String element, String guess) { // take element and guess as parameters
		char[] des = (guess).toCharArray(); // des as guess
		char[] src = (element).toCharArray(); // src as element
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

    // function to get a random number in range from min to max
	private static int rand(int max, int min){
		Random number = new Random(); // create random number
		int range = max - min + 1;
		return number.nextInt(range) + min; // return random number
	}

	private static String getRandomFromSet(HashSet<String> set){
		Random rand = new Random();
		int index = rand.nextInt(set.size());
		Iterator<String> iter = set.iterator();
		for (int i = 0; i < index; i++) {
			iter.next();
		}
		return iter.next();
	}
    // function to create a reasonable guess
	public static int make_guess(int hits, int strikes) {

        // first guess
		if (guess == null) {
			possibleAnswers = GetAllPossibleAnswers();
			guess = getRandomFromSet(possibleAnswers); // generate random numbers of string type in 'possibleAnswers' list
			return Integer.parseInt(guess);  // return random numbers
		}

		Result result = new Result(hits, strikes); // get result from process guess
		eliminateGuess(guess, result); // call function eliminateGuess

		// After first guess
		guess = getRandomFromSet(possibleAnswers);  // generate random  numbers of string type in 'possibleAnswers' list
		return Integer.parseInt(guess); // return random numbers
	}
}