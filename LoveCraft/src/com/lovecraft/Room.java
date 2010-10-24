package com.lovecraft;


public class Room extends GameObject {
	Room north,east,south,west; // these are the rooms N/E/S/W of me
	String Name; // this is my name
	String holder; // this is a holder string for while the object is being constructed
	Inventory roomInventory; // this is a list of items that i have
	boolean isLocked = false;
	boolean isLit = true;
	
	public Room(String name)
	{
		Name = name;
		roomInventory = new Inventory(); // i need an empty inventory to add things to
	}
	public Room(String name,String hold)
	{
		Name = name;
		holder = hold;
		roomInventory = new Inventory(); // i need an empty inventory to add things to
	}

	public void receiveItem(Item name)
	{
		roomInventory.add(name); // the checks occur in client, if we make it here just add the item
	}
	public void roomContents() 
	{
		roomInventory.inventoryContents();
	}
	public void removeItem(String item)
	{
		roomInventory.remove(item);
	}
	// this has its own class, it probably doesn't need to be here, maybe delete later
//	public static void Chasm(Scanner scan)
//	{
//		boolean keepGoing = true;
//		Random generator = new Random();
//		String userInput;
//		System.out.println("You jumped into the Chasm....\n" +
//				"You have found yourself completely surrounded by darkness. There is no clear path," +
//				"\nand there is no light source. Your torch was dropped on the ground before you fell. " +
//				"\nYou may go North, South, East, or West, but I doubt it will matter.");
//		do{
//			int r = generator.nextInt();
//			userInput = scan.nextLine();
//			r = r % 2;
//			if(r == 0)
//			{
//				System.out.println("You have managed to climb out of the chasm and returned to the Cave." +
//						"\nYou have returned to the room you were previously in." +
//						"\nI'd suggest not going back into the Chasm, that proved... unhelpful.");
//				keepGoing = false;
//			}
//			else
//				System.out.println("As you continue onward, darkness still surrounds you. It seems no progress has been made. " +
//						"\nYou may go North, South, East or West.");
//		}while(keepGoing == true);
//	}

}
