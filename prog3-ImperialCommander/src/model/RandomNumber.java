package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 
 * Random number used for the fights in the ImperialCommander game.
 * @author Eduard Andrei Duta | NIE: X9281056G
 *
 */
public class RandomNumber {
	/**
	 * Random generator
	 */
	private static Random generator = new Random(1L);
	/**
	 * List that stores all random numbers generated in a game.
	 */
	private static List<Integer> list = new ArrayList<Integer>();
	/**
	 * Constructor for the random number that uses specified number -1 as the maximum.
	 * @param max sets the +1 maximum number
	 * @return a random number
	 */
	public static int newRandomNumber(int max) {
		int r = generator.nextInt(max);
		list.add(r);
		return r;
	}
	/**
	 * Returns a list with all the random numbers used in a game.
	 * @return list with all random numbers used
	 */
	public static List<Integer> getRandomNumberList() {
		return list;
	}
	/**
	 * Resets random counter used to store the random numbers in the list. For tests purposes only.
	 */
	public static void resetRandomCounter() {
        list.clear();
        generator = new Random(1L);
    } 
}
