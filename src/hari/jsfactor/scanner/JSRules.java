package hari.jsfactor.scanner;

import hari.jsfactor.ui.contants.IJSRules;

import java.util.ArrayList;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;

public class JSRules extends RuleBasedScanner {

	public JSRules() {
		ArrayList<IRule> myRuleList = new ArrayList<IRule>();
		
		myRuleList.add(IJSRules.OPEN_CURLY_BRACES);
		
		myRuleList.add(IJSRules.FUNCTION_RULE);
		
		myRuleList.add(IJSRules.SINGLE_LINE_COMMENT_RULE);
		myRuleList.add(IJSRules.MULTI_LINE_COMMENT_RULE);
		
		IRule[] ruleArray = new IRule[myRuleList.size()];
		setRules(myRuleList.toArray(ruleArray));
	}
}
