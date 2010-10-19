package com.lovecraft;

import java.util.Vector;
import java.util.Random;
import java.util.Scanner;

public class Room extends GameObject {
	
	Vector roomContents = new Vector();
	static String[][] move = new String[20][20];
	
	@Override
	public boolean useObject() {
		
		// use object will reflect what happens when you move from room
		// to room or drop an object... or even use an object with another
		// xml should be able to hold a combination of objects and what happens
		// when the objects are used with each other.
		
		return false;
	}
	
	public void roomContents() {
		getName();
		System.out.println("Contents of " + objectName + ":");
		for (int i = 0; i < roomContents.size(); i++) {
			System.out.println("	" + "-" + roomContents.get(i));
		}
	}
	public static void moveTo(int x, int y)
	{
		move[0][0] = "You are in the original room, you may go South or East.";
		move[1][0] = "One of the three originally lit rooms, it seems to be wreaking, and there is a high volume of humidity in the air.";
		move[2][0] ="";
		move[0][1] ="One of the three originally lit rooms, it seems to be wreaking, and there is a high volume of humidity in the air.";
		move[0][2] ="";
		move[0][3] ="";
		move[0][4] ="";
		move[0][5] ="";
		move[1][1] ="One of the three originally lit rooms, it seems to be wreaking, and there is a high volume of humidity in the air.";
		move[1][2] ="";
		move[2][1] ="";
		move[2][2] ="";
		move[3][1] ="";
		move[3][2] ="";
		move[3][4] ="";
		move[3][5] ="";
		move[1][5] ="";
		move[2][5] ="";
		move[4][4] ="";
																			
		System.out.println(move[x][y]);
	}
	public static void Chasm(Scanner scan)
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
			if(r == 0)
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
