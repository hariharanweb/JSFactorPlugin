package hari.jsfactor.scanner;

import hari.jsfactor.jsobjects.IJSObject;
import hari.jsfactor.jsobjects.JSComment;
import hari.jsfactor.jsobjects.JSFile;
import hari.jsfactor.jsobjects.JSFunction;
import hari.jsfactor.ui.contants.IJSFactorTokens;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IToken;

public class JSDocumentScanner {

	private JSRules rules;
	private JSFile jsFile;
	private Stack<IJSObject> programStack;
	private ArrayList<JSFunction> functionList;

	public JSDocumentScanner() {
		rules = new JSRules();
	}

	public void updateModel(IDocument document) {

		jsFile = new JSFile();
		functionList = new ArrayList<JSFunction>();
		programStack = new Stack<IJSObject>();
		programStack.push(jsFile);

		rules.setRange(document, 0, document.getLength());

		IToken nextToken = rules.nextToken();
		IJSObject previousAssignableJSObject = jsFile;

		try {
			while (!nextToken.isEOF()) {
				if (nextToken.equals(IJSFactorTokens.COMMENT_TOKEN)) {
					String content = document.get(rules.getTokenOffset(),
							rules.getTokenLength());
					JSComment comment = new JSComment(content);
					programStack.lastElement().addContainingObjects(comment);
				}
				if (nextToken.equals(IJSFactorTokens.JS_FUNCTION_TOKEN)) {
					int tokenOffset = rules.getTokenOffset();
					int tokenLength = rules.getTokenLength();
					
					String content = document.get(tokenOffset,
							tokenLength);
					JSFunction function = getFunctionDefinition(content, tokenOffset, tokenLength);
					programStack.lastElement().addContainingObjects(function);
					functionList.add(function);
					previousAssignableJSObject = function;
				}
				if(nextToken.equals(IJSFactorTokens.OPEN_CURLY_BRACES_TOKEN)){
					programStack.push(previousAssignableJSObject);
				}
				
				nextToken = rules.nextToken();
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

	}

	protected JSFunction getFunctionDefinition(String content, int offset, int length) {
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

	public JSFile getJSFile() {
		return jsFile;
	}

	public JSFunction[] getJSFunctions() {
		JSFunction[] functions = new JSFunction[functionList.size()];
		return functionList.toArray(functions);
	}
}
