package hari.jsfactor.ui;

import hari.jsfactor.ui.contants.IJSColorConstants;
import hari.jsfactor.ui.contants.IJSFactorContants;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class JSFactorSourceViewConfiguration extends SourceViewerConfiguration {

	private DefaultTagScanner defaultTagScanner;

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return IJSFactorContants.contentTypes;
	}
	
	@Override
	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		
		PresentationReconciler reconciler = new PresentationReconciler();
		
		DefaultDamagerRepairer damagerRepairer = new DefaultDamagerRepairer(getDefaultTagScanner());
		reconciler.setDamager(damagerRepairer, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(damagerRepairer, IDocument.DEFAULT_CONTENT_TYPE);
		
		NonRuleBasedDamagerRepairer commentDamageRepairer = new NonRuleBasedDamagerRepairer(new TextAttribute(IJSColorConstants.COMMENT_COLOR));
		reconciler.setDamager(commentDamageRepairer, IJSFactorContants.JS_COMMENT);
		reconciler.setRepairer(commentDamageRepairer, IJSFactorContants.JS_COMMENT);

		NonRuleBasedDamagerRepairer jsKeywordDamageRepairer = new NonRuleBasedDamagerRepairer(new TextAttribute(IJSColorConstants.JS_KEYWORD_COLOR));
		reconciler.setDamager(jsKeywordDamageRepairer, IJSFactorContants.JS_KEYWORD);
		reconciler.setRepairer(jsKeywordDamageRepairer, IJSFactorContants.JS_KEYWORD);

		NonRuleBasedDamagerRepairer jsOperatorDamageRepairer = new NonRuleBasedDamagerRepairer(new TextAttribute(IJSColorConstants.JS_OPERATOR_COLOR));
		reconciler.setDamager(jsOperatorDamageRepairer, IJSFactorContants.JS_OPERATOR);
		reconciler.setRepairer(jsOperatorDamageRepairer, IJSFactorContants.JS_OPERATOR);
		
		NonRuleBasedDamagerRepairer jsStringDamageRepairer = new NonRuleBasedDamagerRepairer(new TextAttribute(IJSColorConstants.JS_STRING_COLOR));
		reconciler.setDamager(jsStringDamageRepairer, IJSFactorContants.JS_STRING);
		reconciler.setRepairer(jsStringDamageRepairer, IJSFactorContants.JS_STRING);

		return reconciler;
	}

	private ITokenScanner getDefaultTagScanner() {
		if(defaultTagScanner == null ){
			defaultTagScanner = new DefaultTagScanner();
		}
		return defaultTagScanner;
	}
	
	
}
