package hari.jsfactor.jsobjects;

import java.util.ArrayList;
import java.util.List;

public class JSFunction implements IJSObject{
	private List<IJSObject> containingObjects;
	private List<String> parameters;
	private String functionName;
	private final int length;
	private final int offset;
	
	public JSFunction(String functionName, List<String> parameters, int offset, int length) {
		this.functionName = functionName;
		this.parameters = parameters;
		this.offset = offset;
		this.length = length;
		containingObjects = new ArrayList<IJSObject>();
	}

	public void addContainingObjects(IJSObject jsObject){
		containingObjects.add(jsObject);
	}
	
	public List<IJSObject> getContainingObjects() {
		return containingObjects;
	}

	public String getFunctionName() {
		return functionName;
	}

	public List<String> getParameters() {
		return parameters;
	}
	
	@Override
	public String toString() {
		return functionName;
	}

	public int getLength() {
		return length;
	}

	public int getOffset() {
		return offset;
	}
}
