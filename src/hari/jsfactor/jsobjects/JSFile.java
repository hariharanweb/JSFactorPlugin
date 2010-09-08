package hari.jsfactor.jsobjects;

import java.util.ArrayList;
import java.util.List;

public class JSFile implements IJSObject{

	private ArrayList<IJSObject> containingObjects;

	public JSFile() {
		containingObjects = new ArrayList<IJSObject>();
	}
	
	public void addContainingObjects(IJSObject jsObject){
		containingObjects.add(jsObject);
	}
	
	@Override
	public List<IJSObject> getContainingObjects() {
		return containingObjects;
	}
	
	@Override
	public String toString() {
		return "JS Functions";
	}

}
