package hari.jsfactor.scanner;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class TestMyRule implements IRule {

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		System.out.println(scanner.read());
		return Token.UNDEFINED;
	}

}
