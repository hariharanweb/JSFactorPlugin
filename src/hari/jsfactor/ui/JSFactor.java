package hari.jsfactor.ui;

import hari.jsfactor.scanner.JSDocumentScanner;
import hari.jsfactor.ui.outline.JSFactorContentOutline;

import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class JSFactor extends TextEditor {

	private JSFactorContentOutline outlinePage;
	private JSDocumentScanner scanner;

	public JSFactor() {
		scanner = new JSDocumentScanner();
		JSFactorSourceViewConfiguration configuration = new JSFactorSourceViewConfiguration(scanner);
		setSourceViewerConfiguration(configuration);
		setDocumentProvider(new JSFactorDocumentProvider());
	}
	
	@Override
	public Object getAdapter(Class adapter) {
		if(IContentOutlinePage.class.equals(adapter)){
			if(outlinePage == null){
				IDocument document = getDocumentProvider().getDocument(getEditorInput());
				outlinePage = new JSFactorContentOutline(this,scanner);
				if(document != null){
					outlinePage.setInput(document);
					outlinePage.update(document);
				}
			}
			return outlinePage;
		}
		return super.getAdapter(adapter);
	}
	
	@Override
	protected boolean isLineNumberRulerVisible() {
		return true;
	}
	
	@Override
	protected void editorSaved() {
		IDocument document = getDocumentProvider().getDocument(getEditorInput());
		
		if(outlinePage!=null)
			outlinePage.update(document);
	}

	public JSDocumentScanner getScanner() {
		return scanner;
	}
}
