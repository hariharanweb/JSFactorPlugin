package hari.jsfactor.scanner;

import hari.jsfactor.jsobjects.IJSObject;
import hari.jsfactor.jsobjects.JSComment;
import hari.jsfactor.jsobjects.JSFile;
import hari.jsfactor.jsobjects.JSFunction;
import hari.jsfactor.jsobjects.JSVariable;
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
				String content = getContent(document);
				if (nextToken.equals(IJSFactorTokens.COMMENT_TOKEN)) {
					JSComment comment = new JSComment(content);
					programStack.lastElement().addContainingObjects(comment);
				}
				if (nextToken.equals(IJSFactorTokens.JS_FUNCTION_TOKEN)) {
					JSFunction function = JSFunction.getJSFunction(content, rules.getTokenOffset(), rules.getTokenLength());
					programStack.lastElement().addContainingObjects(function);
					functionList.add(function);
					previousAssignableJSObject = function;
				}
				if(nextToken.equals(IJSFactorTokens.OPEN_CURLY_BRACES_TOKEN)){
					programStack.push(previousAssignableJSObject);
				}
				if(nextToken.equals(IJSFactorTokens.JS_SINGLE_LINE_VARIABLE_TOKEN)){
					JSVariable jsVariable = JSVariable.getJSVariable(content,rules.getTokenOffset(), rules.getTokenLength());
					previousAssignableJSObject.addContainingObjects(jsVariable);
				}
				else if(nextToken.equals(JSRules.TOKEN)){
					System.out.println(content);
				}
				nextToken = rules.nextToken();
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

	}

	private String getContent(IDocument document) throws BadLocationException {
		return document.get(rules.getTokenOffset(),
				rules.getTokenLength());
	}

	public JSFile getJSFile() {
		return jsFile;
	}

	public JSFunction[] getJSFunctions() {
		JSFunction[] functions = new JSFunction[functionList.size()];
		return functionList.toArray(functions);
	}
}
