package hari.jsfactor.jsobjects;

import java.util.ArrayList;
import java.util.List;

public class JSVariable implements IJSObject{
	
	private final String variableName;
	private final String variableValue;
	private ArrayList<Integer> usages;
	private final int offset;

	public JSVariable(String variableName,String variableValue, int offset) {
		this.variableName = variableName;
		this.variableValue = variableValue;
		this.offset = offset;
		usages = new ArrayList<Integer>();
	}
	
	@Override
	public List<IJSObject> getContainingObjects() {
		return null;
	}

	@Override
	public void addContainingObjects(IJSObject jsObject) {
	}

	public static IJSObject getJSVariable(String content, int offset, int length){
		String[] split = content.split("var ", 0);
		String[] nameValue = split[1].split("=");

		if(content.contains("function")){
			String parameters = nameValue[1].replace("function(", "");
			parameters = parameters.replaceAll("[)]\\s*[{]", "");
			String[] parameterArray = parameters.split(",");
			List<String> parameterList = new ArrayList<String>();
			for (String parameter : parameterArray) {
				parameterList.add(parameter.trim());
			}
			return new JSFunction(nameValue[0].trim(), parameterList, offset, length);			
		}
		
		return new JSVariable(nameValue[0].trim(), nameValue[1].replace(";", "").trim(),offset);
	}

	public String getVariableName() {
		return variableName;
	}
	
	public String getVariableValue() {
		return variableValue;
	}

	public void addUsage(int usageOffset) {
		usages.add(usageOffset);
	}

	public List<Integer> getUsages() {
		return usages;
	}

	public int getOffset() {
		return offset;
	}
}
