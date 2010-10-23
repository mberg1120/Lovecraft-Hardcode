package com.lovecraft;
/*
 * XML interpreter source copied from 
 * http://www.java-tips.org/java-se-tips/javax.xml.parsers/how-to-read-xml-file-in-java.html
 */
import java.io.File;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {

	static Vector<Room> roomList = new Vector<Room>();
	static Vector<Item> itemList = new Vector<Item>();
	public Room XML()
	{
		try
		{
		File file2 = new File("C:\\Users\\Michael\\Desktop\\Lovecraft-Hardcode\\Reference\\item.xml"); // this should be changed to a relative path
		DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
		DocumentBuilder db2 = dbf2.newDocumentBuilder();
		Document doc2 = db2.parse(file2);
		doc2.getDocumentElement().normalize();
		NodeList nodeList2 = doc2.getElementsByTagName("item");

		for (int s = 0; s < nodeList2.getLength(); s++)
		{

			Node fstNode = nodeList2.item(s);

			if (fstNode.getNodeType() == Node.ELEMENT_NODE)
			{

				Element fstElmnt = (Element) fstNode;
				String holder2="";

				NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("name");
				Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
				NodeList fstNm = fstNmElmnt.getChildNodes();     
				String itemName = ((Node) fstNm.item(0)).getNodeValue();

				fstNmElmntLst = fstElmnt.getElementsByTagName("desc");
				fstNmElmnt = (Element) fstNmElmntLst.item(0);
				fstNm = fstNmElmnt.getChildNodes();     
				holder2 +=((Node) fstNm.item(0)).getNodeValue();

				fstNmElmntLst = fstElmnt.getElementsByTagName("descFloor");
				fstNmElmnt = (Element) fstNmElmntLst.item(0);
				fstNm = fstNmElmnt.getChildNodes();     
				holder2 += " " + ((Node) fstNm.item(0)).getNodeValue();
				
				StringTokenizer tokens = new StringTokenizer(holder2);
				String desc = tokens.nextToken();
				String descF = tokens.nextToken();
				while(tokens.hasMoreTokens())
					descF += " " + tokens.nextToken();
				itemList.add(new Item(itemName, desc, descF));
				System.out.println(itemName + " " + holder2);
			}
		}
		}catch (Exception e) {
				e.printStackTrace();
			}
		try
		{
			File file = new File("C:\\Users\\Michael\\Desktop\\Lovecraft-Hardcode\\Reference\\room.xml"); // this should be changed to a relative path
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

					fstNmElmntLst = fstElmnt.getElementsByTagName("desc");
					fstNmElmnt = (Element) fstNmElmntLst.item(0);
					fstNm = fstNmElmnt.getChildNodes();     
					holder +=((Node) fstNm.item(0)).getNodeValue();

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
					
					
					
					System.out.println(holder);

//					/// this retrieves a list of items in the room, the itemXML hasn't been finished though
//					   // so this will be applied after
//					NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("itemList");
//					Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
//					NodeList lstNm = lstNmElmnt.getChildNodes();
//					System.out.println("room item list: " + ((Node) lstNm.item(0)).getNodeValue());
					 
					roomList.add(new Room(roomName,holder));

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// all of the rooms have now been added, the list of who is next to them is stored in a list
		// we are now going to connect those associations with other rooms, we assume # means no links

		for(int i = 0; i<roomList.size(); i++)
		{
			StringTokenizer tokenz = new StringTokenizer(roomList.get(i).holder);
			// order is north, east, south, west
			String desc = tokenz.nextToken();
			roomList.get(i).description = desc;
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
			if(tokenz.hasMoreTokens())//skeleton key case
				item += " " + tokenz.nextToken();
			if(item.equals("#"))
				;
			else
				roomList.get(i).roomInventory.add(getItemByName(item));
		}
		return roomList.get(0);
	}
	// look through every room for the name of the room we want to link to, if we find
	// it stop looking and return the room, if we don't find it return null
	public static Room getRoomByName(String roomName)
	{
		for(int i=0; i< roomList.size(); i++)
		{
			if(roomList.get(i).Name.equals(roomName))
				return roomList.get(i);
		}
		System.out.println("XMLReader->getRoomByName: error XML specified room " + roomName +" but it was not found");
		return null;
	}
	// returns a new item containing the name of the item specified
	public static Item getItemByName(String itemName)
	{
		for(int i = 0; i < itemList.size(); i++)
		{
			if(itemList.get(i).itemName.equals(itemName))
				return new Item(itemList.get(i).itemName, itemList.get(i).itemDescription, itemList.get(i).floorDescription);
		}
		
//		for(int i=0; i < itemList.size(); i++)
//		{
//			if(itemList.get(i).itemName.equals(itemName))
//				return new Item(itemList.get(i).itemName,itemList.get(i).holder, itemList.get(i).itemDescription);
//		}
		System.out.println("XMLReader->getItemByName: error XML specified item " + itemName +" but it was not found");
		return null;
	}
	
}