package hari.jsfactor.scanner;

import org.eclipse.jface.text.rules.IWordDetector;

public class JSFunctionDetector implements IWordDetector {

	@Override
	public boolean isWordStart(char c) {
		System.out.println("Word start "+c);
		if(c == 'a') return true;
		return false;
	}

	@Override
	public boolean isWordPart(char c) {
		System.out.println("Word part"+c);
		return false;
	}

}
