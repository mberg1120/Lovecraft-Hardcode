package com.lovecraft;
import java.util.Scanner;

public class Client 
{
	public static void main(String[] args)
	{
		// Creates the grid variables used to go from room to room.
		int x = 0;
		int y = 0;
		// Input variable.
		String userInput;
		// Creates a boolean to be use with the DoWhile loop later
		boolean keepGoing = true;
		// Prints the introduction to the user.
		System.out.println("Welcome user, this is Project Love Craft... You have found yourself lying on the ground," +
				"\nand it  appears you have a bump on the back of your head. As you look around, you only " +
				"\nhave 3 areas that are visible, and it appears you are stuck in a dungeon or cave of some sort. " +
				"\nOnce you go forth into this dungeon, you will find objects, it is your choice how to handle them. " +
				"\nYou will have the ability to receive hints in each area, and your first hint is that you must type" +
				"\n'go' and then the word North, West, East or South to move. Currently, you may only go east or south.");
		Scanner scan = new Scanner(System.in);
		// DoWhile loop that will continue looping until the user reaches the end, and then it will
		// terminate and continue forward. Most of the loop is self explanatory, will not need to comment
		// much on it. 
		do{
		userInput = scan.nextLine();
		userInput.toLowerCase();
		if(userInput.equals("go") )
		{
			System.out.println("Go where?");
		}
		else if(userInput.equals("north") || userInput.equals("n") || userInput.equals("go north"))
		{
			y--;
			if(x < 3 && x >  0 && y < 5 && y > 2)
			{
				Room.Chasm(scan);
				y++;
			}
			else
				Room.moveTo(x, y);
			System.out.println("You went North");
		}
		else if(userInput.equals("s") || userInput.equals("south") || userInput.equals("go south"))
		{
			y++;
			if(x < 3 && x >  0 && y < 5 && y > 2)
			{
				Room.Chasm(scan);
				y--;
			}
			else
				Room.moveTo(x, y);
			System.out.println("You went South");
		}
		else if(userInput.equals("e") || userInput.equals("east") || userInput.equals("go east"))
		{
			x++;
			if(x < 3 && x >  0 && y < 5 && y > 2)
			{
				Room.Chasm(scan);
				x--;
			}
			else
				Room.moveTo(x, y);
			System.out.println("You went East");
		}
		else if(userInput.equals("w") || userInput.equals("west") || userInput.equals("go west"))
		{
			x--;
			if(x < 3 && x >  0 && y < 5 && y > 2)
			{
				Room.Chasm(scan);
				x++;
			}
			else
				Room.moveTo(x, y);
			System.out.println("You went West");
		}
		else if (userInput.equals("take") || userInput.equals("pick up") || userInput.equals("pickup"))
		{
			
		}
		else if (userInput.equals("drop"))
		{
			
		}
		else if (userInput.equals("use"))
		{
			
		}
		else if (userInput.equals("i") || userInput.equals("inventroy"))
		{
//			Item.inventoryContents();
		}
		else
			System.out.println("You seem to have used an incorrect command, or you have added an extra space.");
		if(x == 5 && y == 0)
			keepGoing = false;
		}while(keepGoing == true);
		// Prints the end dialogue
		System.out.println("As you open the door... It seems to be that you're at the beginning of a Cave looking out. There also\n" +
				"seems to be a park, how convenient. You don't recognize any of your surroundings. Hell, you can't\n" +
				"remember anything about yourself, let alone what you are, so there is no reason for any of your\n" +
				"surroundings to be known. Though, you see someone coming. If you are to make your escape, you'll\n" +
				"need to take out any eye witnesses. You bludgeon him in the back of the head with your wooden object\n" +
				"and put him in the cave. Do you wish to make your escape?");
		// Allows the user to read the above message, and will print out the end credits once they escape.
		do{
		userInput = scan.nextLine();
		userInput.toLowerCase();
		keepGoing = true;
		if(userInput.equals("yes") || userInput.equals("y") || userInput.equals("escape"))
		{
			System.out.println("Congratulations, You beat Project LoveCraft!\n" +
				"Designed, Written, and Coded By:\n" +
				"Stephen Honda\n" +
				"Michael Berg\n" +
				"Nathan Secrist\n" +
				"Kellie Watson");
			keepGoing = false;
		}
		else
			System.out.println("To escape, type 'yes' 'y' or 'escape'.");
		}while(keepGoing == true);
	}
}
