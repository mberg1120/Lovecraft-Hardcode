package com.lovecraft;

public abstract class GameObject {
	static Inventory playerInventory = new Inventory();
	static Inventory roomInventory = new Inventory();
	
	protected String description = "";
	protected String objectName = "";

	public String getDescription() {
		System.out.println(description);
		return description;
	}
	
	public void setDescription(String pDescription){
		if (pDescription != null) {
			description = pDescription;
		}
	}
	
	public String getName() {
		System.out.println(objectName);
		return objectName;
	}
	
	public void setName(String pName) {
		if (pName != null) {
			objectName = pName;
		}
	}
	
	
	
//	 can the object be used with the given parameters?
//	 i.e. can torch be used with fire to create light?
	 
	public abstract boolean useObject();
	

}
