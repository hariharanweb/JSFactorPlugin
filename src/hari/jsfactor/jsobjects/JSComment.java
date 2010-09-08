package hari.jsfactor.jsobjects;

import java.util.List;

public class JSComment implements IJSObject{
	
	private final String content;

	public JSComment(String content) {
		this.content = content;
	}

	@Override
	public List<IJSObject> getContainingObjects() {
		return null;
	}

	public String getContent() {
		return content;
	}

	@Override
	public void addContainingObjects(IJSObject jsObject) {
	}

	@Override
	public String toString() {
		return content;
	}
}
