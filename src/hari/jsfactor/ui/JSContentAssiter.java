package hari.jsfactor.ui;

import hari.jsfactor.jsobjects.JSFunction;
import hari.jsfactor.scanner.JSDocumentScanner;
import hari.jsfactor.ui.contants.IJSFactorContants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

public class JSContentAssiter implements IContentAssistProcessor {

	private final JSDocumentScanner scanner;
	private ArrayList<String> keywordProposals;

	public JSContentAssiter(JSDocumentScanner scanner) {
		this.scanner = scanner;
		keywordProposals = new ArrayList<String>();
		String[] jsKeywords = IJSFactorContants.JS_KEYWORDS;
		for (String keyword : jsKeywords) {
			keywordProposals.add(keyword);
		}		
	}

	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		
		List<String> completionProposals = new ArrayList<String>();
		completionProposals.addAll(keywordProposals);
		JSFunction[] jsFunctions = scanner.getJSFunctions();
		for (JSFunction function : jsFunctions) {
			completionProposals.add(function.getFunctionName());
		}
		
		Collections.sort(completionProposals);
		ArrayList<CompletionProposal> proposalList = new ArrayList<CompletionProposal>();
		for (String proposal : completionProposals) {
			proposalList.add(new CompletionProposal(proposal, offset, 0, 0));
		}
		return proposalList.toArray(new CompletionProposal[proposalList.size()]);
	}

	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IContextInformationValidator getContextInformationValidator() {
		// TODO Auto-generated method stub
		return null;
	}

}
