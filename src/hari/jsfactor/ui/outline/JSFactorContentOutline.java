package hari.jsfactor.ui.outline;

import hari.jsfactor.jsobjects.IJSObject;
import hari.jsfactor.jsobjects.JSFunction;
import hari.jsfactor.scanner.JSDocumentScanner;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

public class JSFactorContentOutline extends ContentOutlinePage {
	private TreeViewer treeViewer;
	private final ITextEditor editor;
	private final JSDocumentScanner scanner;

	
	public JSFactorContentOutline(ITextEditor editor, JSDocumentScanner scanner) {
		this.editor = editor;
		this.scanner = scanner;
	}

	public void setInput(IDocument document) {
		if(getTreeViewer()!=null && document!=null)
			update(document);
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		treeViewer = getTreeViewer();
		JSFactorContentOutLineProvider contentOutlineProvider = new JSFactorContentOutLineProvider(scanner);
		treeViewer.setContentProvider(contentOutlineProvider);
		treeViewer.setLabelProvider(new JSFactorLabelProvider());
		update(editor.getDocumentProvider().getDocument(editor.getEditorInput()));
	}

	public void update(IDocument document) {
		TreeViewer viewer = getTreeViewer();
		if (viewer != null) {
			Control control = viewer.getControl();
			if (control != null && !control.isDisposed()) {
				control.setRedraw(false);
				viewer.setInput(document);
				viewer.expandAll();
				control.setRedraw(true);
			}
		}
	}
	
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		super.selectionChanged(event);
		
		ISelection selection = event.getSelection();
		if (selection.isEmpty())
			editor.resetHighlightRange();
		else{
			IJSObject jsObject = (IJSObject)((IStructuredSelection) selection).getFirstElement();
			if(jsObject instanceof JSFunction){
				JSFunction function = (JSFunction) jsObject;
				editor.setHighlightRange(function.getOffset(), function.getLength(), true);
			}
		}
	}

}
