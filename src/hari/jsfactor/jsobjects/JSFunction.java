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

	public static JSFunction getJSFunction(String content, int offset, int length) {
		String parameters = content.substring(content.indexOf("("));
		String commaSeparatedParamters = parameters.replace("(", "")
				.replace(")", "").trim();
		List<String> parameterList = new ArrayList<String>();
		if (commaSeparatedParamters.length() > 0) {
			String[] individualParameters = commaSeparatedParamters.split(",");
			for (String parameter : individualParameters) {
				parameterList.add(parameter.trim());
			}
		}
		String functionName = content.replace("function", "")
				.replace(parameters, "").trim();
		JSFunction jsFunction = new JSFunction(functionName, parameterList, offset, length);
		return jsFunction;
	}

	public static JSFunction getJSFunctionFromVariable(String content,
			int tokenOffset, int tokenLength) {
		System.out.println("hello "+content);
		return new JSFunction("test", new ArrayList<String>(), 0, 0);
	}
}
