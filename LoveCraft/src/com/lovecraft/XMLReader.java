package com.lovecraft;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * This is the XML parser used for the code, and 
 * takes the Room.XML and the Item.XML and makes them 
 * rooms and items.
 * @author Michael
 * @author Steven
 * @author Nathan
 * 
 */
public class XMLReader {

	static Vector<Room> roomList = new Vector<Room>(); //array for holding the Rooms
	static Vector<Item> itemList = new Vector<Item>(); //array for holding the Items
	/**
	 * This is the XML Parser that has a return type Room. 
	 * @return
	 */
	public Room XML()
	{
		boolean keepGoing = true;
		try
		{
		File file2 = new File("item.xml"); //reads in the file 'item.xml'
		DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance(); //basis for any document builder
		DocumentBuilder db2 = dbf2.newDocumentBuilder(); //creates a document builder
		Document doc2 = db2.parse(file2); //creates the DOM
		doc2.getDocumentElement().normalize();//'buffers' the DOM.
		NodeList nodeList2 = doc2.getElementsByTagName("item");//Grabs the node item to begin the distribution.

		//A forloop that will continue until the items are all inside the array
		for (int s = 0; s < nodeList2.getLength(); s++)
			{

				Node fstNode = nodeList2.item(s); //Grabs the item 's'

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) //If it is still a node type, it will enter the loop.
				{
					
					Element fstElmnt = (Element) fstNode;
					String holder2=""; //creates a holder for saving the description
					
					NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("name");
				Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
				NodeList fstNm = fstNmElmnt.getChildNodes();     
				String objectName = ((Node) fstNm.item(0)).getNodeValue();

				fstNmElmntLst = fstElmnt.getElementsByTagName("desc");
				fstNmElmnt = (Element) fstNmElmntLst.item(0);
				fstNm = fstNmElmnt.getChildNodes();     
				holder2 += ((Node) fstNm.item(0)).getNodeValue();
				
				itemList.add(new Item(objectName, holder2));
				}
			}
		}
		catch(ParserConfigurationException pce) 
		{			
			System.out.println(pce + "\nThe parsing of the item xml failed at this point, please fix this portion of the XML. " +
		"\nThe program will now be terminating.");
//			pce.printStackTrace();
			System.exit(0);
		} 
		catch(SAXException se)
		{
			System.out.println(se + "\nThe parsing of the item xml failed at this point, please fix this portion of the XML. " +
					"\nThe program will now be terminating.");
//			se.printStackTrace();
			System.exit(0);
		} 
		catch(IOException ioe)
		{
			System.out.println(ioe + "\nThe item XML file could not be found, and the program will now terminate.");
//			ioe.printStackTrace();
			System.exit(0);
		}
		
		try
		{
			File file = new File("room.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeLst = doc.getElementsByTagName("room");

			for (int s = 0; s < nodeLst.getLength(); s++)
			{

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE)
				{

					Element fstElmnt = (Element) fstNode;
					String holder="";

					NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("name");
					Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
					NodeList fstNm = fstNmElmnt.getChildNodes();     
					String roomName = ((Node) fstNm.item(0)).getNodeValue();
					
					fstNmElmntLst = fstElmnt.getElementsByTagName("isLocked");
					fstNmElmnt = (Element) fstNmElmntLst.item(0);
					fstNm = fstNmElmnt.getChildNodes();     
					holder +=((Node) fstNm.item(0)).getNodeValue();

					fstNmElmntLst = fstElmnt.getElementsByTagName("isLit");
					fstNmElmnt = (Element) fstNmElmntLst.item(0);
					fstNm = fstNmElmnt.getChildNodes();     
					holder += " " + ((Node) fstNm.item(0)).getNodeValue();
					
					fstNmElmntLst = fstElmnt.getElementsByTagName("desc");
					fstNmElmnt = (Element) fstNmElmntLst.item(0);
					fstNm = fstNmElmnt.getChildNodes();     
					holder += " " + ((Node) fstNm.item(0)).getNodeValue();

					fstNmElmntLst = fstElmnt.getElementsByTagName("desc2");
					fstNmElmnt = (Element) fstNmElmntLst.item(0);
					fstNm = fstNmElmnt.getChildNodes();     
					holder += " " + ((Node) fstNm.item(0)).getNodeValue();
					
					fstNmElmntLst = fstElmnt.getElementsByTagName("north");
					fstNmElmnt = (Element) fstNmElmntLst.item(0);
					fstNm = fstNmElmnt.getChildNodes();     
					holder += " " + ((Node) fstNm.item(0)).getNodeValue();

					fstNmElmntLst = fstElmnt.getElementsByTagName("east");
					fstNmElmnt = (Element) fstNmElmntLst.item(0);
					fstNm = fstNmElmnt.getChildNodes();     
					holder += " "+((Node) fstNm.item(0)).getNodeValue();

					fstNmElmntLst = fstElmnt.getElementsByTagName("south");
					fstNmElmnt = (Element) fstNmElmntLst.item(0);
					fstNm = fstNmElmnt.getChildNodes();     
					holder += " "+((Node) fstNm.item(0)).getNodeValue();

					fstNmElmntLst = fstElmnt.getElementsByTagName("west");
					fstNmElmnt = (Element) fstNmElmntLst.item(0);
					fstNm = fstNmElmnt.getChildNodes();     
					holder += " "+((Node) fstNm.item(0)).getNodeValue();

					fstNmElmntLst = fstElmnt.getElementsByTagName("itemList");
					fstNmElmnt = (Element) fstNmElmntLst.item(0);
					fstNm = fstNmElmnt.getChildNodes();     
					holder += " "+((Node) fstNm.item(0)).getNodeValue();
					 
					roomList.add(new Room(roomName,holder));

				}

			}
		} 
		catch(ParserConfigurationException pce) 
		{
			System.out.println(pce + "\nThe parsing of the room xml failed at this point, please fix this portion of the XML. " +
				"\nThe program will now be terminating.");
//			pce.printStackTrace();
			System.exit(0);
		} 
		catch(SAXException se)
		{
			System.out.println(se + "\nThe parsing of the room xml failed at this point, please fix this portion of the XML. " +
			"\nThe program will now be terminating.");
//			se.printStackTrace();
			System.exit(0);
		} 
		catch(IOException ioe)
		{
			System.out.println(ioe + "\nThe room XML file could not be found, and the program will now terminate.");
//			ioe.printStackTrace();
			System.exit(0);
		}
		// all of the rooms have now been, the list of who is next to them is stored in a list
		// we are now going to connect those associations with other rooms, we assume # means no links

		for(int i = 0; i<roomList.size(); i++)
		{
			keepGoing = true;
			StringTokenizer tokenz = new StringTokenizer(roomList.get(i).holder);
			// order is north, east, south, west
			String locked = tokenz.nextToken();
			if(locked.equals("true"))
				roomList.get(i).isLocked = true;
			else
				roomList.get(i).isLocked = false;
			String lit = tokenz.nextToken();
			if(lit.equals("true"))
				roomList.get(i).isLit = true;
			else
				roomList.get(i).isLit = false;
			String desc = tokenz.nextToken();
			do{		
				if(desc.contains("#"))
				{
					keepGoing = false;
					desc = desc.replace("#", "");
				}
				else if(desc.contains("\\"))
					desc = desc.replace("\\", "\n");
				else
					desc += " " + tokenz.nextToken();
			}while(keepGoing);
			roomList.get(i).description = desc;
			String desc2 = tokenz.nextToken();
			keepGoing = true;
			do{		
				if(desc2.contains("#"))
				{
					keepGoing = false;
					desc2 = desc2.replace("#", "");
				}
				else if(desc2.contains("\\"))
					desc2 = desc2.replace("\\", "\n");
				else
					desc2 += " " + tokenz.nextToken();
			}while(keepGoing);
			roomList.get(i).description2 = desc2;
			String north = tokenz.nextToken();
			if(north.equals("#"))
				roomList.get(i).north=roomList.get(i);
			else
			{
				roomList.get(i).north=getRoomByName(north);
				if(roomList.get(i).north == null)
					roomList.get(i).north=roomList.get(i);					
			}
			String east = tokenz.nextToken();
			if(east.equals("#"))
				roomList.get(i).east=roomList.get(i);
			else
			{
				roomList.get(i).east=getRoomByName(east);
				if(roomList.get(i).east == null)
					roomList.get(i).east=roomList.get(i);					
			}
			String south = tokenz.nextToken();
			if(south.equals("#"))
				roomList.get(i).south=roomList.get(i);
			else
			{
				roomList.get(i).south=getRoomByName(south);
				if(roomList.get(i).south == null)
					roomList.get(i).south=roomList.get(i);					
			}
			String west = tokenz.nextToken();
			if(west.equals("#"))
				roomList.get(i).west=roomList.get(i);
			else
			{
				roomList.get(i).west=getRoomByName(west);
				if(roomList.get(i).west == null)
					roomList.get(i).west=roomList.get(i);					
			}
			String item = tokenz.nextToken();
			if(tokenz.hasMoreTokens()) //skeleton key case
				item += " " + tokenz.nextToken();
			if(item.equals("#"))
				;
			else
				roomList.get(i).roomInventory.add(getItemByName(item));
		}
		return roomList.get(0);
	}
	/**
	 *Grabs the rooms and returns the room that was made. Otherwise, returns null. 
	 *@param roomName
	 */
	public static Room getRoomByName(String roomName)
	{
		for(int i=0; i < roomList.size(); i++)
		{
			if(roomList.get(i).objectName.equals(roomName))
				return roomList.get(i);
		}
		System.out.println("XMLReader->getRoomByName: error XML specified room " + roomName +" but it was not found");
		return null;
	}
	// returns a new item containing the objectName of the item specified
	/**
	 * Gets the item from the ArrayList and returns a new item. 
	 */
	public static Item getItemByName(String objectName)
	{
		for(int i = 0; i < itemList.size(); i++)
		{
			if(itemList.get(i).objectName.equals(objectName))
				return new Item(itemList.get(i).objectName, itemList.get(i).description);
		}
		
//		for(int i=0; i < itemList.size(); i++)
//		{
//			if(itemList.get(i).objectName.equals(objectName))
//				return new Item(itemList.get(i).objectName,itemList.get(i).holder, itemList.get(i).itemDescription);
//		}
		System.out.println("The item name " + objectName + " placed in the room could not be parsed." +
				"\nPlease fix your item.xml file." +
				"\nThe program will now terminate...");
		System.exit(0);
		return null;
	}
	
}