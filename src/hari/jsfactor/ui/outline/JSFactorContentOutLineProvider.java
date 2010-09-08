package hari.jsfactor.ui.outline;

import hari.jsfactor.jsobjects.IJSObject;
import hari.jsfactor.jsobjects.JSFile;
import hari.jsfactor.jsobjects.JSFunction;
import hari.jsfactor.scanner.JSDocumentScanner;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class JSFactorContentOutLineProvider implements ITreeContentProvider {

	private JSDocumentScanner scanner;

	public JSFactorContentOutLineProvider() {
		scanner = new JSDocumentScanner();
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if(newInput!=null){
			System.out.println("Document changed "+newInput);
			IDocument document = (IDocument) newInput;
			scanner.updateModel(document);
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		System.out.println("Get elements"+inputElement);
		JSFile jsFile = scanner.getJSFile();
		return new IJSObject[]{jsFile};
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof JSFile) {
			System.out.println("Inside "+parentElement);
			return scanner.getJSFunctions();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof JSFunction)
			return scanner.getJSFile();
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return element instanceof JSFile && scanner.getJSFile().getContainingObjects().size()>0 ? true:false; 
	}
}
