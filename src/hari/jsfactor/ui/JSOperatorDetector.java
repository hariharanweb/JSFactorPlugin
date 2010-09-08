package hari.jsfactor.ui;

import hari.jsfactor.ui.contants.IJSFactorContants;

import org.eclipse.jface.text.rules.IWordDetector;

public class JSOperatorDetector implements IWordDetector {

	@Override
	public boolean isWordStart(char c) {
		for (int i = 0; i < IJSFactorContants.JS_OPERATORS.length; i++) {
			if(IJSFactorContants.JS_OPERATORS[i].charAt(0) == c){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isWordPart(char c) {
		if(c != ' ') return true;
		return false;
	}
}
