package hari.jsfactor.ui.contants;

import org.eclipse.jface.text.IDocument;

public interface IJSFactorContants {
	
	String JS_COMMENT = "JS_COMMENT";
	String JS_KEYWORD = "JS_KEYWORD";
	String JS_STRING = "JS_STRING";
	String JS_OPERATOR = "JS_OPERATOR";
	String JS_FUNCTION = "JS_FUNCTION";
	String JS_OPEN_CURLY_BRACES = "JS_OPEN_CURLY_BRACES";
	String JS_VARIABLE = "JS_VARIABLE";
	
	String[] contentTypes = new String[] { IDocument.DEFAULT_CONTENT_TYPE,
			IJSFactorContants.JS_COMMENT, IJSFactorContants.JS_KEYWORD,
			IJSFactorContants.JS_OPERATOR, IJSFactorContants.JS_STRING };

	String[] JS_OPERATORS = new String[] { "+", "-", "/", "*", "++", "--",
			"+=", "-=", "*=", "/=", "<", ">", "==", "===", "!=", "!==" };

	String[] JS_KEYWORDS = new String[] { "break", "continue", "do", "for",
			"import", "new", "this", "void", "case", "default", "else",
			"function", "in", "return", "typeof", "while", "comment", "delete",
			"export", "if", "label", "switch", "var", "with" };
}
