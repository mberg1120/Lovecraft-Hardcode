package com.lovecraft;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Michael
 * @comments This is the Chasm, where it takes the console and the user can't leave until the random number is modulated into 0. 
 */
public class Chasm {
	/**
	 * Drops the player into the chasm. 
	 * @param scan
	 */
	public static void fallIntoChasm(Scanner scan)
	{
		boolean keepGoing = true;
		Random generator = new Random();
		String userInput;
		System.out.println("You jumped into the Chasm....\n" +
				"You have found yourself completely surrounded by darkness. There is no clear path," +
				"\nand there is no light source. Your torch was dropped on the ground before you fell. " +
				"\nYou may go North, South, East, or West, but I doubt it will matter.");
		do{
			int r = generator.nextInt();
			userInput = scan.nextLine();
			r = r % 2;
			if( userInput.toLowerCase().contains("diablo"))
				System.out.println("There is a large roar heard and flames seen in the distance..." +
						"\nStay a while and listen...");
			else if (userInput.toLowerCase().contains("cow"))
				System.out.println("There is no cow level...");

			else if(r == 0)
			{
				System.out.println("You have managed to climb out of the chasm and returned to the Cave." +
						"\nYou have returned to the room you were previously in." +
						"\nI'd suggest not going back into the Chasm, that proved... unhelpful.");
				keepGoing = false;
			}
			else
				System.out.println("As you continue onward, darkness still surrounds you. It seems no progress has been made. " +
						"\nYou may go North, South, East or West.");
		}while(keepGoing == true);
	}

}
