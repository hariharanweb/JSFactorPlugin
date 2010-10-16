package hari.jsfactor.jsobjects;

import java.util.List;

public class JSVariable implements IJSObject{
	
	private final String variableName;
	private final String variableValue;

	public JSVariable(String variableName,String variableValue) {
		this.variableName = variableName;
		this.variableValue = variableValue;
	}
	
	@Override
	public List<IJSObject> getContainingObjects() {
		return null;
	}

	@Override
	public void addContainingObjects(IJSObject jsObject) {
	}

	public static JSVariable getJSVariable(String content, int offset, int length){
		String[] split = content.split("var ", 0);
		String[] nameValue = split[1].split("=");
		return new JSVariable(nameValue[0].trim(), nameValue[1].replace(";", "").trim());
	}

	public String getVariableName() {
		return variableName;
	}
	
	public String getVariableValue() {
		return variableValue;
	}
}
