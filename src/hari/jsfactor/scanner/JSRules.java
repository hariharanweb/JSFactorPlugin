package hari.jsfactor.scanner;

import hari.jsfactor.ui.contants.IJSRules;

import java.util.ArrayList;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;

public class JSRules extends RuleBasedScanner {

	public static final Token TOKEN = new Token("SOMETHING");

	public JSRules() {
		ArrayList<IRule> myRuleList = new ArrayList<IRule>();
		
		myRuleList.add(IJSRules.OPEN_CURLY_BRACES);
		
		myRuleList.add(IJSRules.FUNCTION_RULE);
		
		myRuleList.add(IJSRules.SINGLE_LINE_COMMENT_RULE);
		myRuleList.add(IJSRules.MULTI_LINE_COMMENT_RULE);
		
		myRuleList.add(IJSRules.SINGLE_LINE_VARIABLE_RULE);
		
		
		IRule[] ruleArray = new IRule[myRuleList.size()];
		setRules(myRuleList.toArray(ruleArray));
	}
}
