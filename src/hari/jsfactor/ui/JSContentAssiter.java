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

	public JSContentAssiter(JSDocumentScanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		
		List<CompletionProposal> proposalList = new ArrayList<CompletionProposal>();
		
		JSFunction[] jsFunctions = scanner.getJSFunctions();
		for (JSFunction function : jsFunctions) {
			proposalList.add(new CompletionProposal(function.getFunctionName(), offset, 0, 0));
		}
		
		String[] jsKeywords = IJSFactorContants.JS_KEYWORDS;
		for (String keyword : jsKeywords) {
			if(JSKeywordContentAssist.keywordsContentAssistant.containsKey(keyword)){
				String content = JSKeywordContentAssist.keywordsContentAssistant.get(keyword);
				proposalList.add(new CompletionProposal(content, offset, 0, content.length(), null, keyword, null, null));
			}else{
				proposalList.add(new CompletionProposal(keyword, offset, 0, keyword.length()));
			}
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
