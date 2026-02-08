package Week01;

import java.io.FileNotFoundException;
import java.util.Random;

public interface DicewareInterface {
	
	/***
	 * The name of the diceware cryptographic word list file
	 */
	static final String wordlist = "src/Week01/EFFWordList.txt";
	
	/***
	 * Seeded pseudo random number generator to be used
	 */
	static final Random rn = new Random(42);
	
	/***
	 * Generates a password using the diceware process. Indices into the cryptographic word list file 
	 * are 5 decimal digits generated in most significant to least significant order.
	 * 
	 * @param minlength -- minimum password length
	 * @param separator -- character used to separate diceware generated words/phrases
	 * @return -- the generated password
	 * @throws FileNotFoundException -- if the diceware cryptographic word list file cannot be opened
	 */
	String genpassword(int minlength, char separator) throws FileNotFoundException;

}