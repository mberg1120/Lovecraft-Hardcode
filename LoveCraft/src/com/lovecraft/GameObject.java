package com.lovecraft;

public abstract class GameObject {
	
	protected String description;
	protected String objectName;
	protected String Key;
	protected String SkeletonKey;
	public static boolean lamp = false;
	
	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return objectName;
	}
	
//	 can the object be used with the given parameters?
//	 i.e. can torch be used with fire to create light?
	 
	public abstract boolean useObject();
	

}
