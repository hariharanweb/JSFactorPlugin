package hari.jsfactor.ui.action;

import hari.jsfactor.ui.JSFactor;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

public class ExtractMethodAction extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (!(selection instanceof TextSelection)
				|| !(editor instanceof JSFactor)) {
			MessageDialog.openError(null, "Cannot perform the operation",
					"This operation is valid only on JSFactor editor");
			return null;
		}
		JSFactor jsFactor = (JSFactor) editor;
		String selectedText = ((TextSelection) selection).getText();
		
		InputDialog inputDialog = new InputDialog(null, "Function name", "Enter the new function name", "NewFunctionName", null);
		inputDialog.open();
		String newFunctionName = inputDialog.getValue();
		
		String newFunctionDefinition = "\nfunction "+newFunctionName+"(){\n\t"+selectedText+"\n}";
		
		IDocument document = jsFactor.getDocumentProvider().getDocument(
				jsFactor.getEditorInput());
		
		
		String newContent = document.get().replace(selectedText, newFunctionName+"();");
		document.set(newContent+newFunctionDefinition);
		return null;
	}


}
