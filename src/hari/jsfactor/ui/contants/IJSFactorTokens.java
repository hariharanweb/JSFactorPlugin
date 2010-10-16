package hari.jsfactor.ui.contants;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public interface IJSFactorTokens {
	Token COMMENT_TOKEN = new Token(IJSFactorContants.JS_COMMENT);
	Token STRING_TOKEN = new Token(IJSFactorContants.JS_STRING);
	Token JS_KEYWORD_TOKEN = new Token(IJSFactorContants.JS_KEYWORD);
	Token JS_OPERATOR_TOKEN = new Token(IJSFactorContants.JS_OPERATOR);
	Token JS_FUNCTION_TOKEN = new Token(IJSFactorContants.JS_FUNCTION);
	IToken OPEN_CURLY_BRACES_TOKEN = new Token(IJSFactorContants.JS_OPEN_CURLY_BRACES);
	IToken JS_SINGLE_LINE_VARIABLE_TOKEN = new Token(IJSFactorContants.JS_VARIABLE);
}