package hari.jsfactor.ui;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

public class JSWordPredictor extends WordRule implements IPredicateRule {

	private final Token successToken;

	public JSWordPredictor(IWordDetector wordDetector, String[] validWords, Token successToken) {
		super(wordDetector, Token.UNDEFINED, false);
		this.successToken = successToken;
		
		for (int i = 0; i < validWords.length; i++) {
			addWord(validWords[i], successToken);
		}
		
	}

	@Override
	public IToken getSuccessToken() {
		return successToken;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		return super.evaluate(scanner);
	}
}
