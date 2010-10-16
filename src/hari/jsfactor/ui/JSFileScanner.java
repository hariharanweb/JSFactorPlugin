package hari.jsfactor.ui;

import hari.jsfactor.ui.contants.IJSFactorContants;
import hari.jsfactor.ui.contants.IJSFactorTokens;
import hari.jsfactor.ui.contants.IJSRules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;

public class JSFileScanner extends RuleBasedPartitionScanner {

	public JSFileScanner() {
		List<IPredicateRule> jsRules = new ArrayList<IPredicateRule>();

		IPredicateRule jsKeywordPredictor = new JSWordPredictor(new JSWordDetector(),IJSFactorContants.JS_KEYWORDS,IJSFactorTokens.JS_KEYWORD_TOKEN);
		IPredicateRule jsOperatorPredictor = new JSWordPredictor(new JSOperatorDetector(),IJSFactorContants.JS_OPERATORS,IJSFactorTokens.JS_OPERATOR_TOKEN);

		jsRules.add(IJSRules.STRING_RULE_SINGLE_QUOTE);
		jsRules.add(IJSRules.STRING_RULE_DOUBLE_QUOTE);
		jsRules.add(IJSRules.SINGLE_LINE_COMMENT_RULE);
		jsRules.add(IJSRules.MULTI_LINE_COMMENT_RULE);

		jsRules.add(jsOperatorPredictor);
		jsRules.add(jsKeywordPredictor);

		IPredicateRule[] rules = new IPredicateRule[jsRules.size()];
		
		setPredicateRules(jsRules.toArray(rules));
	}
}
