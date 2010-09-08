package hari.jsfactor.jsobjects;

import java.util.List;

public interface IJSObject {
	public List<IJSObject> getContainingObjects();
	public void addContainingObjects(IJSObject jsObject);
}
