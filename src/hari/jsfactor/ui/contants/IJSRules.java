package hari.jsfactor.ui.contants;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.SingleLineRule;

public interface IJSRules {
	IPredicateRule STRING_RULE_DOUBLE_QUOTE = new SingleLineRule("\"", "\"", IJSFactorTokens.STRING_TOKEN);
	IPredicateRule STRING_RULE_SINGLE_QUOTE = new SingleLineRule("\'", "\'", IJSFactorTokens.STRING_TOKEN);
	IPredicateRule SINGLE_LINE_COMMENT_RULE = new SingleLineRule("//", "", IJSFactorTokens.COMMENT_TOKEN);
	IPredicateRule MULTI_LINE_COMMENT_RULE = new MultiLineRule("/*", "*/", IJSFactorTokens.COMMENT_TOKEN);
	
	IPredicateRule OPEN_CURLY_BRACES = new SingleLineRule("{", null, IJSFactorTokens.OPEN_CURLY_BRACES_TOKEN);
	IPredicateRule FUNCTION_RULE = new MultiLineRule("function", ")", IJSFactorTokens.JS_FUNCTION_TOKEN);
}
